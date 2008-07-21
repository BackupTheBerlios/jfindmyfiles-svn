/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.readingfiles.utils;

import de.berlios.jfindmyfiles.catalog.CatalogConstants;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrador
 */
public class ReadingUtilsTest {

    public ReadingUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findExtension method, of class ReadingUtils.
     */
    @Test
    public void testFindExtension() {
        System.out.println("findExtension");
        String filename = "dados.txt";
        String expResult = "txt";
        String result = ReadingUtils.findExtension(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSHA1HashString method, of class ReadingUtils.
     */
    @Test
    public void testCalculateSHA1HashString() {
        System.out.println("calculateSHA1HashString");
        File file = new File("");
        String expResult = "dasda";
        String result = ReadingUtils.calculateSHA1HashString(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findFileType method, of class ReadingUtils.
     */
    @Test
    public void testFindFileType() {
        System.out.println("findFileType");
        String absolutepath = "d:\\";
        int expResult = CatalogConstants.DVDROM;
        int result = ReadingUtils.findFileType(absolutepath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of stripFileExtension method, of class ReadingUtils.
     */
    @Test
    public void testStripFileExtension() {
        System.out.println("stripFileExtension");
        String filename = "teste.txt";
        String expResult = "teste";
        String result = ReadingUtils.stripFileExtension(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}