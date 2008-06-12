/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.readingfiles.pluginapi;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author Knitter
 */
public class PluginCache {

    private Hashtable<String, Reader> cache;

    public PluginCache() {
        cache = new Hashtable<String, Reader>();
    //loadPlugins();
    }

    private void loadPlugins() {
        try {
            File f = new File("C:\\Documents and Settings\\Knitter\\Desktop\\PLGIN\\");
            String s = "pluginapi.AntMovieCatalogerPlugin";
            URLClassLoader ucl = new URLClassLoader(new URL[]{f.toURI().toURL()}, ClassLoader.getSystemClassLoader());
            Class tClass = Class.forName(s, true, ucl);
            Reader reader = (Reader) tClass.newInstance();
            cache.put(reader.pluginFor(), reader);
        } catch (MalformedURLException ex) {
            //TODO: logging
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InstantiationException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalAccessException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public Reader readerFor(String extension) {
        return cache.get(extension);
    }
    
    public int pluginCount() {
        return cache.size();
    }
    
    public List listAll() {
        ArrayList<Reader> temp = new ArrayList<Reader>();
        for(Reader r : cache.values()) {
            temp.add(r);
        }
        return temp;
    }
}
