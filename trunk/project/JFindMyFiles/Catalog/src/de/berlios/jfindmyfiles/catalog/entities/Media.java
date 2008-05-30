/**
 *  Copyright (C) 2008  Patrícia Monteiro e Sérgio Lopes
 *
 *  This file is part of JFindMyFiles.
 *
 *  JFindMyFiles is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFindMyFiles is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFindMyFiles.  If not, see 
 * <http://www.gnu.org/licenses/gpl.html>.
 */
package de.berlios.jfindmyfiles.catalog.entities;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author ei10635
 */
public class Media {

    private Long id;
    //Base attributes
    private String name;
    private long capacity;
    private long lastModified;
    private String description;
    private long freeSpace;
    private String location;
    //Attributes from relationships
    private Set files;
    private Set labels;
    private DiskGroup group;
    private Set loans;
    private Type type;

    public Media() {
        //DO NOTHING
    }

    public Media(String name, long capacity, long lastModified,
            String description, long freeSpace, String location,
            DiskGroup group, Type type) {

        this.name = name;
        this.capacity = capacity;
        this.lastModified = lastModified;
        this.description = description;
        this.freeSpace = freeSpace;
        this.location = location;
        this.group = group;
        this.type = type;
    }

    public void addFile(FileWrapper file) {
        if (files == null) {
            files = new LinkedHashSet();
        }
        files.add(files);
    }

    public void removeFile(FileWrapper file) {
        if (files != null) {
            files.remove(file);
        }
    }

    public void addLabel(Label label) {
        if (labels == null) {
            labels = new LinkedHashSet();
        }
        labels.add(label);
    }

    public void removeLabel(Label label) {
        if (labels != null) {
            labels.remove(label);
        }
    }
    
    public void addLoan(Loan loan) {
        if (loans == null) {
            loans = new LinkedHashSet();
        }
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        if (loans != null) {
            loans.remove(loan);
        }
    }

    public void increaseCapacity(long amount) {
        capacity += amount;
    }

    public void increaseFreeSpace(long amount) {
        freeSpace += amount;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

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

    public long getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(long freeSpace) {
        this.freeSpace = freeSpace;
    }

    public DiskGroup getGroup() {
        return group;
    }

    public void setGroup(DiskGroup group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private Set getLabels() {
        return labels;
    }

    private void setLabels(Set labels) {
        this.labels = labels;
    }
    
    private Set getLoans() {
        return loans;
    }
    
    private void setLoans(Set loans) {
        this.loans = loans;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Media)) {
            return false;
        }

        Media other = (Media) obj;
        return name.equals(other.name) && description.equals(other.description) && type.equals(other.type);
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
        int hash = 7;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 31 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 31 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
}
