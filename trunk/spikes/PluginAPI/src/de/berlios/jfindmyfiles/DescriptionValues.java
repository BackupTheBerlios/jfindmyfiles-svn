/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles;

import de.berlios.jfindmyfiles.catalog.entities.AudioData;
import de.berlios.jfindmyfiles.catalog.entities.ImageData;
import de.berlios.jfindmyfiles.catalog.entities.VideoData;
import javax.swing.ImageIcon;

/**
 *
 * @author ei10635
 */
public interface DescriptionValues {
    
    String getTextDescription();
    ImageIcon getIcon();
    AudioData getAudioData();
    VideoData getVideoData();
    ImageData getThumbnail();
}
