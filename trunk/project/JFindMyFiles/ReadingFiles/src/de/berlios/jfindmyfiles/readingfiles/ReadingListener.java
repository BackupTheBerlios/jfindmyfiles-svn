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

import java.util.EventListener;

/**
 * Provides reading events
 */
public interface ReadingListener extends EventListener {
    
    /**
     * Reading has started.
     * 
     * @param evt reading event.
     */
    void readingStarted(ReadingEvent evt);
    
    /**
     * Identifies the file currently being read.
     * 
     * @param evt reading event.
     */
    void readingFile(ReadingEvent evt);
    
    /**
     * Reading has stopped normally.
     * 
     * @param evt reading event.
     */
    void readingStopped(ReadingEvent evt);
    
    /**
     * The reading was aborted.
     * 
     * @param evt reading event.
     */
    void readingAborted(ReadingEvent evt);

}
