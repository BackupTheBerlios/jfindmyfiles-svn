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

import de.berlios.jfindmyfiles.readingfiles.utils.ReadingUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Knitter
 */
public class CSV extends ExportEngine {

    private File file;

    public CSV(File file) {
        this.file = file;
    }

    public void export() {
        start();
    }

    @Override
    public void run() {
        PrintWriter pw = null;
        //TODO: i18n
        String base = ReadingUtils.stripFileExtension(file.getAbsolutePath());
        try {
            //name,creation-date,description,total-size,disk-number,total-folders
            pw = new PrintWriter(new FileWriter(file));
            pw.print("name,creation-date,description,total-size,disk-number,total-folders\r\n");
            pw.close();
            
            //label-id,name
            pw = new PrintWriter(new FileWriter(base + "-labels.csv"));
            pw.print("label-id,name\r\n");
            pw.close();
            
            //group-id,name,description,capacity,groups/*ids separdos por ponto e virgula*/,parent-id
            pw = new PrintWriter(new FileWriter(base + "-groups.csv"));
            pw.print("group-id,name,description,capacity,groups,parent-id\r\n");
            pw.close();
            
            //media-id,name,capacity,last_modified,description,free-space,location,files/*ids separdos por ponto e virgula*/,labels/*ids separdos por ponto e virgula*/,type
            pw = new PrintWriter(new FileWriter(base + "-media.csv"));
            pw.print("media-id,name,capacity,last_modified,description,free-space,location,files,labels,type\r\n");
            pw.close();
            
            //file-id,name,absolute-path,last-modified,description,hidden,size,extension,image-thumb,video-preview,audio-clip,is-file,children,parent-id,media-id
            pw = new PrintWriter(new FileWriter(base + "-files.csv"));
            pw.print("file-id,name,absolute-path,last-modified,description,hidden,size,extension,image-thumb,video-preview,audio-clip,is-file,children,parent-id,media-id\r\n");
            pw.close();
            
            //user-id,firstname,surname,loans>/*ids separdos por ponto e virgula*/
            pw = new PrintWriter(new FileWriter(base + "-users.csv"));
            pw.print("user-id,firstname,surname,loans\r\n");
            pw.close();

            //loan-id,loaned-date,returned-date,user-id,media-id
            pw = new PrintWriter(new FileWriter(base + "-loans.csv"));
            pw.print("loan-id,loaned-date,returned-date,user-id,media-id\r\n");
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
}
