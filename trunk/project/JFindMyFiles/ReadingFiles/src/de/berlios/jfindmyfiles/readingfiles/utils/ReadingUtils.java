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
package de.berlios.jfindmyfiles.readingfiles.utils;

import de.berlios.jfindmyfiles.catalog.CatalogConstants;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import jonelo.jacksum.JacksumAPI;
import jonelo.jacksum.algorithm.AbstractChecksum;

/**
 * Utility class that provides several useful methods used when reading files.
 */
public class ReadingUtils {

    /**
     * Method that, given a file name, will return it's extension in lower case.
     * A file extension has little meaning for some operating systems, being 
     * relevant only on Windows OS. Nevertheless it is necessary to determine 
     * the extension of a given file to use on the Description API.
     * 
     * @param filename the name of the file
     * @return the extension or an empty string if this name has no extension
     */
    public static String findExtension(String filename) {
        int x = -1;
        return ((x = filename.lastIndexOf(".")) > 0 ? filename.substring(x + 1) : "").toLowerCase();
    }

    /**
     * Calculates a file's hashing code using the SHA-1 algorithm.
     * It uses the Jacksum library to compute the hash code.
     * 
     * @param file the file to calculate the hash for.
     * @return A String with the calculated hash code or an empty string if an 
     * error occorred.
     */
    public static String calculateSHA1HashString(File file) {
        AbstractChecksum checksum = null;
        try {
            checksum = JacksumAPI.getChecksumInstance("sha-1");
            return "" + checksum.readFile(file.getAbsolutePath());
        } catch (IOException ex) {
            return "";
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }

    /**
     * Gets the file type defined by in the catalog constants'.
     * Some assumptions are made regarding drive naming in MS Windows operationg 
     * systems. Such assumptions may provide incorrect results.
     * 
     * @param absolutepath the absolute path to the file.
     * @return an int define by the <code>CatalogConstants</code> interface.
     * 
     * @see de.berlios.jfindmyfiles.catalog.CatalogConstants
     */
    public static int findFileType(String absolutepath) {
        if (absolutepath.length() == 3 && absolutepath.matches("")) {
            if (absolutepath.compareToIgnoreCase("a:\\") == 0 || absolutepath.compareToIgnoreCase("b:\\") == 0) {
                return CatalogConstants.FLOPPY;
            }
            if (absolutepath.compareToIgnoreCase("c:\\") == 0) {
                return CatalogConstants.HDD;
            }
            return CatalogConstants.CDROM;
        }
        return CatalogConstants.FOLDER;
    }

    /**
     * Removes the file extension from a filename.
     * Note that, given a file with, at least, a dot and no extension, the text 
     * after the last dot will be removed, thus the resulting name can be 
     * invalid.
     * 
     * @param filename the name of the file to be read.
     * @return String constructed by removind the text after the last dot, or 
     * an empty string if the filename had no dots.
     */
    public static String stripFileExtension(String filename) {
        int x = -1;
        return ((x = filename.lastIndexOf(".")) > 0 ? filename.substring(0, x) : filename).toLowerCase();
    }
}
