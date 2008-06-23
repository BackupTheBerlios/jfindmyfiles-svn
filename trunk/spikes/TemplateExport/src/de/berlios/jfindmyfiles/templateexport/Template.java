/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.templateexport;

import java.io.File;

/**
 *
 * @author Knitter
 */
public class Template {
    
    private String name;
    private String author;
    private String version;
    private int type;
    private File folder;
    
    public Template(String name, String author, String version, int type, File folder) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.type = type;
        this.folder = folder;
    }
    
    public File getFolder() {
        return folder;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }
    
    public String toString() {
        return name;
    }

}
