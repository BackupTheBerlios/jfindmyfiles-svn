/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog.entities;

import java.util.Set;

/**
 *
 * @author ei10635
 */
public class Type {

    private long id;
    private String name;
    private Set media;

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    private Set getMedia() {
        return media;
    }

    private void setMedia(Set media) {
        this.media = media;
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Type)) {
            return false;
        }

        Type other = (Type) obj;
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
        int hash = 7;
        hash = 61 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
    public String toString() {
        return super.toString() + " " + id + " " + name;
    }
}
