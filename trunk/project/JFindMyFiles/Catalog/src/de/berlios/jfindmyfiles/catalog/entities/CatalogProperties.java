/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog.entities;

/**
 *
 * @author knitter
 */
public class CatalogProperties {
    
    private Long id;
    private String name;

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
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CatalogProperties)) {
            return false;
        }
        
        return this.name.equals(((CatalogProperties)obj).name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return name;
    }

}
