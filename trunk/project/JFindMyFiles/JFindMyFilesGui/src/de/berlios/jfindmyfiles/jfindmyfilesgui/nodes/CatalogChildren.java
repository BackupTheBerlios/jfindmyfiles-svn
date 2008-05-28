/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.catalog.entities.Media;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Knitter
 */
public class CatalogChildren extends Children.Keys {

    private CatalogNode parent;
    private List items;
    private CatalogEngine eng;

    public CatalogChildren(CatalogNode parent) {
        this.parent = parent;
        items = new LinkedList();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        items.addAll(s.createQuery("from DiskGroup").list());
        items.addAll(s.createQuery("from Media").list());
        s.getTransaction().commit();
    }

    @Override
    protected void addNotify() {
        List keys = items;
        setKeys(keys);
    }

    @Override
    protected Node[] createNodes(Object key) {
        if (key instanceof DiskGroup) {
            DiskGroupNode gn = new DiskGroupNode((DiskGroup) key);
            return new Node[]{gn};
        }
        if (key instanceof Media) {
            DiskNode in = new DiskNode((Media) key);
            return new Node[]{in};
        }
        return null;
    }
}
