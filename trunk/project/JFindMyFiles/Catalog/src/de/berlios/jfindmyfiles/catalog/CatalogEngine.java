/**
 *  Copyright (C) 2008  Patrícia Monteiro e Sérgio Lopes
 *
 *  This file is part of JFindMyFiles.
 *
 *  JFindMyFiles is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFindMyFiles is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFindMyFiles.  If not, see 
 * <http://www.gnu.org/licenses/gpl.html>.
 */
package de.berlios.jfindmyfiles.catalog;

import de.berlios.jfindmyfiles.catalog.entities.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class CatalogEngine {

    /* Constants for the available dabatabase engines*/
    public SessionFactory sessionFactory;
    private CatalogProperties properties;
//    private Vector<CatalogEngineListener> listeners;
    private CopyOnWriteArrayList<CatalogEngineListener> listeners;
    private static final Logger logger = Logger.getLogger(CatalogEngine.class.getName());
    private boolean opened;

    /**
     * Empty construtor so that this class can be instanciated using reflection 
     * by the netbeans platform.
     */
    public CatalogEngine() {
        //DO NOTHING
        listeners = new CopyOnWriteArrayList<CatalogEngineListener>();
    }

    private void recreateConnection(String dbname, String dburl, String port,
            String username, String password, int dbType, boolean open) {

        closeCatalog();

        String strategy = "create";
        try {
            Configuration hConfig = new Configuration().configure("de/berlios" +
                    "/jfindmyfiles/catalog/hibernate.general.cfg.xml");

            if (open) {
                strategy = "update";
            }
            //Creation strategy
            hConfig.setProperty("hibernate.hbm2ddl.auto", strategy);

            switch (dbType) {
                case CatalogConstants.FIREBIRD://TODO: change for FIREBIRD database engine

                    hConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                    hConfig.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
                    hConfig.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:" + dburl + "/" + dbname);
                    hConfig.setProperty("hibernate.connection.username", "sa");
                    hConfig.setProperty("hibernate.connection.password", password);
                    hConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");

                    break;
                case CatalogConstants.POSTGRESQL://TODO: change for POSTGRESQL database engine

                    hConfig.setProperty("hibernate.dialect", "");
                    hConfig.setProperty("hibernate.connection.driver_class", "");
                    hConfig.setProperty("hibernate.connection.url", "");
                    hConfig.setProperty("hibernate.connection.username", "");
                    hConfig.setProperty("hibernate.connection.password", password);

                    break;
                case CatalogConstants.MYSQL:
                    hConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                    hConfig.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                    hConfig.setProperty("hibernate.connection.url", "jdbc:mysql://" + dburl + ":" + port + "/" + dbname);
                    hConfig.setProperty("hibernate.connection.username", username);
                    hConfig.setProperty("hibernate.connection.password", password);
                    break;
                case CatalogConstants.MSSQL://TODO: change for MSSQL database engine

                    hConfig.setProperty("hibernate.dialect", "");
                    hConfig.setProperty("hibernate.connection.driver_class", "");
                    hConfig.setProperty("hibernate.connection.url", "");
                    hConfig.setProperty("hibernate.connection.username", "");
                    hConfig.setProperty("hibernate.connection.password", password);
                    break;
                default://If it's not a network database we can only use HSQLDB engine

                    hConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                    hConfig.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
                    hConfig.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:" + dburl + "/" + dbname);
                    hConfig.setProperty("hibernate.connection.username", "sa");//Default user for every HSQLDB database

                    //Try to shutdown the database as soon as there all connections are gone
                    //hConfig.setProperty("hibernate.connection.shutdown", "true");

                    hConfig.setProperty("hibernate.connection.password", "");//HSQLDB has a user with no password

            }

            sessionFactory = hConfig.buildSessionFactory();
            opened = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "HIBERNATE: Initial SessionFactory creation failed.", ex);
            opened = false;
            throw new ExceptionInInitializerError(ex);
        }

    }

    /**
     * 
     * @param dbname
     * @param dburl
     * @param port
     * @param dbType
     * @param username
     * @param password
     */
    public void openCatalog(String dbname, String dburl, String port, int dbType,
            String username, String password) {

        recreateConnection(dbname, dburl, port, username, password, dbType, true);

        //Reading catalog properties
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        properties = (CatalogProperties) cSession.createQuery("from CatalogProperties").uniqueResult();
        cSession.getTransaction().commit();
        fireCatalogOpened(new CatalogEngineEvent(this, dbname, null, null, null, null));
    }

    /**
     * 
     * @param dbname
     * @param dburl
     * @param port
     * @param dbType
     * @param username
     * @param password
     */
    public void createCatalog(String dbname, String dburl, String port, int dbType,
            String username, String password) {

        recreateConnection(dbname, dburl, port, username, password, dbType, false);

        //Create and save catalog properties
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        properties = new CatalogProperties();
        properties.setName(dbname);
        properties.setCreationDate(new Date());
        cSession.save(properties);
        cSession.getTransaction().commit();
        fireCatalogCreated(new CatalogEngineEvent(this, dbname, null, null, null, null));
    }

    /**
     * 
     */
    public void closeCatalog() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
            opened = false;
            fireCatalogClosed(new CatalogEngineEvent(this, properties.getName(), null, null, null, null));
            listeners.clear();
        }
    }

    public CatalogProperties getProperties() {
        return properties;
    }

    public boolean isOpened() {
        return opened;
    }

    /*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
    public void addDiskGroup(String name, String description, DiskGroup parent) {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        DiskGroup dg = new DiskGroup();
        dg.setName(name);
        dg.setDescription(description);
        dg.setParent(parent);
        cSession.save(dg);
        cSession.getTransaction().commit();
        fireDiskGroupAdded(new CatalogEngineEvent(this, "", null, dg, null, null));
    }

    public void addLabel(String name) {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        Label l = new Label();
        l.setName(name);
        cSession.save(l);
        cSession.getTransaction().commit();
    }

    public void removeLabel(Label label) {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        Label l = (Label) cSession.createQuery("from Label where id=" + label.getId()).uniqueResult();
        cSession.delete(l);
        cSession.getTransaction().commit();
    }

    public void addUser(String firstname, String surname) {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        User u = new User();
        u.setFirstname(firstname);
        u.setSurname(surname);
        cSession.save(u);
        cSession.getTransaction().commit();
    }

    public void removeUser(User user) {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        User u = (User) cSession.createQuery("from Label where id=" + user.getId()).uniqueResult();
        cSession.delete(u);
        cSession.getTransaction().commit();
    }

    public List getLabels() {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        List rs = cSession.createQuery("from User").list();
        cSession.getTransaction().commit();
        return rs;
    }

    public List getUsers() {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        List rs = cSession.createQuery("from User").list();
        cSession.getTransaction().commit();
        return rs;
    }

    public List getDiskGroups() {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        List rs = cSession.createQuery("from DiskGroup").list();
        cSession.getTransaction().commit();
        return rs;
    }

    public List getDisks() {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        List rs = cSession.createQuery("from Media").list();
        cSession.getTransaction().commit();
        return rs;
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void addListener(CatalogEngineListener l) {
        if (listeners == null) {
            listeners = new CopyOnWriteArrayList<CatalogEngineListener>();
        }
        listeners.add(l);
    }

    public void removeListener(CatalogEngineListener l) {
        if (listeners != null) {
            listeners.remove(l);
        }
    }

    private void fireCatalogCreated(CatalogEngineEvent evt) {
        if (listeners != null) {
            for (CatalogEngineListener l : listeners) {
                l.catalogCreated(evt);
            }
        }
    }

    private void fireCatalogOpened(CatalogEngineEvent evt) {
        if (listeners != null) {
            for (CatalogEngineListener l : listeners) {
                l.catalogOpened(evt);
            }
        }
    }

    private void fireCatalogClosed(CatalogEngineEvent evt) {
        if (listeners != null) {
            for (CatalogEngineListener l : listeners) {
                l.catalogClosed(evt);
            }
        }
    }

    private void fireDiskGroupAdded(CatalogEngineEvent evt) {
        if (listeners != null) {
            for (CatalogEngineListener l : listeners) {
                l.diskGroupAdded(evt);
            }
        }
    }

    /**
     * In overriding the finalize method we try to garantee that data is 
     * flushed and all resources are released.
     */
    @Override
    public void finalize() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    //TESTING:
    public void addNewDisk(Media disk, List<FileWrapper> files) {
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();
        s.save(disk);
        for (FileWrapper f : files) {
            s.save(f);
        }
        s.getTransaction().commit();
    }
    //TESTING:
    public List<FileWrapper> searchDuplicates(String hash) {
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();
        List rs = null;//TODO:s.createCriteria(FileWrapper.class).add(Restrictions.like("sha1", hash, MatchMode.EXACT)).list();
        s.getTransaction().commit();
        return rs;
    }
    
    public List<FileWrapper> findDuplicates() {
        List<FileWrapper> result = new ArrayList<FileWrapper>();
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();
        result.addAll(s.createQuery("select f from FileWrapper f where sha1 in " +
                "(select f2.sha1 from FileWrapper f2 group by f2.sha1 having " +
                "( count(f2.sha1) > 1 AND f2.sha1 <> '' AND f2.sha1 is not null))").list());      
        s.getTransaction().commit();
        
        return result;
    }
}
