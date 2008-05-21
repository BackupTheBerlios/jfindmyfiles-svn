/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog;

/**
 *
 * @author knitter
 */
public interface CatalogEngineListener {
    
    void catalogCreated();
    void catalogOpened();
    void diskGroupAdded();
    void diskGroupRemoved();
    void diskGroupRenamed();
    void diskAdded();
    void diskRemoved();
}
