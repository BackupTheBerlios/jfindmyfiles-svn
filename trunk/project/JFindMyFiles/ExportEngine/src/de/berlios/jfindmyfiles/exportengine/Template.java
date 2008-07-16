/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.exportengine;

import java.io.File;
import java.util.Properties;

/**
 *
 * @author Knitter
 */
public class Template {

    public static final int SINGLE = 0;
    public static final int COMPLETE = 0;
    private String name;
    private String author;
    private String version;
    private int type;
    private File folder;
    private Properties properties;

    public Template(String name, String author, String version, int type, File folder, Properties properties) {
        if (type != SINGLE && type != COMPLETE) {
            throw new IllegalArgumentException("Invalid template type. " +
                    "Valid types are Template.SINGE and Template.COMPLETE");
        }
        this.name = name;
        this.author = author;
        this.version = version;
        this.type = type;
        this.folder = folder;
        this.properties = properties;
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

    public Properties getProperties() {
        return properties;
    }
    
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return name;
    }
}
