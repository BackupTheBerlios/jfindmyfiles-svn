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
}
