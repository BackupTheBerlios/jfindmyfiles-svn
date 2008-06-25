/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog;

import org.openide.util.NbBundle;

/**
 *
 * @author Knitter
 */
public abstract class CatalogConstants {

    public static final int HDD = 0;
    public static final int FLOPPY = 1;
    public static final int CDROM = 2;
    public static final int DVDROM = 3;
    public static final int FOLDER = 4;
    //
    public static final int FIREBIRD = 0;
    public static final int POSTGRESQL = 1;
    public static final int MSSQL = 2;
    public static final int MYSQL = 3;
    public static final int LOCAL = 4;

    public static String getTypeName(int type) {
        switch (type) {
            case CatalogConstants.HDD:
                return NbBundle.getMessage(CatalogConstants.class, "HDD");
            case CatalogConstants.FLOPPY:
                return NbBundle.getMessage(CatalogConstants.class, "FLOPPY");
            case CatalogConstants.CDROM:
                return NbBundle.getMessage(CatalogConstants.class, "CDROM");
            case CatalogConstants.DVDROM:
                return NbBundle.getMessage(CatalogConstants.class, "DVDROM");
            case CatalogConstants.FOLDER:
                return NbBundle.getMessage(CatalogConstants.class, "FOLDER");
            default:
                return NbBundle.getMessage(CatalogConstants.class, "UNKNOWN_TYPE");
        }
    }
}