/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package executarficheiro;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Knitter
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(Desktop.isDesktopSupported()) {
            Desktop d = Desktop.getDesktop();
            try {
                d.open(new File("C:\\System\\Working\\MyProjects\\JFindMyFiles\\docs\\Notas do relatorio.txt"));
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
