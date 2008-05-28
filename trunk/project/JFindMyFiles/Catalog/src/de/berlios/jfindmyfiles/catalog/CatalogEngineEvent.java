/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog;

import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.catalog.entities.Media;

/**
 *
 * @author Knitter
 */
public class CatalogEngineEvent {

    private String name;
    private DiskGroup oldDiskGroup;
    private DiskGroup newDiskGroup;
    private Media oldDisk;
    private Media newDisk;

    public CatalogEngineEvent(String name, DiskGroup oldDiskGroup,
            DiskGroup newDiskGroup, Media oldDisk, Media newDisk) {
        
        this.name = name;
        this.oldDiskGroup = oldDiskGroup;
        this.newDiskGroup = newDiskGroup;
        this.oldDisk = oldDisk;
        this.newDisk = newDisk;
    }

    public String getName() {
        return name;
    }

    public Media getNewDisk() {
        return newDisk;
    }

    public DiskGroup getNewDiskGroup() {
        return newDiskGroup;
    }

    public Media getOldDisk() {
        return oldDisk;
    }

    public DiskGroup getOldDiskGroup() {
        return oldDiskGroup;
    }
}
