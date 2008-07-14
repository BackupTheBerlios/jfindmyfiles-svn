/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.jfindmyfilesgui.utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Knitter
 */
public class CatalogFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
    if (f.isDirectory()) {
	return true;
    }
    //TODO: check filtering options
    return false;
}

    @Override
    public String getDescription() {
        //TODO: i18n
        return "JFindMyFiles Catalog";
    }

}
