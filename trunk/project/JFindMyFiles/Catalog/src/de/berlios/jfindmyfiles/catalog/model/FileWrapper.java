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
public class FileWrapper {
    
    private Medium disk;
    private long size;
    private boolean file;
    private boolean folder;
    private String name;
    private String absolutePath;
    private boolean hidden;
    private long lastModified;
    private List<Label> label;
    private List<FileWrapper> children;
    private FileWrapper parent;
    private String description;    
}
