/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.readingfiles;

import java.util.EventObject;

/**
 *
 * @author Knitter
 */
public class ReadingEvent extends EventObject {
    
    private String currentFileName;
    
    public ReadingEvent(MediaReader source, String currentFileName) {
        super(source);
        this.currentFileName = currentFileName;
    }
    
    public String getCurrentFileName() {
        return currentFileName;
    }

}
