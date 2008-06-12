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

public class DescriptionValues {

    private String description;
    private byte[] image;
    private byte[] audio;
    private byte[] video;
    private ImageIcon icon;
    
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

    public byte[] getAudio() {
        return audio;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public byte[] getImage() {
        return image;
    }

    public byte[] getVideo() {
        return video;
    }
}
