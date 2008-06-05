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
public class DiskGroupChildren extends Children.Keys {

    private Long parentId;
    private List items;
    private CatalogEngine eng;

    @SuppressWarnings("unchecked")
    public DiskGroupChildren(Long parentId) {
        this.parentId = parentId;
        items = new LinkedList();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        items.addAll(s.createQuery("from DiskGroup group where group.parent.id = ?").setLong(0, parentId).list());
        items.addAll(s.createQuery("from Media disk where disk.group.id = ?").setLong(0, parentId).list());
        s.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addNotify() {
        List keys = items;
        setKeys(keys);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Node[] createNodes(Object key) {
        if (key instanceof DiskGroup) {
            Session s = eng.sessionFactory.getCurrentSession();
            s.beginTransaction();
            List rs = s.createQuery("from DiskGroup group where parent.id = ?").setLong(0, ((DiskGroup) key).getId()).list();
            rs.addAll(s.createQuery("from Media media where group.id = ?").setLong(0, ((DiskGroup) key).getId()).list());
            s.getTransaction().commit();

            DiskGroupNode gn = new DiskGroupNode((DiskGroup) key, rs.isEmpty());
            return new Node[]{gn};
        }
        if (key instanceof Media) {
            DiskNode in = new DiskNode((Media) key);
            return new Node[]{in};
        }
        return null;
    }

}
