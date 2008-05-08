/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.readingfiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author ei10635
 */
public class Reader {
    
    private static Reader reader;
    
    private Reader() {
    }
    
    public static Reader getReader() {
        if(reader == null) {
            reader = new Reader();
        }
        return reader;
    }
    
    public List<File> read(File parent) {
        //TODO: correctly read folders and file info.
        Stack<File> dados = new Stack<File>();
        ArrayList<File> fileList = new ArrayList<File>();
        File f2 = parent;
        long size = 0L;
        do {
            if (f2.isDirectory()) {
                File[] listing = f2.listFiles();
                if (listing != null) {
                    size = 0;
                    for (int i = listing.length; i-- > 0;) {
                        if (listing[i].isDirectory()) {
                            dados.push(listing[i]);
                        } else {
                            size += listing[i].length();
                        }
                    }
                }
            }            
        }while(!dados.empty());
        
        return fileList;
    }

}
