/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import java.util.ArrayList;
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
public class SearchChildren extends Children.Keys {

    private SearchParentNode parent;
    private CatalogEngine eng;
    private List items;

    @SuppressWarnings("unchecked")
    public SearchChildren(SearchParentNode parent) {
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        items = new LinkedList();
        this.parent = parent;
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        items.addAll(s.createQuery("select f from FileWrapper f where sha1 in " +
                "(select f2.sha1 from FileWrapper f2 group by f2.sha1 having " +
                "( count(f2.sha1) > 1 AND f2.sha1 <> '' AND f2.sha1 is not null))").list());
        //items = s.createSQLQuery("SELECT * FROM tbl_file WHERE sha1 in(SELECT e.sha1 FROM tbl_file e GROUP BY e.sha1 HAVING ( COUNT(e.sha1) > 1 AND e.sha1 <> '' AND e.sha1 is not null))").list();
        s.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addNotify() {
        List keys = items;
        setKeys(keys);
    }

    @Override
    protected Node[] createNodes(Object key) {
        return new Node[]{new SearchResultNode((FileWrapper) key)};
    }
}
