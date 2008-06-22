/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.readingfiles.pluginapi;

import de.berlios.jfindmyfiles.readingfiles.utils.ReadingUtils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import org.openide.util.NbPreferences;

/**
 *
 * @author Knitter
 */
public class PluginCache {

    private Hashtable<String, Reader> cache;
    private Properties active;

    public PluginCache() {
        cache = new Hashtable<String, Reader>();
        //TODO:
        active = new Properties();
        loadPlugins();
    }

    private void loadPlugins() {
        String baseFolder = NbPreferences.forModule(PluginCache.class).get("PluginFolder", 
                System.getProperty("user.home") + File.pathSeparator + "JFindMyFiles Plugins");
        
        Class tClass = null;
        Reader reader = null;
        boolean found = false;

        try {
            if (baseFolder != null) {
                File f = new File(baseFolder);
                if (f.exists() && f.isDirectory()) {
                    URLClassLoader ucl = new URLClassLoader(new URL[]{f.toURI().toURL()}, ClassLoader.getSystemClassLoader());
                    File[] flst = f.listFiles();
                    String[] existing = null;
                    if (flst != null) {
                        existing = new String[flst.length];
                        for (int z = flst.length; z-- > 0;) {
                            if (ReadingUtils.findExtension(flst[z].getName()).equals("class")) {
                                existing[z] = ReadingUtils.stripFileExtension(flst[z].getName());
                                found = true;
                            }
                        }
                    }
                    if (found) {
                        for (int z = existing.length; z-- > 0;) {
                            tClass = Class.forName(existing[z], true, ucl);
                            reader = (Reader) tClass.newInstance();
                            cache.put(reader.pluginFor(), reader);
                        }
                    }
                }
            }
            //Provided plugins. - Could be the same as an URL search
            tClass = Class.forName("de.berlios.jfindmyfiles.readingfiles.plugins.AntMovieCataloferPlugin");
            reader = (Reader) tClass.newInstance();
            cache.put(reader.pluginFor(), reader);
            
            tClass = Class.forName("de.berlios.jfindmyfiles.readingfiles.plugins.JPGPlugin");
            reader = (Reader) tClass.newInstance();
            cache.put(reader.pluginFor(), reader);
        } catch (MalformedURLException ex) {
            //TODO: logging
        } catch (ClassNotFoundException ex) {
            //TODO: logging
        } catch (InstantiationException ex) {
            //TODO: logging
        } catch (IllegalAccessException ex) {
            //TODO: logging
        }
    }

    public Reader readerFor(String extension) {
        return cache.get(extension);
    }

    public int pluginCount() {
        return cache.size();
    }

    public List<Reader> listAll() {
        ArrayList<Reader> temp = new ArrayList<Reader>();
        for (Reader r : cache.values()) {
            temp.add(r);
        }
        return temp;
    }
}
