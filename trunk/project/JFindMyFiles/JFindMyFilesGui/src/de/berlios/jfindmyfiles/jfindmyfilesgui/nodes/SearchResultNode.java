/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;

/**
 *
 * @author Knitter
 */
public class SearchResultNode extends AbstractNode {

    private SystemAction[] sysact;
    private FileWrapper file;

    public SearchResultNode(FileWrapper file) {
        super(Children.LEAF);
        this.file = file;
        setName(file.getName());
    }

    @Override
    public Image getIcon(int type) {
        return Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/icon-duplicates.png"); // NOI18N
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public SystemAction[] getActions(boolean bool) {
        //TODO:
        return new SystemAction[]{};
    }
}
