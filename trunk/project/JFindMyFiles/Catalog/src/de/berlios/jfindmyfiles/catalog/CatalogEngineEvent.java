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
package de.berlios.jfindmyfiles.catalog;

import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.catalog.entities.Media;
import java.util.EventObject;

/**
 *
 * @author Knitter
 */
public class CatalogEngineEvent extends EventObject {

    private String name;
    private DiskGroup oldDiskGroup;
    private DiskGroup newDiskGroup;
    private Media oldDisk;
    private Media newDisk;
    private int groupId;

    public CatalogEngineEvent(CatalogEngine source, String name, DiskGroup oldDiskGroup,
            DiskGroup newDiskGroup, Media oldDisk, Media newDisk, int groupId) {
        
        super(source);
        this.name = name;
        this.oldDiskGroup = oldDiskGroup;
        this.newDiskGroup = newDiskGroup;
        this.oldDisk = oldDisk;
        this.newDisk = newDisk;
        this.groupId = groupId;               
    }
    
    public int getGroupId() {
        return groupId;
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
