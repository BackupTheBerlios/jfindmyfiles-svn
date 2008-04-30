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
public class Ficheiro {

    private Long id;
    private Suporte disk;
    private long size;
    private boolean file;
    private boolean folder;
    private String name;
    private String absolutePath;
    private boolean hidden;
    private long lastModified;
    private List<Etiqueta> label;
    private List<Imagem> thumb;
    private List<Som> audioClip;
    private List<Video> videoClip;
    
    private List<Ficheiro> children;

    public Ficheiro() {
    }

    public Ficheiro(Suporte disk, long size, boolean file, boolean folder,
            String name, String absolutePath, boolean hidden, long lastModified) {

        this.disk = disk;
        this.size = size;
        this.file = file;
        this.folder = folder;
        this.name = name;
        this.absolutePath = absolutePath;
        this.hidden = hidden;
        this.lastModified = lastModified;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public List<Som> getAudioClip() {
        return audioClip;
    }

    public void setAudioClip(List<Som> audioClip) {
        this.audioClip = audioClip;
    }

    public Suporte getDisk() {
        return disk;
    }

    public void setDisk(Suporte disk) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public List<Etiqueta> getLabel() {
        return label;
    }

    public void setLabel(List<Etiqueta> label) {
        this.label = label;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<Imagem> getThumb() {
        return thumb;
    }

    public void setThumb(List<Imagem> thumb) {
        this.thumb = thumb;
    }

    public List<Video> getVideoClip() {
        return videoClip;
    }

    public void setVideoClip(List<Video> videoClip) {
        this.videoClip = videoClip;
    }
    
}
