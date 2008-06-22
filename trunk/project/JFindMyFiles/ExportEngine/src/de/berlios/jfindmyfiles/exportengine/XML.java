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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.hibernate.Session;
import org.openide.util.Lookup;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author Knitter
 */
public class XML extends ExportEngine {

    private File file;

    public XML(File file) {
        this.file = file;
    }

    public void export() {
        start();
    }

    @Override
    public void run() {
        StreamResult sr = null;
        try {
            fireExportStarted();
            sr = new StreamResult(new PrintWriter(file));
            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

            // SAX2.0 ContentHandler.
            TransformerHandler hd = tf.newTransformerHandler();
            Transformer serializer = hd.getTransformer();

            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            //serializer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "users.dtd");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            hd.setResult(sr);
            hd.startDocument();
            AttributesImpl atts = new AttributesImpl();
            String aux;
            List lAux;
            CatalogEngine eng = Lookup.getDefault().lookup(CatalogEngine.class);
            Session s = eng.sessionFactory.getCurrentSession();
            s.beginTransaction();
            CatalogProperties props = eng.getProperties();

            hd.startDocument();
            hd.startElement("", "", "catalog", atts);
            //CATALOG INFO
            hd.startElement("", "", "name", atts);
            aux = props.getName() != null ? props.getName() : "";
            hd.characters(aux.toCharArray(), 0, aux.length());
            hd.endElement("", "", "name");
            hd.startElement("", "", "creation-date", atts);
            aux = props.getCreationDate() != null ? String.valueOf(props.getCreationDate().getTime()) : "";
            hd.characters(aux.toCharArray(), 0, aux.length());
            hd.endElement("", "", "creation-date");
            hd.startElement("", "", "description", atts);
            aux = props.getDescription() != null ? props.getDescription() : "";
            hd.characters(aux.toCharArray(), 0, aux.length());
            hd.endElement("", "", "description");
            aux = String.valueOf(props.getTotalSize());
            hd.startElement("", "", "total-size", atts);
            hd.characters(aux.toCharArray(), 0, aux.length());
            hd.endElement("", "", "total-size");
            aux = String.valueOf(props.getDiskNumber());
            hd.startElement("", "", "disk-number", atts);
            hd.characters(aux.toCharArray(), 0, aux.length());
            hd.endElement("", "", "disk-number");
            aux = String.valueOf(props.getTotalFolders());
            hd.startElement("", "", "total-folders", atts);
            hd.characters(aux.toCharArray(), 0, aux.length());
            hd.endElement("", "", "total-folders");
            aux = String.valueOf(props.getTotalFiles());
            hd.startElement("", "", "total-files", atts);
            hd.characters(aux.toCharArray(), 0, aux.length());
            hd.endElement("", "", "total-files");            
            //END CATALOG INFO
            //START LABEL LIST
            lAux = s.createQuery("from Label").list();
            if (lAux != null) {
                hd.startElement("", "", "label-list", atts);
                for (Object l : lAux) {
                    atts.clear();
                    atts.addAttribute("", "", "id", "CDATA", String.valueOf(((Label) l).getId()));
                    hd.startElement("", "", "label", atts);
                    atts.clear();
                    hd.startElement("", "", "name", atts);
                    aux = ((Label) l).getName() != null ? ((Label) l).getName() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "label");
                }
                hd.endElement("", "", "label-list");
            }
            //END LABEL LIST
            //START GROUP LIST
            lAux = s.createQuery("from DiskGroup").list();
            if (lAux != null) {
                hd.startElement("", "", "group-list", atts);
                DiskGroup g;
                for (Object l : lAux) {
                    atts.clear();
                    g = (DiskGroup) l;
                    atts.addAttribute("", "", "id", "CDATA", String.valueOf(g.getId()));
                    hd.startElement("", "", "group", atts);
                    atts.clear();
                    hd.startElement("", "", "name", atts);
                    aux = g.getName() != null ? g.getName() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "name");
                    hd.startElement("", "", "description", atts);
                    aux = g.getDescription() != null ? g.getDescription() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "description");
                    hd.startElement("", "", "capacity", atts);
                    aux = String.valueOf(g.getCapacity());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "capacity");
                    hd.startElement("", "", "groups", atts);//ids separdos por ponto e virgula

