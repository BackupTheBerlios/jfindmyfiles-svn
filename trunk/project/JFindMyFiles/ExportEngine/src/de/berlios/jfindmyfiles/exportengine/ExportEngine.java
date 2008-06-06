/**
 *  Copyright (C) 2008  Patrícia Monteiro e Sérgio Lopes
 *
 *  This file is part of JFindMyFiles.
 *
 *  JFindMyFiles is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFindMyFiles is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFindMyFiles.  If not, see 
 * <http://www.gnu.org/licenses/gpl.html>.
 */
package de.berlios.jfindmyfiles.exportengine;

import java.io.File;
import java.util.List;

public abstract class ExportEngine {

    protected List groups;
    protected List types;
    protected List labels;
    
    private ExportEngine() {
    }
    
    public final void queryData() {
        //
    }

    public static void exportToXML(File file) {
        new XML(file).start();//.export()
    }

    public static void exportToCSV(File file) {
        new CSV(file).start();//.export()
    }

    public static void exportToODS(File file) {
        new ODS(file).start();//.export()
    }

    public static void exportToSQL(File file) {
        new SQL(file).start();//.export()
    }

    public static void exportToHTML(File destination, String name, String template) {
        new HTML(destination, name, template).start();//.export()
    }
}
