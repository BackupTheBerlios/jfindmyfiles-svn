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
package de.berlios.jfindmyfiles.readingfiles.pluginapi;

import java.io.File;

public interface Reader {
    
    /**
     * Gets the name of this plugin.
     * A plugin name needs not be unique as it is used only for the user 
     * interface.
     * 
     * @return String representing the name of the plugin.
     */
    String getName();
    
    /**
     * Gets the plugin's author name.
     * 
     * @return String with the author name.
     */
    String getAuthor();
    
    /**
     * Gets the file extension for the files this plugin accepts.
     * 
     * Though the file extension is not important in all operating systems, 
     * currently it is the identifying part of a plugin. Therefor it is not 
     * possible to have two different plugins for the same extension.
     * 
     * If the plugin folder has two plugins for the same file extension, the 
     * last one to be loaded will override the any other that exists.
     * 
     * @return String representing an extension.
     */
    String pluginFor();
    
    /**
     * This method starts the plugin on the given file. After processing the 
     * file, the plugin should return a <code>DescriptionValues</code> object 
     * with the data collected by the plugin.
     * 
     * @param file the file to process.
     * @return DescriptionValues with all the information gathered by this plugin.
     */
    DescriptionValues read(File file);
    
    /**
     * Provides a way to determine if this plugin is active.
     * This is just a glue method, plugin implementation should not make this 
     * method return always <code>true</code> or <code>false</code>.
     * 
     * As plugin classes are not serializable and will not be serialized, there 
     * is now way for the implementation to know if it is active or not. It is 
     * up to the application, and the <code>PluginCache</code> class to know if 
     * a given plugin is active or inactive.
     * 
     * An example implementation would be:
     * <pre>
     *     <code>
     *     private boolean active;
     * 
     *     (...)
     * 
     *     public boolean isActive() {
     *         return active;
     *     }
     *     </code>
     * </pre>
     * Do not worry about how the attribute is changed, but make sure you also 
     * implement the <code>setActive</code> method.
     * 
     * @return boolean indicating the state of this plugin.
     * 
     * @see setActive
     */
    boolean isActive();
    
    /**
     * Sets the state of this plugin.
     * This method must be implemented in a way that the <code>isActive</code> 
     * method  properly reflects the state of the object.
     */
    void setActive(boolean active);
}
