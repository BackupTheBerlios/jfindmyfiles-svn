/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog.entities;

import java.util.List;
import java.util.Set;

/**
 *
 * @author ei10635
 */
public class DiskGroup {

    private Long id;
    private String name;
    private String description;
    private Set<DiskGroup> groups;
    private List<FileWrapper> files;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private List<FileWrapper> getFiles() {
        return files;
    }

    private void setFiles(List<FileWrapper> files) {
        this.files = files;
    }

    private Set<DiskGroup> getGroups() {
        return groups;
    }

    private void setGroups(Set<DiskGroup> groups) {
        this.groups = groups;
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
}
