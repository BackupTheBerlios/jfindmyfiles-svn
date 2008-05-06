/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog.model;

/**
 *
 * @author ei10635
 */
public class VideoData {

    private Long id;
    private FileWrapper owner;

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
}
