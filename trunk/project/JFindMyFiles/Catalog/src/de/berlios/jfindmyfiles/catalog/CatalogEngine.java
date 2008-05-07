/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog;

import de.berlios.jfindmyfiles.catalog.model.*;
import de.berlios.jfindmyfiles.catalog.utils.ConnectionManager;
import java.util.List;
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

        if(store) {
            Label l = new Label();
            l.setName("uma label" + ++count);
            session.save(l);        
        } else {
            List<Label> res = session.createQuery("from Label").list();
            
            for(Label l1 : res) {
                System.out.println(l1);
            }
        }
        session.getTransaction().commit();
        ConnectionManager.getSessionFactory().close();
    }
}
