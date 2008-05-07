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
public class FileWrapper {
    
    private Long id;
    private Media disk;
    private long size;
    private boolean file;
    private boolean folder;
    private String name;
    private String absolutePath;
    private boolean hidden;
    private long lastModified;
    private Set children;
    private FileWrapper parent;
    private String description;
    private ImageData image;
    private VideoData video;
    private AudioData audio;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
    
    public AudioData getAudio() {
        return audio;
    }
    
    public void setAudio(AudioData audio) {
        this.audio = audio;
    }

    private Set getChildren() {
        return children;
    }

    private void setChildren(Set children) {
        this.children = children;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Media getDisk() {
        return disk;
    }

    public void setDisk(Media disk) {
        this.disk = disk;
    }

    public boolean isFile() {
        return file;
    }

    public void setFile(boolean file) {
        this.file = file;
    }

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
    
    public ImageData getImage() {
        return image;
    }

    public void setImage(ImageData image) {
        this.image = image;
    }
    
    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileWrapper getParent() {
        return parent;
    }

    public void setParent(FileWrapper parent) {
        this.parent = parent;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
    
    public VideoData getVideo() {
        return video;
    }
    
    public void setVideo(VideoData video) {
        this.video = video;
    }
}
