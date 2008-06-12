
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pluginapi.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Knitter
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            PluginCache c = new PluginCache();
            Reader r = c.readerFor("AMC".toLowerCase());
            DescriptionValues d = r.read(new File("example.amc"));
            System.out.println(d.getDescription());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
