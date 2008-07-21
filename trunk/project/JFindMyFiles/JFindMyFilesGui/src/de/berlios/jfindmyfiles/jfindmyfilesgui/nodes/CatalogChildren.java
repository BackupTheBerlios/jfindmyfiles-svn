/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.CatalogEngineEvent;
import de.berlios.jfindmyfiles.catalog.CatalogEngineListener;
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
public class CatalogChildren extends Children.Keys implements CatalogEngineListener {

    private CatalogNode parent;
    private List items;
    private CatalogEngine eng;

    @SuppressWarnings("unchecked")
    public CatalogChildren(CatalogNode parent) {
        this.parent = parent;
        items = new LinkedList();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        eng.addListener(this);
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        items.addAll(s.createQuery("from DiskGroup where parent.id is null").list());
        items.addAll(s.createQuery("from Media where group.id is null").list());
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

    public void catalogCreated(CatalogEngineEvent evt) {
        //ignore
    }

    public void catalogOpened(CatalogEngineEvent evt) {
        //ignore
    }

    public void catalogClosed(CatalogEngineEvent evt) {
        //ignore
    }

    //TODO: not tested!
    @SuppressWarnings("unchecked")
    public void diskGroupAdded(CatalogEngineEvent evt) {
        if (evt.getNewDiskGroup().getParent() == null) {
            items.add(evt.getNewDiskGroup());
            addNotify();
        }
    }

    @SuppressWarnings("unchecked")
    public void diskGroupRemoved(CatalogEngineEvent evt) {
        if (evt.getOldDiskGroup().getParent() == null) {
            items.remove(evt.getOldDiskGroup());
            addNotify();
        }
    }

    @SuppressWarnings("unchecked")
    public void diskGroupRenamed(CatalogEngineEvent evt) {
        if (evt.getNewDiskGroup().getParent() == null) {
            addNotify();
        }
    }

    @SuppressWarnings("unchecked")
    public void diskAdded(CatalogEngineEvent evt) {
        if (evt.getNewDisk().getGroup() == null) {
            items.add(evt.getNewDisk());
            addNotify();
        }
    }

    @SuppressWarnings("unchecked")
    public void diskRemoved(CatalogEngineEvent evt) {
        if (evt.getNewDisk().getGroup() == null) {
            items.remove(evt.getNewDisk());
            addNotify();
        }
    }
}
