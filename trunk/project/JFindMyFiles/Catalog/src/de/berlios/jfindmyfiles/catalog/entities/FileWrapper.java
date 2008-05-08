/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog.entities;

import java.util.LinkedHashSet;
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
    private Set children = new LinkedHashSet();
    private FileWrapper parent;
    private String description;
    private ImageData image;
    private VideoData video;
    private AudioData audio;

    public FileWrapper() {
        //do nothing
    }

    public FileWrapper(String name, String absolutePath, long size,
            boolean file, boolean hidden, long lastModified, Media disk,
            FileWrapper parent) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.size = size;
        this.folder = !(this.file = file);
        this.hidden = hidden;
        this.lastModified = lastModified;
        this.disk = disk;
        this.parent = parent;
    }

    public void addChild(FileWrapper child) {
        if (children == null) {
            children = new LinkedHashSet();
        }

        children.add(child);

    }

    public void removeChild(FileWrapper child) {
        if (children != null) {
            children.remove(child);
        }
    }

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
        if(this == obj) {
            return true;
        }
        
        if (!(obj instanceof FileWrapper)) {
            return false;
        }

        FileWrapper other = (FileWrapper) obj;
        return size == other.size && name.equals(other.name) && absolutePath.equals(other.absolutePath);
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
        int hash = 5;
        hash = 11 * hash + (int) (this.size ^ (this.size >>> 32));
        hash = 11 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 11 * hash + (this.absolutePath != null ? this.absolutePath.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + absolutePath;
    }
}
