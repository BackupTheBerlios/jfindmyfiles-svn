package pluginapi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Knitter
 */
public class AntMovieCatalogerPlugin implements Reader {

    private static final String NAME = "Ant Movie Cataloger Reader";
    private static final String AUTHOR = "Sergio Lopes";//TODO: provide correct author info
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
        byte[] sixtyfivebytes = new byte[65];
        try {
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));

            //Reading AMC file version
            bin.read(sixtyfivebytes);
            StringBuffer buff = new StringBuffer();
            buff.append("AMC Version format: ");
            for (int z = 0; z < 65; z++) {
                buff.append(new Character((char) ((int) sixtyfivebytes[z])));
            }
            
            buff.append("\nOwner: " + readString(bin));//owner
            buff.append("\nSite: " + readString(bin));//site
            buff.append("\nE-Mail: " + readString(bin));//mail
            buff.append("\n\n" + readString(bin));//description

            return new DescriptionValues(buff.toString(), null, null, null, null);
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    //TODO: this needs not be a separated method
    private String readString(BufferedInputStream bin) throws IOException {
        byte[] fourbytes = new byte[4];
        StringBuffer buff = new StringBuffer();
        int size = 0;
        byte[] stringtemp;

        bin.read(fourbytes);
        size = ((fourbytes[0] & 0xff)) | ((fourbytes[1] & 0xff) << 8) | ((fourbytes[2] & 0xff) << 16) | ((fourbytes[3] & 0xff) << 24);
        stringtemp = new byte[size];
        bin.read(stringtemp);

        buff = new StringBuffer();
        for (int y = 0; y < size; y++) {
            buff.append(new Character((char) ((int) stringtemp[y])));
        }
        return buff.toString();
    }
}
