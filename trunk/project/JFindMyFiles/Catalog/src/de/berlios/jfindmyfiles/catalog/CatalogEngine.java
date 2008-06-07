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
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CatalogEngine {

    /* Constants for the available dabatabase engines*/
    public static final int FIREBIRD = 0;
    public static final int POSTGRESQL = 1;
    public static final int MSSQL = 2;
    public static final int MYSQL = 3;
    public static final int LOCAL = 4;
    public SessionFactory sessionFactory;
    private CatalogProperties properties;
    private Vector<CatalogEngineListener> listeners;

    /**
     * Empty construtor so that this class can be instanciated using reflection 
     * by the netbeans platform.
     */
    public CatalogEngine() {
        //DO NOTHING
    }

    private void recreateConnection(String dbname, String dburl, String port,
            String username, String password, int dbType, boolean open) {

        closeCatalog();

        String strategy = "create";
        try {
            Configuration hConfig = new Configuration().configure("de/berlios" +
                    "/jfindmyfiles/catalog/utils/hibernate.general.cfg.xml");

            if (open) {
                strategy = "update";
            }
            //Creation strategy
            hConfig.setProperty("hibernate.hbm2ddl.auto", strategy);

            switch (dbType) {
                case FIREBIRD://TODO: change for FIREBIRD database engine

                    hConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                    hConfig.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
                    hConfig.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:" + dburl + "/" + dbname);
                    hConfig.setProperty("hibernate.connection.username", "sa");
                    hConfig.setProperty("hibernate.connection.password", password);
                    hConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");

                    break;
                case POSTGRESQL://TODO: change for POSTGRESQL database engine

                    hConfig.setProperty("hibernate.dialect", "");
                    hConfig.setProperty("hibernate.connection.driver_class", "");
                    hConfig.setProperty("hibernate.connection.url", "");
                    hConfig.setProperty("hibernate.connection.username", "");
                    hConfig.setProperty("hibernate.connection.password", password);

                    break;
                case MYSQL:
                    //TODO: remove this!
                    username = "jfindmyfilesuser";
                    password = "Jf1ndmYf1l3z!";
                    dbname = "jfindmyfilesdb";
                    dburl = "67.207.139.234";
                    port = "3306";

                    hConfig.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                    hConfig.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                    hConfig.setProperty("hibernate.connection.url", "jdbc:mysql://" + dburl + ":" + port + "/" + dbname);
                    hConfig.setProperty("hibernate.connection.username", username);
                    hConfig.setProperty("hibernate.connection.password", password);
                    break;
                case MSSQL://TODO: change for MSSQL database engine

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

        } catch (Throwable ex) {//TODO: proper logging
            System.err.println("HIBERNATE: Initial SessionFactory creation failed." + ex);
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
        fireCatalogOpened(new CatalogEngineEvent(dbname, null, null, null, null));
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
        fireCatalogCreated(new CatalogEngineEvent(dbname, null, null, null, null));
    }

    /**
     * 
     */
    public void closeCatalog() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
            fireCatalogClosed(new CatalogEngineEvent(properties.getName(), null, null, null, null));
        }
    }

    public CatalogProperties getProperties() {
        return properties;
    }

    public void addDiskGroup(String name, String description, DiskGroup parent) {
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        DiskGroup dg = new DiskGroup();
        dg.setName(name);
        dg.setDescription(description);
        dg.setParent(parent);
        cSession.save(dg);
        cSession.getTransaction().commit();
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

    public void readDisk(File file, DiskGroup group, boolean isMedia) {
        //TODO: remove this method
    }

    public void exportCatalog() {
    }

    public void importCatalog() {
    }

    //NOTE: TEMP CODE!
    public void addListener(CatalogEngineListener l) {
        if (listeners == null) {
            listeners = new Vector<CatalogEngineListener>();
        }
        listeners.add(l);
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
}
