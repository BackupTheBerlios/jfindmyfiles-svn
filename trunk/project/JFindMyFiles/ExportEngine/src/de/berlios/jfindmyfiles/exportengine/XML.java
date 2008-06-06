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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author Knitter
 */
public class XML extends Thread implements ExportContract {

    private File file;

    public XML(File file) {
        this.file = file;
    }

    public void export() {
        start();
    }

    @Override
    public void run() {

        try {
            PrintWriter out = new PrintWriter(file);

            StreamResult sr = new StreamResult(out);

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

           /* // USERS tag.
            hd.startElement("", "", "USERS", atts);

            // USER tags.
            String[] id = {"PWD122", "MX787", "A4Q45"};
            String[] type = {"customer", "manager", "employee"};
            String[] desc = {"Tim@Home", "Jack&Moud", "John D'oé"};

            for (int i = 0; i < id.length; i++) {
                atts.clear();
                atts.addAttribute("", "", "ID", "CDATA", id[i]);
                atts.addAttribute("", "", "TYPE", "CDATA", type[i]);
                hd.startElement("", "", "USER", atts);
                hd.characters(desc[i].toCharArray(), 0, desc[i].length());
                hd.endElement("", "", "USER");
            }
            hd.endElement("", "", "USERS");
            hd.endDocument();*/
        } catch (FileNotFoundException ex) {
        } catch (TransformerConfigurationException ex) {
        } catch (SAXException ex) {
        }
    }
    /*
    <catalog>
    <name></name>
    <creation-date></creation-date>
    <description></description>
    <total-size></total-size>
    <disk-number></disk-number>
    <total-folders></total-folders>
    <file-list>
    <file id="">
    <name></name>
    <absolute-path></absolute-path>
    <last-modified></last-modified>
    <description></description>
    <hidden><!-- true/false -->
    </hidden>
    <size></size>
    <extension></extension>
    <image-thumb></image-thumb>
    <video-preview></video-preview>
    <audio-clip></audio-clip>
    <is-file></is-file>
    <children><!-- file items -->
    </children>
    <parent-id></parent-id>
    <media-id></media-id>
    </file>
    </file-list>
    <label-list>
    <label id="">
    <name></name>
    </label>
    </label-list>
    <group-list>
    <group id="">
    <name></name>
    <description></description>
    <capacity></capacity>
    <groups><!-- ids separdos por ponto e virgula -->
    </groups>
    <parent-id></parent-id>
    </group>
    </group-list>
    <loan-list>
    <loan id="">
    <date-loaned></date-loaned>
    <date-returned></date-returned>
    <user-id></user-id>
    <media-id></media-id>
    </loan>
    </loan-list>
    <media-list>
    <media id="">
    <name></name>
    <capacity></capacity>
    <last-modified></last-modified>
    <description></description>
    <free-space></free-space>
    <location></location>
    <files><!-- ids separdos por ponto e virgula -->
    </files>
    <labels><!-- ids separdos por ponto e virgula -->
    </labels>
    <type-id></type-id>
    </media>
    </media-list>
    <type-list>
    <type id="">
    <name></name>
    </type>
    </type-list>
    <user-list>
    <user id="">
    <first-name></first-name>
    <loans><!-- ids separdos por ponto e virgula -->
    </loans>
    <surname></surname>
    </user>
    </user-list>
    </catalog>*/
}
