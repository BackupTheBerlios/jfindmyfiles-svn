/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog.model;

/**
 *
 * @author ei10635
 */
public class Tipo {
    
    private Long id;
    private String type;
    
    public Tipo() {
    }
    
    public Tipo(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
