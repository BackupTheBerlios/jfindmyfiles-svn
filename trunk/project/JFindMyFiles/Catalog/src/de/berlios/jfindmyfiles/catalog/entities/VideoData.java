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

/**
 *
 * @author ei10635
 */
public class VideoData {

    private Long id;
    private FileWrapper owner;
    private byte[] data;

    public VideoData() {
        //DO NOTHING
    }

    public VideoData(FileWrapper owner, byte[] data) {
        this.owner = owner;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public FileWrapper getOwner() {
        return owner;
    }

    public void setOwner(FileWrapper owner) {
        this.owner = owner;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
        if(obj == null) {
            return false;
        }
        
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VideoData)) {
            return false;
        }
        VideoData other = (VideoData) obj;
        return owner.equals(other.owner) && data.length == other.data.length && data == other.data;

    }

    /**
     * HashCode for this object, excluding the id field.
     * @return integer representing the hashcode
     * 
     * @see equals
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.owner != null ? this.owner.hashCode() : 0);
        hash = 89 * hash + (this.data != null ? this.data.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return super.toString() + " " + id + " " + data.length;
    }
}
