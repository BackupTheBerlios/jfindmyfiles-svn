/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.utils;

import de.berlios.jfindmyfiles.catalog.CatalogConstants;

public abstract class GuiUtils {
    
    public static String findIconForType(int type) {
        switch(type) {
            case CatalogConstants.CDROM:
                return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/media-drive-optical.png";
            case CatalogConstants.DVDROM:
                return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/media-drive-optical.png";
            case CatalogConstants.FLOPPY:
                return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/media-floppy.png";
            case CatalogConstants.FOLDER:
                return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/media-folder.png";
            case CatalogConstants.HDD:
                return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/harddisk.png";
            default:
                return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/media-drive-optical.png";
                
        }
    }

}
