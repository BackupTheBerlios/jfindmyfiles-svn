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
    
    void catalogCreated(CatalogEngineEvent evt);
    void catalogOpened(CatalogEngineEvent evt);
    void catalogClosed(CatalogEngineEvent evt);
    void diskGroupAdded(CatalogEngineEvent evt);
    void diskGroupRemoved(CatalogEngineEvent evt);
    void diskGroupRenamed(CatalogEngineEvent evt);
    void diskAdded(CatalogEngineEvent evt);
    void diskRemoved(CatalogEngineEvent evt);
}
