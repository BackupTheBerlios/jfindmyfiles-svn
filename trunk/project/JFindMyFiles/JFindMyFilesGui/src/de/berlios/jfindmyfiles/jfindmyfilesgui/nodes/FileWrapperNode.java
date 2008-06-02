/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;

/**
 *
 * @author Knitter
 */
public class FileWrapperNode extends AbstractNode {
    
    public FileWrapperNode() {
       super(Children.LEAF);

        CatalogEngine eng = Lookup.getDefault().lookup(CatalogEngine.class);
        setName(eng.getProperties().getName());
    }

    @Override
    public Image getIcon(int type) {//TODO: remove icon and replace with correct one
        return Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/icon-audio.png"); // NOI18N
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
