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
package de.berlios.jfindmyfiles.readingfiles;

import de.berlios.jfindmyfiles.catalog.entities.Media;
import java.util.EventObject;

/**
 * Reading event for all listeners.
 */
public class ReadingEvent extends EventObject {
    
    private String currentFileName;
    private Media media;
    
    public ReadingEvent(MediaReader source, String currentFileName, Media media) {
        super(source);
        this.currentFileName = currentFileName;
        this.media = media;
    }
    
    /**
     * Gets the name of the current file.
     * 
     * @return String representing the file being read or null if this event 
     * does not represent a file being read.
     */
    public String getCurrentFileName() {
        return currentFileName;
    }
    
    /**
     * Gets the media being scanned.
     * 
     * @return Media being scanned.
     */
    public Media getMedia() {
        return media;
    }

}
