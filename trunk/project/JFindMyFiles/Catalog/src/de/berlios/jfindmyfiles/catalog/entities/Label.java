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
public class Label {

    private Long id;
    //Base attributes
    private String name;

    public Label() {
        //DO NOTHING
    }

    public Label(String name) {
        this.name = name;
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

        if (!(obj instanceof Label)) {
            return false;
        }

        Label other = (Label) obj;
        return name.equals(other.name);
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
        int hash = 5;
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return super.toString() + " " + id + " " + name;
    }
}
