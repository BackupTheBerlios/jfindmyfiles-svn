/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog.entities;

/**
 *
 * @author ei10635
 */
public class ImageData {

    private Long id;
    private FileWrapper owner;
    private byte[] data;

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
        if(this == obj) {
            return true;
        }
        
        if (!(obj instanceof ImageData)) {
            return false;
        }

        ImageData other = (ImageData) obj;
        return owner.equals(other.owner) && data.length == other.data.length && data == data;
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
        hash = 97 * hash + (this.owner != null ? this.owner.hashCode() : 0);
        hash = 97 * hash + (this.data != null ? this.data.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return super.toString() + " " + data.length;
    }
}
