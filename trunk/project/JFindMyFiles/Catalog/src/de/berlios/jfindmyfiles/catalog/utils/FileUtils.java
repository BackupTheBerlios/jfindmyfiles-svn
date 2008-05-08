/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog.utils;

import java.io.File;
import java.util.zip.CRC32;

/**
 *
 * @author knitter
 */
public abstract class FileUtils {
    
    /**
     * Method that, given a file name, will return it's extension.
     * A file extension has little meaning for some operating systems, being 
     * relevant only on Windows OS. Nevertheless it is necessary to determine 
     * the extension of a given file to use on the Description API.
     * 
     * @param filename the name of the file
     * @return the extension or an empty string if this name has no extension
     */
    public static String findExtension(String filename) {
        int x = -1;
        return ((x = filename.lastIndexOf(".")) > 0 ? filename.substring(x + 1) : "");
    }
    
    public String fileCRC(File file) {
        //TODO: implement
        return new CRC32().toString();
    }

}
