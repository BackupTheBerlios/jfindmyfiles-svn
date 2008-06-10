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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
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

            CatalogEngine eng = Lookup.getDefault().lookup(CatalogEngine.class);

            hd.startDocument();
            hd.startElement("", "", "catalog", atts);//<catalog>
            //CATALOG INFO
            hd.startElement("", "", "name", atts);//<name>
            hd.characters(eng.getProperties().getName().toCharArray(), 0, eng.getProperties().getName().length());
            hd.endElement("", "", "name");//</name>
            hd.startElement("", "", "creation-date", atts);//<creation-date>
            hd.endElement("", "", "creation-date");//</creation-date>
            hd.startElement("", "", "description", atts);//<description>
            hd.endElement("", "", "description");//</description>
            hd.startElement("", "", "total-size", atts);//<total-size>
            hd.endElement("", "", "total-size");//</total-size>
            hd.startElement("", "", "disk-number", atts);//<disk-number>
            hd.endElement("", "", "disk-number");//</disk-number>
            hd.startElement("", "", "total-folders", atts);//<total-folders>
            hd.endElement("", "", "total-folders");//</total-folders>
            //END CATALOG INFO
            hd.startElement("", "", "file-list", atts);//<file-list>
            //START FILE LIST
            hd.startElement("", "", "file", atts);//<file id="">
            hd.startElement("", "", "name", atts);//<name>
            hd.endElement("", "", "name");//</name>
            hd.startElement("", "", "absolute-path", atts);//<absolute-path>
            hd.endElement("", "", "absolute-path");//</absolute-path>
            hd.startElement("", "", "last-modified", atts);//<last-modified>
            hd.endElement("", "", "last-modified");//</last-modified>
            hd.startElement("", "", "description", atts);//<description>
            hd.endElement("", "", "description");//</description>
            hd.startElement("", "", "hidden", atts);//<hidden><!-- true/false -->
            hd.endElement("", "", "hidden");//</hidden>
            hd.startElement("", "", "size", atts);//<size>
            hd.endElement("", "", "size");//</size>
            hd.startElement("", "", "extension", atts);//<extension>
            hd.endElement("", "", "extension");//</extension>
            hd.startElement("", "", "image-thumb", atts);//<image-thumb>
            hd.endElement("", "", "image-thumb");//</image-thumb>
            hd.startElement("", "", "video-preview", atts);//<video-preview>
            hd.endElement("", "", "video-preview");//</video-preview>
            hd.startElement("", "", "audio-clip", atts);//<audio-clip>
            hd.endElement("", "", "audio-clip");//</audio-clip>
            hd.startElement("", "", "is-file", atts);//<is-file>
            hd.endElement("", "", "is-file");//</is-file>
            hd.startElement("", "", "children", atts);//<children><!-- file items -->
            hd.endElement("", "", "children");//</children>//TODO: ?
            hd.startElement("", "", "parent-id", atts);//<parent-id>
            hd.endElement("", "", "parent-id");//</parent-id>
            hd.startElement("", "", "media-id", atts);//<media-id>
            hd.endElement("", "", "media-id");//</media-id>
            hd.endElement("", "", "");//</file>
            hd.endElement("", "", "file-list");//</file-list
            //END FILE LIST
            //TODO: atts
            hd.startElement("", "", "label-list", atts);//<label-list>
            //START LABEL LIST
            hd.startElement("", "", "label", atts);//<label id="">
            hd.startElement("", "", "name", atts);//<name>
            hd.endElement("", "", "name");//</name>
            hd.endElement("", "", "label");//</label>
            hd.endElement("", "", "label-list");//</label-list>
            hd.startElement("", "", "group-list", atts);//<group-list>
            hd.startElement("", "", "group", atts);//<group id="">
            hd.startElement("", "", "name", atts);//<name>
            hd.endElement("", "", "name");//</name>
            hd.startElement("", "", "description", atts);//<description>
            hd.endElement("", "", "description");//</description>
            hd.startElement("", "", "capacity", atts);//<capacity>
            hd.endElement("", "", "capacity");//</capacity>
            hd.startElement("", "", "groups", atts);//<groups> < !--ids separdos por ponto e virgula -->
            hd.endElement("", "", "groups");//</groups>
            hd.startElement("", "", "parent-id", atts);//<parent-id>
            hd.endElement("", "", "parent-id");//</parent-id>
            hd.endElement("", "", "group");//</group>
            hd.endElement("", "", "group-list");//</group-list>
            hd.startElement("", "", "loan-list", atts);//<loan-list>
            hd.startElement("", "", "loan", atts);//<loan id="">
            hd.startElement("", "", "loaned-date", atts);//<loaned-date > 
            hd.endElement("", "", "loaned-date");//</loaned-date>
            hd.startElement("", "", "returned-date", atts);//<returned-date>
            hd.endElement("", "", "returned-date");//</returned-date>
            hd.startElement("", "", "user-id", atts);//<user-id >
            hd.endElement("", "", "user-id");//</user-id >
            hd.startElement("", "", "media-id", atts);//<media-id >
            hd.endElement("", "", "media-id");//</media-id >
            hd.startElement("", "", "loan", atts);//</loan> 
            hd.endElement("", "", "loan-list");//</loan-list>
            hd.startElement("", "", "media-list", atts);//<media-list>
            hd.startElement("", "", "media", atts);//<media id="">
            hd.startElement("", "", "name", atts);//<name>
            hd.endElement("", "", "name");//</name>
            hd.startElement("", "", "capacity", atts);//<capacity>
            hd.endElement("", "", "capacity");//</capacity>
            hd.startElement("", "", "last-modified", atts);//<last-modified> 
            hd.endElement("", "", "last-modified");//</last-modified>
            hd.startElement("", "", "description", atts);//<description>

            hd.endElement("", "", "description");//</description>
            hd.startElement("", "", "free-space", atts);//<free-space>
            hd.endElement("", "", "free-space");// </free-space>
            hd.startElement("", "", "location", atts);//<location>

            hd.endElement("", "", "location");//</location>
            hd.startElement("", "", "files", atts);//<files> < !--ids separdos por ponto e virgula -->
            hd.endElement("", "", "/files");//</files>
            hd.startElement("", "", "labels", atts);//<labels> ids separdos por ponto e virgula
            hd.endElement("", "", "labels");//</labels>
            hd.startElement("", "", "type-id", atts);//<type-id>
            hd.endElement("", "", "type-id");//</type-id>
            hd.endElement("", "", "media");//</media>
            hd.endElement("", "", "media-list");//</media-list>
            hd.startElement("", "", "type-list", atts);//<type-list>
            hd.startElement("", "", "type", atts);//<type id="">
            hd.startElement("", "", "naeme", atts);//<name>

            hd.endElement("", "", "name");//</name>
            hd.startElement("", "", "type", atts);//</type> 
            hd.endElement("", "", "type-list");//</type-list>
            hd.startElement("", "", "user-list", atts);//<user-list>
            hd.startElement("", "", "user", atts);//<user  id="">
            hd.startElement("", "", "firstname", atts);//<firstname> 

            hd.endElement("", "", "firstname");//</firstname>
            hd.startElement("", "", "surname", atts);//<surname>
            hd.endElement("", "", "surname");//</surname>

            hd.startElement("", "", "loans", atts);//<loans> < !--ids separdos por ponto e virgula -->
            hd.endElement("", "", "loans");//</loans>
            hd.endElement("", "", "user");//</user>
            hd.endElement("", "", "user-list");//</user-list>
            //END TOP LEVEL
            hd.endElement("", "", "catalog");//</catalog>
            hd.endDocument();
            sr.getOutputStream().flush();

            fireExportFinished();
        } catch (FileNotFoundException ex) {
            fireExportError();
        } catch (IOException ex) {
            fireExportError();
        } catch (TransformerConfigurationException ex) {
            fireExportError();
        } catch (SAXException ex) {
            fireExportError();
        } finally {
            try {
                if (sr != null) {
                    sr.getOutputStream().close();
                }
            } catch (Exception ex) {
                //IGNORE
            }
        }
    }
}
