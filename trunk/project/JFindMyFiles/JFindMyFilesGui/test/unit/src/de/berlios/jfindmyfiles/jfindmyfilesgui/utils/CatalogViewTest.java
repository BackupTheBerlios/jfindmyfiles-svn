/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.jfindmyfilesgui.utils;

import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
public class CatalogViewTest {

    public CatalogViewTest() {
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
     * Test of getTypeDescription method, of class CatalogView.
     */
    @Test
    public void testGetTypeDescription() {
        System.out.println("getTypeDescription");
        File f = new File("");
        CatalogView instance = new CatalogView();
        String expResult = "JFindMyFile Catalog";
        String result = instance.getTypeDescription(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getIcon method, of class CatalogView.
     */
    @Test
    public void testGetIcon() {
        System.out.println("getIcon");
        File f = new File("");
        CatalogView instance = new CatalogView();
        Icon expResult = new ImageIcon();
        Icon result = instance.getIcon(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}