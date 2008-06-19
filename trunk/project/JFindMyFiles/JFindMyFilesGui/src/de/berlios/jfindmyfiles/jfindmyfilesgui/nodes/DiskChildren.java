/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
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
public class DiskChildren extends Children.Keys {

    private Long parentId;
    private List items;
    private CatalogEngine eng;
    private boolean isBase;

    @SuppressWarnings("unchecked")
    public DiskChildren(Long parentId, boolean isBase) {
        this.parentId = parentId;
        items = new LinkedList();
        this.isBase = isBase;
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        if (isBase) {
            items.addAll(s.createQuery("from FileWrapper file where file.folder = ? and file.disk.id = ? and file.parent is null").setBoolean(0, true).setLong(1, parentId).list());
        } else {
            items.addAll(s.createQuery("from FileWrapper file where file.folder = ? and file.parent.id = ?").setBoolean(0, true).setLong(1, parentId).list());
        }
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
        FileWrapper f = (FileWrapper) key;
        Session s = eng.sessionFactory.getCurrentSession();
        List rs = null;
        s.beginTransaction();
        if (f.isFolder()) {
            rs = s.createQuery("from FileWrapper file where file.folder = ? and file.parent.id = ?").setBoolean(0, true).setLong(1, f.getId()).list();
        }        
        s.getTransaction().commit();

        FileWrapperNode fwn = new FileWrapperNode((FileWrapper) key, rs.isEmpty());
        return new Node[]{fwn};
    }
}
