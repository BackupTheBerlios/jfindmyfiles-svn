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
public class AntMovieCatalogerPlugin implements Reader {
    
    private static final String NAME = "Ant Movie Cataloger Reader";
    private static final String AUTHOR = "JFindMyFiles";
    private static final String EXT = "amc";

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
        return new DescriptionValues();
    }

}
