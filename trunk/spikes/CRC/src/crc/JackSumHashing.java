/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crc;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import jonelo.jacksum.JacksumAPI;
import jonelo.jacksum.algorithm.AbstractChecksum;

/**
 *
 * @author Knitter
 */
public class JackSumHashing {

    public static void main(String[] args) {
        new JackSumHashing().runnMD5();
    }

    public void runnMD5() {
        AbstractChecksum checksum = null;
        try {
            // select an algorithm (md5 in this case)
            checksum = JacksumAPI.getChecksumInstance("md5");
        // On some systems you get a better performance for particular
        // algorithms if you select an alternate algorithm (see also option -A)
        // checksum = JacksumAPI.getChecksumInstance("md5", true);
        } catch (NoSuchAlgorithmException nsae) {
            // algorithm doesn't exist
            nsae.printStackTrace();
        }

        // updates the checksum with the content of a file
        try {
            checksum.readFile("C:\\System\\Working\\MyProjects\\JFindMyFiles\\docs\\TODO");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println(checksum);
    }
}
