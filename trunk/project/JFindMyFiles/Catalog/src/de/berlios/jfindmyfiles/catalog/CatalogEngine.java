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
import java.util.LinkedList;
import java.util.List;
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
    private SessionFactory sessionFactory;
    
    private CatalogProperties properties = new CatalogProperties("teste", "<sem desc>", 50, 25, 500, 30, new Date()); //TODO: remove

    /**
     * Empty construtor so that this class can be instanciated using reflection 
     * by the netbeans platform.
     */
    public CatalogEngine() {
        //do nothing
    }

    //TODO: error management
    private void recreateConnection(String dbname, String dburl, String port,
            String username, String password, int dbType) {

        closeCatalog();
        try {
            /*Configuration cfg = new Configuration().configure("de/berlios" +
            "/jfindmyfiles/catalog/utils/hibernate.hsqldb.cfg.xml");*/

            Configuration cfg = new Configuration()//Configuration object
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.AudioData.class)//Add AudioData class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.DiskGroup.class)//Add DiskGroup class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.FileWrapper.class)//Add FileWrapper class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.ImageData.class)//Add ImageData class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.Label.class)//Add Label class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.Loan.class)//Add Loan class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.Media.class)//Add Media class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.Type.class)//Add Type class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.User.class)//Add User class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.VideoData.class)//Add VideoData class
                    .addClass(de.berlios.jfindmyfiles.catalog.entities.CatalogProperties.class)//Add CatalogProperties class
                    .setProperty("hibernate.connection.pool_size", "1")//JDBC connection pool (use the built-in)
                    .setProperty("hibernate.current_session_context_class", "thread")//Enable Hibernate's automatic session context management
                    .setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider")//Disable the second-level cache
                    .setProperty("hibernate.show_sql", "true")//Echo all executed SQL to stdout
                    .setProperty("hibernate.hbm2ddl.auto", "create-drop");//TODO: remove this line and replace with proper one

            switch (dbType) {
                case FIREBIRD://TODO: change for FIREBIRD database engine

                    cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                    cfg.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
                    cfg.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:" + dburl + "/" + dbname);
                    cfg.setProperty("hibernate.connection.username", "sa");
                    cfg.setProperty("hibernate.connection.password", password);
                    cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");

                    break;
                case POSTGRESQL://TODO: change for POSTGRESQL database engine

                    cfg.setProperty("hibernate.dialect", "o");
                    cfg.setProperty("hibernate.connection.driver_class", "");
                    cfg.setProperty("hibernate.connection.url", "");
                    cfg.setProperty("hibernate.connection.username", "");
                    cfg.setProperty("hibernate.connection.password", password);

                    break;
                case MYSQL:
                    //TODO: remove this!
                    username = "jfindmyfilesuser";
                    password = "Jf1ndmYf1l3z!";
                    dbname = "jfindmyfilesdb";
                    dburl = "67.207.139.234";
                    port = "3306";

                    cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                    cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                    cfg.setProperty("hibernate.connection.url", "jdbc:mysql://" + dburl + ":" + port + "/" + dbname);
                    cfg.setProperty("hibernate.connection.username", username);
                    cfg.setProperty("hibernate.connection.password", password);
                    break;
                case MSSQL://TODO: change for MSSQL database engine

                    cfg.setProperty("hibernate.dialect", "");
                    cfg.setProperty("hibernate.connection.driver_class", "");
                    cfg.setProperty("hibernate.connection.url", "");
                    cfg.setProperty("hibernate.connection.username", "");
                    cfg.setProperty("hibernate.connection.password", password);
                    break;
                default://If it's not a network database we can only use HSQLDB engine

                    cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                    cfg.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
                    cfg.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:" + dburl + "/" + dbname);
                    cfg.setProperty("hibernate.connection.username", "sa");//Default user for every HSQLDB database
                    cfg.setProperty("hibernate.connection.password", "");//HSQLDB has a user with no password
            }
            
            sessionFactory = cfg.buildSessionFactory();
            
        } catch (Throwable ex) {//TODO: proper logging
            // Make sure you log the exception, as it might be swallowed
            System.err.println("HIBERNATE: Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public void openCatalog(String dbname, String dburl, String port, int dbType,
            String username, String password) {

        recreateConnection(dbname, dburl, port, username, password, dbType);
        
        //Reading catalog properties
        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();
        List result = cSession.createQuery("from CatalogProperties").list();
        cSession.getTransaction().commit();
        properties = (CatalogProperties)result.get(0);
    }

    public void createCatalog(String dbname, String dburl, String port, int dbType,
            String username, String password) {

        recreateConnection(dbname, dburl, port, username, password, dbType);

        Session cSession = sessionFactory.getCurrentSession();
        cSession.beginTransaction();//TODO: find correct settings to store
        properties = new CatalogProperties();
        properties.setName(dbname);
        cSession.save(properties);
        cSession.getTransaction().commit();
        cSession.close();

    //TODO: notify listeners
    }

    public void closeCatalog() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }
    
    public CatalogProperties getProperties() {
        return properties;
    }
    
    public void setProperties(CatalogProperties props) {
        //TODO: persist the object
        this.properties = props;
    }

    public void addDiskGroup(String name, String description, DiskGroup parent) {
      /*  Session hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        DiskGroup dg = new DiskGroup();
        dg.setName(name);
        dg.setDescription(description);
        dg.setParent(parent);
        hSession.save(dg);
        hSession.getTransaction().commit();*/
    }

    public void addLabel(String name) {
        /*Session hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Label l = new Label();
        l.setName(name);
        hSession.save(l);
        hSession.getTransaction().commit();*/
    }

    public void removeLabel(Label label) {
       /*Session  hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List result = hSession.createQuery("from Label where id=" + label.getId()).list();
        if (result.size() != 1) {
            ;//TODO: show error, invalid state

        }
        Label rem = (Label) result.get(0);//TODO: verify this

        hSession.delete(rem);
        hSession.getTransaction().commit();*/
    }

    public void addUser(String firstname, String surname) {
        /*Session hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        User u = new User();
        u.setFirstname(firstname);
        u.setSurname(surname);
        hSession.save(u);
        hSession.getTransaction().commit();*/
    }

    public void removeUser(User user) {
        /*Session hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List result = hSession.createQuery("from Label where id=" + user.getId()).list();
        if (result.size() != 1) {
            ;//TODO: show error, invalid state

        }
        User rem = (User) result.get(0);//TODO: verify this

        hSession.delete(rem);
        hSession.getTransaction().commit();*/
    }

    public List getLabels() {//TODO: validate session states
/*
Session         hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List rs = hSession.createQuery("from User").list();
        hSession.getTransaction().commit();
        return rs;
        */
                return null;
    }

    public List getUsers() {//TODO: validate session states
/*
     Session    hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List rs = hSession.createQuery("from User").list();
        hSession.getTransaction().commit();
        return rs;*/
                return null;
    }

    public List getDiskGroups() {//TODO: validate session states
       /* hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List rs = hSession.createQuery("from DiskGRoup").list();
        hSession.getTransaction().commit();
        return rs;*/
        return new LinkedList();
    }

    public void readDisk(File file, DiskGroup group, boolean isMedia) {
        /*Stack<File> directories = new Stack<File>();
        File current, listing[];
        long totalSize = 0L, childSize = 0L;
        FileWrapper fw, child;
        int z;
        /*Media container = null;
        
        if(isMedia) {
        container = new Media();            
        }*/

        /*if (file.isDirectory()) {//NOTE: this protections may be dropped
        fw = new FileWrapper(file.getName(),
        file.getAbsolutePath(), 0, file.isFile(), file.isHidden(),
        file.lastModified(), container, null);
        
        if ((listing = file.listFiles()) != null) {
        for (z = listing.length; z-- > 0;) {
        if (listing[z].isDirectory()) {
        directories.push(listing[z]);
        } else {
        totalSize += listing[z].length();
        fw.addChild(new FileWrapper(listing[z].getName(),
        listing[z].getAbsolutePath(),
        listing[z].length(), listing[z].isFile(),
        listing[z].isHidden(),
        listing[z].lastModified(), container, fw));
        }
        }
        }
        
        while (!directories.empty()) {
        current = directories.pop();
        childSize = 0;
        child = new FileWrapper(current.getName(),
        current.getAbsolutePath(), 0, current.isFile(),
        current.isHidden(), current.lastModified(),
        container, null);
        
        if ((listing = current.listFiles()) != null) {
        for (z = listing.length; z-- > 0;) {
        if (listing[z].isDirectory()) {
        directories.push(listing[z]);
        } else {
        childSize += listing[z].length();
        child.addChild(new FileWrapper(listing[z].getName(),
        listing[z].getAbsolutePath(),
        listing[z].length(), listing[z].isFile(),
        listing[z].isHidden(),
        listing[z].lastModified(), container, fw));
        }
        }
        }
        child.setSize(childSize);
        }
        fw.setSize(totalSize);
        }*/
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
