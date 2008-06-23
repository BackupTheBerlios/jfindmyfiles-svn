/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.readingfiles.plugins;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import de.berlios.jfindmyfiles.readingfiles.pluginapi.DescriptionValues;
import de.berlios.jfindmyfiles.readingfiles.pluginapi.Reader;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Knitter
 */
public class JPGPlugin implements Reader {

    private static final String NAME = "JPG Reader";
    private static final String AUTHOR = "JFindMyFiles";
    private static final String EXT = "jpg";
    private ImageIcon ic;
    private BufferedImage bimg;
    private ByteArrayOutputStream bas;
    private static final Logger logger = Logger.getLogger(JPGPlugin.class.getName());

    public JPGPlugin() {
        ic = new ImageIcon();
        bimg = new BufferedImage(100, 75, BufferedImage.TYPE_INT_RGB);
        bas = new ByteArrayOutputStream();
    }

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
        try {
            StringBuffer buf = new StringBuffer();
            Metadata metadata = JpegMetadataReader.readMetadata(file);

            //iterate through metadata directories
            Iterator directories = metadata.getDirectoryIterator();
            Tag tag;
            while (directories.hasNext()) {
                Directory directory = (Directory) directories.next();
                // iterate through tags and print to System.out  
                Iterator tags = directory.getTagIterator();
                while (tags.hasNext()) {
                    tag = (Tag) tags.next();
                    if (tag.getTagName().equals("Make") ||
                            tag.getTagName().equals("Model") ||
                            tag.getTagName().equals("Date/Time") ||
                            tag.getTagName().equals("Exposure Time") ||
                            tag.getTagName().equals("F-Number") ||
                            tag.getTagName().equals("ISO Speed Ratings") ||
                            tag.getTagName().equals("Flash") ||
                            tag.getTagName().equals("Exif Image Width") ||
                            tag.getTagName().equals("Exif Image Height") ||
                            tag.getTagName().equals("Exposure Mode") ||
                            tag.getTagName().equals("White Balance") ||
                            tag.getTagName().equals("Digital Zoom Ratio") ||
                            tag.getTagName().equals("Scene Capture Type") ||
                            tag.getTagName().equals(" Exif Version")) {

                        buf.append(tag.toString() + "\n");
                    }
                }
            }

            Image img = Toolkit.getDefaultToolkit().createImage(file.getAbsolutePath()).
                    getScaledInstance(100, 75, Image.SCALE_FAST);

            //NOTE: Another ugly hack!
            ic.setImage(img);
            bimg.getGraphics().dispose();
            bimg.createGraphics().drawImage(img, 0, 0, null);

            bas.reset();
            ImageIO.write(bimg, "jpeg", bas);
            byte[] data = bas.toByteArray();
            bas.flush();

            return new DescriptionValues(buf.toString(), data, null, null, null);
        } catch (JpegProcessingException ex) {
            logger.log(Level.WARNING, "JPGPlugin failed", ex);
        } catch (IOException ex) {
            logger.log(Level.WARNING, "JPGPlugin failed", ex);
        }
        return new DescriptionValues();
    }
}
