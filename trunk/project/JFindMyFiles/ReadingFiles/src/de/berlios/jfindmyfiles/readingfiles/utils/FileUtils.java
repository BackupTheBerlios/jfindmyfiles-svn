/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.readingfiles.utils;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import jonelo.jacksum.JacksumAPI;
import jonelo.jacksum.algorithm.AbstractChecksum;

/**
 *
 * @author Knitter
 */
public class FileUtils {

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

    /**
     * Calculates a file's hashing code using the SHA-1 algorithm.
     * It uses the Jacksum library to compute the hash code.
     * 
     * @param file File to calculate the hash for.
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
}
