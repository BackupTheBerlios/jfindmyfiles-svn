/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crc;

import com.twmacinta.util.MD5;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Knitter
 */
public class MD5Hashing {
    
    public static void main(String[] args) {
        new MD5Hashing().runnMD5();
    }

    public void runnMD5() {
        try {
            System.out.println(MD5.asHex(MD5.getHash(new File("C:\\System\\Working\\MyProjects\\JFindMyFiles\\docs\\TODO"))));
            //System.out.println(MD5.getHash(new File("C:\\System\\Working\\MyProjects\\JFindMyFiles\\docs\\TODO")));
        } catch (IOException ex) {
            Logger.getLogger(MD5Hashing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
