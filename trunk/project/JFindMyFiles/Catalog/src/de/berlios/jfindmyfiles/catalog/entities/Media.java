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
public class Media {
    
    private Long id;
    private Set files;
    private String description;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Set getFiles() {
        return files;
    }

    private void setFiles(Set files) {
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

}
