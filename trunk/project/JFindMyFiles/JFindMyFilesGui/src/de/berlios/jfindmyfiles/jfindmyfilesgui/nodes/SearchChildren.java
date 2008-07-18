/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import java.util.List;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Knitter
 */
public class SearchChildren extends Children.Keys {

    private List items;

    @SuppressWarnings("unchecked")
    public SearchChildren() {
        CatalogEngine eng = Lookup.getDefault().lookup(CatalogEngine.class);
        items = eng.findDuplicates();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addNotify() {
        setKeys(items);
    }

    @Override
    protected Node[] createNodes(Object key) {
        return new Node[]{new SearchResultNode((FileWrapper) key)};
    }

    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }
}
