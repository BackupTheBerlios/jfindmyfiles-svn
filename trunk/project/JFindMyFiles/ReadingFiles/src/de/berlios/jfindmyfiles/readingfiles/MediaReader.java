/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.readingfiles;

import java.io.File;

/**
 *
 * @author Knitter
 */
public class MediaReader {
    
    public void read(File source, boolean sha) {
        
    }
    /*Stack<File> directories = new Stack<File>();
    File current, listing[];
    long totalSize = 0L, childSize = 0L;
    FileWrapper fw, child;
    int z;
    /*Media container = null;
    
    if(isMedia) {
    container = new Media();            
    }*/

    /*if (file.isDirectory()) {//NOTE: this protections may be dropped
    fw = new FileWrapper(file.getName(),
    file.getAbsolutePath(), 0, file.isFile(), file.isHidden(),
    file.lastModified(), container, null);
    
    if ((listing = file.listFiles()) != null) {
    for (z = listing.length; z-- > 0;) {
    if (listing[z].isDirectory()) {
    directories.push(listing[z]);
    } else {
    totalSize += listing[z].length();
    fw.addChild(new FileWrapper(listing[z].getName(),
    listing[z].getAbsolutePath(),
    listing[z].length(), listing[z].isFile(),
    listing[z].isHidden(),
    listing[z].lastModified(), container, fw));
    }
    }
    }
    
    while (!directories.empty()) {
    current = directories.pop();
    childSize = 0;
    child = new FileWrapper(current.getName(),
    current.getAbsolutePath(), 0, current.isFile(),
    current.isHidden(), current.lastModified(),
    container, null);
    
    if ((listing = current.listFiles()) != null) {
    for (z = listing.length; z-- > 0;) {
    if (listing[z].isDirectory()) {
    directories.push(listing[z]);
    } else {
    childSize += listing[z].length();
    child.addChild(new FileWrapper(listing[z].getName(),
    listing[z].getAbsolutePath(),
    listing[z].length(), listing[z].isFile(),
    listing[z].isHidden(),
    listing[z].lastModified(), container, fw));
    }
    }
    }
    child.setSize(childSize);
    }
    fw.setSize(totalSize);
    }*/
}