                    aux = "";
                    for (DiskGroup g2 : g.getGroupList()) {
                        aux += g2.getId() + ";";
                    }
                    if (aux.length() > 1) {
                        aux = aux.substring(0, aux.length() - 2);
                    }

                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "groups");
                    hd.startElement("", "", "parent-id", atts);
                    aux = g.getParent() != null ? String.valueOf(g.getParent().getId()) : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "parent-id");
                    hd.endElement("", "", "group");
                }
                hd.endElement("", "", "group-list");
            }
            //END GROUP LIST
            //START MEDIA LIST
            lAux = s.createQuery("from Media").list();
            if (lAux != null) {
                hd.startElement("", "", "media-list", atts);
                Media m;
                for (Object l : lAux) {
                    m = (Media) l;
                    atts.clear();
                    atts.addAttribute("", "", "id", "CDATA", String.valueOf(m.getId()));
                    hd.startElement("", "", "media", atts);
                    atts.clear();
                    hd.startElement("", "", "name", atts);
                    aux = m.getName() != null ? m.getName() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "name");
                    hd.startElement("", "", "capacity", atts);
                    aux = String.valueOf(m.getCapacity());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "capacity");

                    hd.startElement("", "", "last-modified", atts);
                    aux = String.valueOf(m.getLastModified());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "last-modified");

                    hd.startElement("", "", "description", atts);
                    aux = m.getDescription() != null ? m.getDescription() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "description");
                    hd.startElement("", "", "free-space", atts);
                    aux = String.valueOf(m.getFreeSpace());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "free-space");
                    hd.startElement("", "", "location", atts);
                    aux = m.getLocation() != null ? m.getLocation() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "location");
                    hd.startElement("", "", "files", atts);//ids separdos por ponto e virgula

                    aux = "";
                    for (FileWrapper f : m.getFileList()) {
                        aux += f.getId() + ";";
                    }
                    if (aux.length() > 1) {
                        aux = aux.substring(0, aux.length() - 2);
                    }

                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "files");

                    hd.startElement("", "", "labels", atts);//ids separdos por ponto e virgula
                    aux = "";
                    for (Label l2 : m.getLabelList()) {
                        aux += l2.getId() + ";";
                    }
                    if (aux.length() > 1) {
                        aux = aux.substring(0, aux.length() - 2);
                    }

                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "labels");
                    hd.startElement("", "", "type", atts);
                    aux = String.valueOf(m.getType());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "type");
                    hd.endElement("", "", "media");
                }
                hd.endElement("", "", "media-list");
            }
            //END MEDIA LIST
            //START FILE LIST
            lAux = s.createQuery("from FileWrapper").list();
            if (lAux != null) {
                hd.startElement("", "", "file-list", atts);
                FileWrapper f;
                for (Object l : lAux) {
                    f = (FileWrapper) l;
                    atts.clear();
                    atts.addAttribute("", "", "id", "CDATA", String.valueOf(f.getId()));
                    hd.startElement("", "", "file", atts);
                    atts.clear();
                    hd.startElement("", "", "name", atts);
                    aux = f.getName() != null ? f.getName() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "name");
                    hd.startElement("", "", "absolute-path", atts);
                    aux = f.getAbsolutePath() != null ? f.getAbsolutePath() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "absolute-path");
                    hd.startElement("", "", "last-modified", atts);
                    aux = String.valueOf(f.getLastModified());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "last-modified");
                    hd.startElement("", "", "description", atts);
                    aux = f.getDescription() != null ? f.getDescription() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "description");
                    hd.startElement("", "", "hidden", atts);
                    aux = String.valueOf(f.isHidden());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "hidden");
                    hd.startElement("", "", "size", atts);
                    aux = String.valueOf(f.getSize());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "size");
                    hd.startElement("", "", "extension", atts);
                    aux = f.getExtension() != null ? f.getExtension() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "extension");
                    //TODO: image, video and audio data
                    //hd.startElement("", "", "", atts);<image-thumb></image-thumb>
                    //hd.startElement("", "", "", atts);<video-preview></video-preview>
                    //hd.startElement("", "", "", atts);<audio-clip></audio-clip>
                    hd.startElement("", "", "is-file", atts);
                    aux = String.valueOf(f.isFile());
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "is-file");
                    hd.startElement("", "", "sha1", atts);
                    aux = f.getSha1() != null ? f.getSha1() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "sha1");

                    hd.startElement("", "", "children", atts);//ids separados por ponto e virgula
                    //TODO: add elements
                    hd.endElement("", "", "children");

                    hd.startElement("", "", "parent-id", atts);
                    aux = f.getParent() != null ? String.valueOf(f.getParent().getId()) : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "parent-id");
                    hd.startElement("", "", "media-id", atts);
                    aux = f.getDisk() != null ? String.valueOf(f.getDisk().getId()) : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "media-id");
                    hd.endElement("", "", "file");
                }
                hd.endElement("", "", "file-list");
            }
            //END FILE LIST
            //START USER LIST
            lAux = s.createQuery("from User").list();
            if (lAux != null) {
                hd.startElement("", "", "user-list", atts);
                User u;
                for (Object o : lAux) {
                    u = (User) o;
                    atts.clear();
                    atts.addAttribute("", "", "id", "CDATA", String.valueOf(u.getId()));
                    hd.startElement("", "", "user", atts);
                    atts.clear();
                    hd.startElement("", "", "firstname", atts);
                    aux = u.getFirstname() != null ? u.getFirstname() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "firstname");
                    hd.startElement("", "", "surname", atts);
                    aux = u.getSurname() != null ? u.getSurname() : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "surname");
                    hd.endElement("", "", "user");
                }
                hd.endElement("", "", "user-list");
            }
            //END USER LIST
            //START LOAN LIST
            lAux = s.createQuery("from Loan").list();
            if (lAux != null) {
                hd.startElement("", "", "loan-list", atts);
                Loan l;
                for (Object o : lAux) {
                    l = (Loan) o;
                    atts.clear();
                    atts.addAttribute("", "", "id", "CDATA", String.valueOf(l.getId()));
                    hd.startElement("", "", "loan", atts);
                    atts.clear();
                    hd.startElement("", "", "loaned-date", atts);
                    aux = l.getLoaned() != null ? String.valueOf(l.getLoaned().getTime()) : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "loaned-date");

                    hd.startElement("", "", "returned-date", atts);
                    aux = l.getReturned() != null ? String.valueOf(l.getReturned().getTime()) : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "returned-date");
                    hd.startElement("", "", "user-id", atts);
                    aux = l.getLoanee() != null ? String.valueOf(l.getLoanee().getId()) : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "user-id");
                    hd.startElement("", "", "media-id", atts);
                    aux = l.getMedia() != null ? String.valueOf(l.getMedia().getId()) : "";
                    hd.characters(aux.toCharArray(), 0, aux.length());
                    hd.endElement("", "", "media-id");
                    hd.endElement("", "", "loan");
                }
                hd.endElement("", "", "loan-list");
            }
            //END LOAN LIST            
            hd.endElement("", "", "catalog");
            //END CATALOG
            hd.endDocument();
            s.getTransaction().commit();
            fireExportFinished();
        } catch (FileNotFoundException ex) {
            fireExportError();
        } catch (IOException ex) {
            fireExportError();
        } catch (TransformerConfigurationException ex) {
            fireExportError();
        } catch (SAXException ex) {
            fireExportError();
        }
    }
}
