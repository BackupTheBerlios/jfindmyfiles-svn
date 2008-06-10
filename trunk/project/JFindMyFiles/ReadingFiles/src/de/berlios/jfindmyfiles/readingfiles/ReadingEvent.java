/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.readingfiles;

import de.berlios.jfindmyfiles.catalog.entities.Media;
import java.util.EventObject;

/**
 *
 * @author Knitter
 */
public class ReadingEvent extends EventObject {
    
    private String currentFileName;
    private Media media;
    
    public ReadingEvent(MediaReader source, String currentFileName, Media media) {
        super(source);
        this.currentFileName = currentFileName;
        this.media = media;
    }
    
    public String getCurrentFileName() {
        return currentFileName;
    }
    
    public Media getMedia() {
        return media;
    }

}
