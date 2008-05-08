/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog;

import de.berlios.jfindmyfiles.catalog.entities.*;
import de.berlios.jfindmyfiles.catalog.utils.ConnectionManager;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author ei10635
 */
public class CatalogEngine {

    private static int count = 0;

    public void runtest(boolean store) {
        Session session = ConnectionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Label l = new Label();
        l.setName("uma label" + ++count);
        session.save(l);

        session.getTransaction().commit();
        ConnectionManager.getSessionFactory().close();
    }
    
    public void addDiskGroup(String name, String description, DiskGroup parent) {
        
    }
    
    public void addLabel(String name) {
        
    }
    
    public void removeLabel(Long id) {
        
    }
    
    public void removeLabel(Label label) {
        
    }
    
    public void addUser(String firstname, String surname) {
        
    }
    
    public void removeUser(Long id) {
        
    }
    
    public void removeUser(User user) {
        
    }
    
    public Set getLabels() {
        return null;
    }
    
    public Set getUsers() {
        return null;
    }
    
    public Set getDiskGroups() {
        return null;
    }
}
