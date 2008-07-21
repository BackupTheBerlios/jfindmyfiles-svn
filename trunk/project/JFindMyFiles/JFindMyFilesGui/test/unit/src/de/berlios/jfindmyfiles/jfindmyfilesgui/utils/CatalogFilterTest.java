/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.jfindmyfilesgui.utils;

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
public class CatalogFilterTest {

    public CatalogFilterTest() {
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
     * Test of accept method, of class CatalogFilter.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        File f = new File("");
        CatalogFilter instance = new CatalogFilter();
        boolean expResult = false;
        boolean result = instance.accept(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class CatalogFilter.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        CatalogFilter instance = new CatalogFilter();
        String expResult = "JFindMyFiles Catalog";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}