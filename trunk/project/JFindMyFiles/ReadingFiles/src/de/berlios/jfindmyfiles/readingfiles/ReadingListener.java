/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.readingfiles;

/**
 *
 * @author Knitter
 */
public interface ReadingListener {
    
    void readingFile(ReadingEvent evt);
    void readingStopped(ReadingEvent evt);
    void readingAborted(ReadingEvent evt);

}
