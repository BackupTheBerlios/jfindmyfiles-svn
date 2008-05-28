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
package de.berlios.jfindmyfiles.catalog.entities;

import java.util.Set;

/**
 *
 * @author ei10635
 */
public class DiskGroup {

    private Long id;
    //Base attributes
    private String name;
    private String description;
    private long capacity;
    //Attributes from relationships
    private Set groups;
    private DiskGroup parent;

    public DiskGroup() {
        //DO NOTHING
    }

    public DiskGroup(String name, String description, long capacity) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }
    
    public void increaseCapacity(long amount) {
        capacity += amount;
    }
    
    public long getCapacity() {
        return capacity;
    }
    
    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Set getGroups() {
        return groups;
    }

    private void setGroups(Set groups) {
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiskGroup getParent() {
        return parent;
    }

    public void setParent(DiskGroup parent) {
        this.parent = parent;
    }

    /**
     * Equals method doesn't take into account the id of the object. Hibernate 
     * only gives an id to a new object when that object is persisted, it is 
     * therefor not possible to use the id attribute.
     * 
     * A <em>Bussines Key</em> is used instead.
     * @param obj the object to compare
     * @return true if both objects are equal under the <em>Bussines Key</em>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DiskGroup)) {
            return false;
        }

        DiskGroup other = (DiskGroup) obj;
        return name.equals(other.name) && description.equals(other.description) && parent.equals(other.parent);
    }

    //TODO: link for the equals method
    /**
     * HashCode for this object, excluding the id field.
     * @return integer representing the hashcode
     * 
     * @see equals
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.description != null ? this.description.hashCode()
                : 0);
        hash = 83 * hash + (this.parent != null ? this.parent.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
}
