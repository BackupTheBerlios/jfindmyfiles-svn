/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.readingfiles;

import de.berlios.jfindmyfiles.catalog.entities.AudioData;
import de.berlios.jfindmyfiles.catalog.entities.ImageData;
import de.berlios.jfindmyfiles.catalog.entities.VideoData;


/**
 *
 * @author ei10635
 */
public interface Reader {
    
    AudioData importAudioClip();
    ImageData importImageThumb();
    VideoData importVideoPreview();
    String importTextDescription();
    String importCustomExtension();
    
    void register(/*TODO:something*/);
    void init();


}
