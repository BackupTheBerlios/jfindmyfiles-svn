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

import javax.swing.ImageIcon;

/**
 * Utility class that aggregates the information extracted by a Reader.
 */
public class DescriptionValues {

    private String description;
    private byte[] image;
    private byte[] audio;
    private byte[] video;
    private ImageIcon icon;
    
    /**
     * Default constructor creating empty attributes.
     */
    public DescriptionValues() {
        this("", null, null, null, null);
    }

    public DescriptionValues(String description, byte[] image, byte[] audio, byte[] video, ImageIcon icon) {
        this.description = description;
        this.image = image;
        this.audio = audio;
        this.video = video;
        this.icon = icon;
    }

    /**
     * Gets audio data.
     * 
     * @return byte[] or null if this plugin doesn't provide audio data.
     */
    public byte[] getAudio() {
        return audio;
    }

    /**
     * Gets the text description
     * 
     * @return String with the description or an empty string if no textual 
     * description was gathered.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the custom icon for this file type.
     * 
     * @return ImageIcon representing the icon or null if no icon is available.
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * Gets image data.
     * 
     * @return byte[] or null if this plugin doesn't provide image data.
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Gets video data.
     * 
     * @return byte[] or null if this plugin doesn't provide video data.
     */
    public byte[] getVideo() {
        return video;
    }
}
