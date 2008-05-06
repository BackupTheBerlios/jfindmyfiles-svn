/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog.model;

import java.util.List;

/**
 *
 * @author ei10635
 */
public class DiskGroup {

    private Long id;
    private String name;
    private String description;
    private List<DiskGroup> groups;
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

    private List<DiskGroup> getGroups() {
        return groups;
    }

    private void setGroups(List<DiskGroup> groups) {
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
