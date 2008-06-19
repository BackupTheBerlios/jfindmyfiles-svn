/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog.entities;

/**
 *
 * @author Knitter
 */
public class CustomFileIcon {
    
    private Long id;
    private byte[] icon;
    private String extension;
    
    public CustomFileIcon() {
        //DO NOTHING
    }
    
    public CustomFileIcon(byte[] icon, String extension) {
        this.icon = new byte[icon.length];
        for(int z = icon.length; z-- > 0;) {
            this.icon[z] = icon[z];
        }
        this.extension = extension;
    }
    
    public Long getId() {
        return id;
    }
    
    private void setId(Long id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
    
    private boolean compareByteArray(byte[] other) {
        if(icon == other) {
            return true;
        }
        
        if(icon.length != other.length) {
            return false;
        }
        for(int z = icon.length; z-- > 0;) {
            if(icon[z] != other[z]) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        
        if(!(obj instanceof CustomFileIcon)) {
            return false;
        }
        
        CustomFileIcon other = (CustomFileIcon)obj;
        return extension.equals(other.extension) && compareByteArray(other.icon);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.icon != null ? this.icon.hashCode() : 0);
        hash = 53 * hash + (this.extension != null ? this.extension.hashCode() : 0);
        return hash;
    }

}
