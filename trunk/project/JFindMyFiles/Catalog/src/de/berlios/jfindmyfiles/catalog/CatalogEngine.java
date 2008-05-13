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
import de.berlios.jfindmyfiles.catalog.utils.ConnectionManager;
import java.io.File;
import java.util.List;
import java.util.Stack;
import org.hibernate.Session;

/**
 *
 * @author ei10635
 */
public class CatalogEngine {

    Session hSession;

    public CatalogEngine() {
        //hSession = ConnectionManager.getSessionFactory().getCurrentSession();
    }

    public void runtest(boolean store) {//TODO: remove
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();

        User u1 = new User();
        u1.setFirstname("user");
        u1.setSurname("1");
        hSession.save(u1);

        User u2 = new User();
        u2.setFirstname("user");
        u2.setSurname("2");
        hSession.save(u2);

        User u3 = new User();
        u3.setFirstname("user");
        u3.setSurname("3");
        hSession.save(u3);


        User u4 = new User();
        u4.setFirstname("user");
        u4.setSurname("4");
        hSession.save(u4);

        User u5 = new User();
        u5.setFirstname("user");
        u5.setSurname("5");
        hSession.save(u5);
        hSession.getTransaction().commit();
    }

    public void addDiskGroup(String name, String description, DiskGroup parent) {
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        DiskGroup dg = new DiskGroup();
        dg.setName(name);
        dg.setDescription(description);
        dg.setParent(parent);
        hSession.save(dg);
        hSession.getTransaction().commit();          
    }

    public void addLabel(String name) {
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Label l = new Label();
        l.setName(name);
        hSession.save(l);
        hSession.getTransaction().commit();        
    }

    public void removeLabel(Label label) {
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List result = hSession.createQuery("from Label where id=" + label.getId()).list();
        if(result.size() != 1) {
            ;//TODO: show error, invalid state
        }
        Label rem = (Label)result.get(0);//TODO: verify this
        hSession.delete(rem);
        hSession.getTransaction().commit();          
    }

    public void addUser(String firstname, String surname) {
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        User u = new User();
        u.setFirstname(firstname);
        u.setSurname(surname);
        hSession.save(u);
        hSession.getTransaction().commit();          
    }

    public void removeUser(User user) {
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List result = hSession.createQuery("from Label where id=" + user.getId()).list();
        if(result.size() != 1) {
            ;//TODO: show error, invalid state
        }
        User rem = (User)result.get(0);//TODO: verify this
        hSession.delete(rem);
        hSession.getTransaction().commit();        
    }

    public List getLabels() {//TODO: validate session states
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List rs = hSession.createQuery("from User").list();
        hSession.getTransaction().commit();
        return rs;
    }

    public List getUsers() {//TODO: validate session states
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List rs = hSession.createQuery("from User").list();
        hSession.getTransaction().commit();
        return rs;
    }

    public List getDiskGroups() {//TODO: validate session states
        hSession = ConnectionManager.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        List rs = hSession.createQuery("from DiskGRoup").list();
        hSession.getTransaction().commit();
        return rs;
    }

    public void readDisk(File file, Media container) {
        Stack<File> directories = new Stack<File>();
        File current, listing[];
        long totalSize = 0L, childSize = 0L;
        FileWrapper fw, child;
        int z;

        if (file.isDirectory()) {//NOTE: this protections may be dropped
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
        }
    }

    /**
     * In overriding the finalize method we try to garantee that data is 
     * flushed and all resources are released.
     */
    @Override
    public void finalize() {
        if (hSession != null && hSession.isOpen()) {
            //hSession.flush();hSession.clear();
            hSession.close();
        }
    }
}
