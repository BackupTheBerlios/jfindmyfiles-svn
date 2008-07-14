/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.utils;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

/**
 *
 * @author Knitter
 */
public class CatalogView extends FileView {

    private static final ImageIcon icon = new ImageIcon();

    @Override
    public String getTypeDescription(File f) {
        //TODO: i18n
        return "JFindMyFile Catalog";
    }

    @Override
    public Icon getIcon(File f) {
        boolean found = false;

        if (f.isDirectory()) {
            File[] lst = f.listFiles();
            if (lst != null) {
                for (File f2 : lst) {
                    if (f2.getName().equalsIgnoreCase("jfindmyfiles.catalog")) {
                        found = true;
                        break;
                    }
                }
            }
        }
        return found ? icon : null;
    }
}
