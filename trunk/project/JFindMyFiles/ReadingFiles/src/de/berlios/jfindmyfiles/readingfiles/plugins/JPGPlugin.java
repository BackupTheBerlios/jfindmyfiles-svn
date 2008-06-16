/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.readingfiles.plugins;

import de.berlios.jfindmyfiles.readingfiles.pluginapi.DescriptionValues;
import de.berlios.jfindmyfiles.readingfiles.pluginapi.Reader;
import java.io.File;

/**
 *
 * @author Knitter
 */
public class JPGPlugin implements Reader {

    private static final String NAME = "JPG Reader";
    private static final String AUTHOR = "Sergio Lopes";//TODO: provide correct author info
    private static final String EXT = "jpg";
    
    public String getName() {
        return NAME;
    }

    public String getAuthor() {
        return AUTHOR;
    }

    public String pluginFor() {
        return EXT;
    }

    public DescriptionValues read(File file) {
        //TODO: create plugin
        return new DescriptionValues();
    }

}
