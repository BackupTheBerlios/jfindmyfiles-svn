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
public class Label {

    private Long id;
    private String name;
    private Set media;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
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
    
    public String toString() {
        return id + " " + name;
    }
}