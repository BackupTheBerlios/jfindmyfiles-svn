/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog.entities;

import java.util.Date;

/**
 *
 * @author knitter
 */
public class CatalogProperties {

    private Long id;
    private String name;
    private String description;
    private long totalSize;
    private int diskNumber;
    private long totalFiles;
    private long totalFolders;
    private Date creationDate;

    public CatalogProperties() {
        //DO NOTHING
    }

    //TODO: confirm if it's really necessary
    public CatalogProperties(String name, String description, long totalSize,
            int diskNumber, long totalFiles, long totalFolders,
            Date creationDate) {

        this.name = name;
        this.description = description;
        this.totalSize = totalSize;
        this.diskNumber = diskNumber;
        this.totalFiles = totalFiles;
        this.totalFolders = totalFolders;
        this.creationDate = creationDate;
    }
    
    public void increaseDiskNumber(int amount) {
        diskNumber += amount;
    }
    
    public void increaseTotalFiles(int amount) {
        totalFiles += totalFiles;
    }
    
    public void increaseTotalFolders(int amount) {
        totalFolders += amount;
    }

    public void increaseTotalSize(long amount) {
        totalSize += amount;
    }   
    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getDiskNumber() {
        return diskNumber;
    }

    public void setDiskNumber(int diskNumber) {
        this.diskNumber = diskNumber;
    }

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

    
    public long getTotalFiles() {
        return totalFiles;
    }

    public void setTotalFiles(long totalFiles) {
        this.totalFiles = totalFiles;
    }
        
    public long getTotalFolders() {
        return totalFolders;
    }

    public void setTotalFolders(long totalFolders) {
        this.totalFolders = totalFolders;
    }
    
    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
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
        if(obj == null) {
            return false;
        }

        if (!(obj instanceof CatalogProperties)) {
            return false;
        }

        CatalogProperties other = (CatalogProperties) obj;
        //All attributes are ignored, except for the name
        return this.name.equals(other.name);
    }

    /**
     * HashCode for this object, excluding the id field.
     * @return integer representing the hashcode
     * 
     * @see equals
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 71 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 71 * hash + (int) (this.totalSize ^ (this.totalSize >>> 32));
        hash = 71 * hash + this.diskNumber;
        hash = 71 * hash + (int) (this.totalFiles ^ (this.totalFiles >>> 32));
        hash = 71 * hash + (int) (this.totalFolders ^ (this.totalFolders >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
}
