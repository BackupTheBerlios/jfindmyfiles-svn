/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog.model;

/**
 *
 * @author ei10635
 */
public class Medium {
    
    private Long id;
    private FileWrapper file;
    private String description;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FileWrapper getFile() {
        return file;
    }

    public void setFile(FileWrapper file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

}
