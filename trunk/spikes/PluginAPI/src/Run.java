
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
            /*Reader r = c.readerFor("AMC".toLowerCase());
            DescriptionValues d = r.read(new File("example.amc"));*/

            Reader r = c.readerFor("JPG".toLowerCase());
            final DescriptionValues d1 = r.read(new File("DSC00165.JPG"));
            final DescriptionValues d2 = r.read(new File("Canon PowerShot G2 (1).jpg"));

            System.out.println("<========================= DSC00165.JPG =====================>");
            System.out.println(d1.getDescription());
            System.out.println("<========================= Canon PowerShot G2 (1).jpg =====================>");
            System.out.println(d2.getDescription());

            System.out.println("###############################################");
            
            ImageDlg dialog = new ImageDlg(new javax.swing.JFrame(), true);
            dialog.setDisplayImage(d2.getImage(), d1.getImage());
            dialog.showVisible();

        } catch (MalformedURLException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
