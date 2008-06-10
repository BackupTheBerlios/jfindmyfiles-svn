/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.readingfiles;

import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import java.io.File;

/**
 *
 * @author Knitter
 */
public class CompositeFile {
    
    public final File file;
    public final FileWrapper parent;
    
    public CompositeFile(File file, FileWrapper parent) {
        this.file = file;
        this.parent = parent;                
    }

}
