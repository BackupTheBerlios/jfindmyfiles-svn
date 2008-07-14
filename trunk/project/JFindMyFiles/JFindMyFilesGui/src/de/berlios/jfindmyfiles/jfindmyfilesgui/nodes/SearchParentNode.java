/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;

/**
 *
 * @author Knitter
 */
public class SearchParentNode extends AbstractNode {

    //private SystemAction[] sysact;
    
    public SearchParentNode() {
        super(Children.LEAF);
        //super(new SearchChildren(null));TODO: squash the damn bug
        setName("Duplicates Found");//TODO: i18n
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
