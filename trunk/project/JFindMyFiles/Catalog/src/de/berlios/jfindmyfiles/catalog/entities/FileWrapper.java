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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ei10635
 */
public class FileWrapper {

    private Long id;
    //Base attributes
    private String name;
    private String absolutePath;
    private long lastModified;
    private String description;
    private boolean hidden;
    private long size;
    private String extension;
    private ImageData image;
    private VideoData video;
    private AudioData audio;
    private String sha1;
    //Helpers    
    private boolean file;
    private boolean folder;
    //Attributes from relationships    
    private Set children = new LinkedHashSet();
    private FileWrapper parent;
    private Media disk;

    public FileWrapper() {
        //DO NOTHING
    }

    /**
     * 
     * @param name
     * @param absolutePath
     * @param size
     * @param file
     * @param hidden
     * @param lastModified
     * @param disk
     * @param parent
     */
    public FileWrapper(String name, String absolutePath, long size,
            boolean file, boolean hidden, long lastModified, Media disk,
            FileWrapper parent, String sha1, String extension, String description) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.size = size;
        this.folder = !(this.file = file);
        this.hidden = hidden;
        this.lastModified = lastModified;
        this.disk = disk;
        this.parent = parent;
        this.sha1 = sha1;
        this.extension = extension;
        this.description = description;
    }

    /**
     * 
     * @param child
     */
    @SuppressWarnings("unchecked")
    public void addChild(FileWrapper child) {
        if (children == null) {
            children = new LinkedHashSet();
        }
        children.add(child);
    }

    /**
     * 
     * @param child
     */
    public void removeChild(FileWrapper child) {
        if (children != null) {
            children.remove(child);
        }
    }

    public List<FileWrapper> getChildrenList() {
        ArrayList<FileWrapper> temp = new ArrayList<FileWrapper>(children.size());
        for (Object o : children) {
            temp.add((FileWrapper) o);
        }
        return temp;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FileWrapper)) {
            return false;
        }

        FileWrapper other = (FileWrapper) obj;
        return size == other.size && name.equals(other.name) && absolutePath.equals(other.absolutePath);
    }

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
        return name;
    }
}
