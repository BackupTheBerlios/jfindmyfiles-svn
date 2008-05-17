/**
 *  Copyright (C) 2008  Patrícia Monteiro e Sérgio Lopes
 *
 *  This file is part of JFindMyFiles.
 *
 *  JFindMyFiles is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFindMyFiles is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFindMyFiles.  If not, see 
 * <http://www.gnu.org/licenses/gpl.html>.
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
        //TODO: implement!
        return new CRC32().toString();
    }
    
    public String getIconFromExtension(String extension) {
        //TODO: implement!
        return "";
    }

}
