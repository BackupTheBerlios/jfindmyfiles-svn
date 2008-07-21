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

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.CatalogProperties;
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import de.berlios.jfindmyfiles.catalog.entities.Label;
import de.berlios.jfindmyfiles.catalog.entities.Loan;
import de.berlios.jfindmyfiles.catalog.entities.Media;
import de.berlios.jfindmyfiles.catalog.entities.User;
import de.berlios.jfindmyfiles.readingfiles.utils.ReadingUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.openide.util.Lookup;

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
            String aux;
            List lAux;
            CatalogEngine eng = Lookup.getDefault().lookup(CatalogEngine.class);
            Session s = eng.sessionFactory.getCurrentSession();
            s.beginTransaction();
            CatalogProperties props = eng.getProperties();

            //name,creation-date,description,total-size,disk-number,total-folders
            pw = new PrintWriter(new FileWriter(base + ".csv"));
            pw.print("name,creation-date,description,total-size,disk-number,total-folders,total-files\r\n");

            aux = props.getName() + "," + props.getCreationDate() + "," + props.getDescription() +
                    "," + props.getTotalSize() + "," + props.getDiskNumber() + "," +
                    props.getTotalFolders() + "," + props.getTotalFiles() + "\r\n";
            pw.print(aux);
            pw.close();

            //label-id,name
            lAux = s.createQuery("from Label").list();
            if (lAux != null) {
                pw = new PrintWriter(new FileWriter(base + "-labels.csv"));
                pw.print("label-id,name\r\n");
                for (Object l : lAux) {
                    aux = ((Label) l).getId() + "," + ((Label) l).getName() + "\r\n";
                }
                pw.print(aux);
                pw.close();
            }

            //group-id,name,description,capacity,groups/*ids separdos por ponto e virgula*/,parent-id
            lAux = s.createQuery("from DiskGroup").list();
            if (lAux != null) {
                pw = new PrintWriter(new FileWriter(base + "-groups.csv"));
                pw.print("group-id,name,description,capacity,groups,parent-id\r\n");

                DiskGroup g;
                for (Object l : lAux) {
                    g = (DiskGroup) l;
                    aux = g.getId() + "," + g.getName() + "," + g.getDescription() +
                            "," + g.getCapacity() + ",";

                    for (DiskGroup g2 : g.getGroupList()) {
                        aux += g2.getId() + ";";
                    }

                    if (aux.length() > 1) {
                        aux = aux.substring(0, aux.length() - 2);
                    }

                    aux = "," + aux + g.getParent().getId() + "\r\n";
                }
                pw.print(aux);
                pw.close();
            }

            //media-id,name,capacity,last_modified,description,free-space,location,files/*ids separdos por ponto e virgula*/,labels/*ids separdos por ponto e virgula*/,type            
            lAux = s.createQuery("from Media").list();
            if (lAux != null) {
                pw = new PrintWriter(new FileWriter(base + "-media.csv"));
                pw.print("media-id,name,capacity,last_modified,description,free-space,location,files,labels,type\r\n");
                Media m;
                for (Object l : lAux) {
                    m = (Media) l;
                    aux = m.getId() + "," + m.getName() + "," + m.getCapacity() + "," +
                            m.getLastModified() + "," + m.getDescription() + "," +
                            m.getFreeSpace() + "," + m.getLocation() + ",";

                    for (FileWrapper f : m.getFileList()) {
                        aux += f.getId() + ";";
                    }
                    if (aux.length() > 1) {
                        aux = aux.substring(0, aux.length() - 2);
                    }
                    aux += ",";

                    for (Label l2 : m.getLabelList()) {
                        aux += l2.getId() + ";";
                    }
                    if (aux.length() > 1) {
                        aux = aux.substring(0, aux.length() - 2);
                    }
                    aux = "," + m.getType() + "\r\n";
                    pw.print(aux);
                }
                pw.close();
            }

            //file-id,name,absolute-path,last-modified,description,hidden,size,extension,image-thumb,video-preview,audio-clip,is-file,children,parent-id,media-id
            lAux = s.createQuery("from FileWrapper").list();
            if (lAux != null) {

                pw = new PrintWriter(new FileWriter(base + "-files.csv"));
                pw.print("file-id,name,absolute-path,last-modified,description,hidden,size,extension,image-thumb,video-preview,audio-clip,is-file,children,parent-id,media-id\r\n");
                FileWrapper f;
                for (Object l : lAux) {
                    f = (FileWrapper) l;
                    //TODO: image, video and audio data, children
                    aux = f.getId() + "," + f.getName() + "," + f.getAbsolutePath() + "," +
                            f.getLastModified() + "," + f.getDescription() + "," +
                            f.isHidden() + "," + f.getSize() + "," + f.getExtension() + "," +
                            f.isFile() + "," + f.getSha1() + ",TODO:CHILDREN," +
                            f.getDisk().getId() + "\r\n";
                    pw.print(aux);
                }
                pw.close();
            }

            //user-id,firstname,surname,loans>/*ids separdos por ponto e virgula*/
            lAux = s.createQuery("from User").list();
            if (lAux != null) {
                pw = new PrintWriter(new FileWriter(base + "-users.csv"));
                pw.print("user-id,firstname,surname,loans\r\n");
                User u;
                for (Object o : lAux) {
                    u = (User) o;
                    aux = u.getId() + "," + u.getFirstname() + "," + u.getSurname() + "\r\n";
                    pw.print(aux);
                }
                pw.close();
            }

            //loan-id,loaned-date,returned-date,user-id,media-id
            lAux = s.createQuery("from Loan").list();
            if (lAux != null) {
                pw = new PrintWriter(new FileWriter(base + "-loans.csv"));
                pw.print("loan-id,loaned-date,returned-date,user-id,media-id\r\n");

                Loan l;
                for (Object o : lAux) {
                    l = (Loan) o;
                    aux = l.getId() + "," + l.getLoaned() + "," + l.getReturned() +
                            "," + l.getLoanee() + "," + l.getMedia() + "\r\n";
                }
                pw.print(aux);
                pw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
}
