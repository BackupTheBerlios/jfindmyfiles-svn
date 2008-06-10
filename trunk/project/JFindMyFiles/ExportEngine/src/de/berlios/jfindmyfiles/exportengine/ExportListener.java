/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.exportengine;

/**
 *
 * @author Knitter
 */
public interface ExportListener {
    
    void exportStarted();
    void exportFinished();
    void exportError();

}
