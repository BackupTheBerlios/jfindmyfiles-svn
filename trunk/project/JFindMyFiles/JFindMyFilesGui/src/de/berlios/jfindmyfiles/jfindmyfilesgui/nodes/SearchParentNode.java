/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;

/**
 *
 * @author Knitter
 */
public class SearchParentNode extends AbstractNode {
    
    private SearchChildren ch;
    
    public SearchParentNode() {
        this(new SearchChildren());
    }
    
    private SearchParentNode(SearchChildren ch) {
       super(ch);
       this.ch = ch;
       setName("Duplicates Found: " + ch.getItemCount());//TODO: i18n
    }

    @Override
    public Image getIcon(int type) {
        return Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/search-duplicates.png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public SystemAction[] getActions(boolean bool) {
        return new SystemAction[]{};
    }
}
