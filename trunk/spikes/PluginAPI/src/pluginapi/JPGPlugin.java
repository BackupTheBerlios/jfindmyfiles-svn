/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pluginapi;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
            bimg.createGraphics().drawImage(img, 0, 0, null);

            bas.reset();
            ImageIO.write(bimg, "jpeg", bas);
            byte[] data = bas.toByteArray();
            bas.flush();
            bimg.getGraphics().dispose();

            return new DescriptionValues(buf.toString(), data, null, null, null);
        } catch (JpegProcessingException ex) {
            //TODO: logging
        } catch (IOException ex) {
            //TODO: logging
            //Logger.getLogger(JPGPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DescriptionValues();
    }
    /*Make|[Exif] Make - Sony Ericsson
    T-Name: Model|[Exif] Model - K610i
    T-Name: X Resolution|[Exif] X Resolution - 72 dots per inch
    T-Name: Y Resolution|[Exif] Y Resolution - 72 dots per inch
    T-Name: Resolution Unit|[Exif] Resolution Unit - Inch
    T-Name: Date/Time|[Exif] Date/Time - 2008:06:06 01:13:50
    T-Name: Exposure Time|[Exif] Exposure Time - 0.04 sec
    T-Name: F-Number|[Exif] F-Number - F2,8
    T-Name: ISO Speed Ratings|[Exif] ISO Speed Ratings - 125
    T-Name: Exif Version|[Exif] Exif Version - 2.20
    T-Name: Flash|[Exif] Flash - Flash did not fire, auto
    T-Name: Exif Image Width|[Exif] Exif Image Width - 1600 pixels
    T-Name: Exif Image Height|[Exif] Exif Image Height - 1200 pixels
    T-Name: Exposure Mode|[Exif] Exposure Mode - Auto exposure
    T-Name: White Balance|[Exif] White Balance - Auto white balance
    T-Name: Digital Zoom Ratio|[Exif] Digital Zoom Ratio - Digital zoom not used.
    T-Name: Scene Capture Type|[Exif] Scene Capture Type - Standard
     */
}
