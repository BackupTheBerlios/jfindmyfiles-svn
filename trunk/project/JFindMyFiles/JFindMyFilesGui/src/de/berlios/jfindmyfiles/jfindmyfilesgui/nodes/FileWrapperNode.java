/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.CatalogConstants;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import de.berlios.jfindmyfiles.jfindmyfilesgui.utils.GuiUtils;
import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Knitter
 */
public class FileWrapperNode extends AbstractNode {

    private FileWrapper folder;
    
    public FileWrapperNode(FileWrapper folder, boolean leaf) {
        super(leaf ? Children.LEAF : new DiskChildren(folder.getId(), false), Lookups.singleton(folder));
        this.folder = folder;
        setName(folder.getName());
    }
    
   /* @Override
    protected Sheet createSheet() {
        Sheet s = Sheet.createDefault();
        try {
            
            Sheet.Set sSet = Sheet.createPropertiesSet();
            //Property p = new PropertySupport.Reflection(media, String.class, "name");
            Property p = new PropertySupport.Reflection(folder, String.class, "name");
            //DiskNameProperty p = new DiskNameProperty(media.getName(), "", media.getName());
            sSet.put(p);
            s.put(sSet);
        } catch (NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
        }
        return s;
    } */   

    @Override
    public Image getIcon(int type) {
        //return Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/icon-media-drive-optical.png"); // NOI18N
        return Utilities.loadImage(GuiUtils.findIconForType(CatalogConstants.FOLDER)); // NOI18N
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }    
}
