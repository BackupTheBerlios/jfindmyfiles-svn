/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.readingfiles.pluginapi;

import de.berlios.jfindmyfiles.readingfiles.plugins.AntMovieCatalogerPlugin;
import de.berlios.jfindmyfiles.readingfiles.utils.ReadingUtils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 * Controller class responsible for loading and providing the existing plugins 
 * to any other entity that queries for them.
 */
public class PluginCache {

    private Hashtable<String, Reader> cache;
    private static final Logger logger = Logger.getLogger(PluginCache.class.getName());
    private Preferences p;
    
    /**
     * Constructs the cache loading all available plugins.
     */
    public PluginCache() {
        cache = new Hashtable<String, Reader>();
        p = NbPreferences.forModule(PluginCache.class);
        loadPlugins();
    }

    private void loadPlugins() {
        String baseFolder = NbPreferences.forModule(PluginCache.class).get("PluginFolder", 
                System.getProperty("user.home") + File.separator + "jfmf" + File.separator +
                "plugins");
        
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
                            reader.setActive(p.getBoolean(reader.pluginFor(), false));
                        }
                    }
                }
            }
            //Provided plugins
            AntMovieCatalogerPlugin ant = new AntMovieCatalogerPlugin();
            cache.put(ant.pluginFor(), ant);
            reader.setActive(p.getBoolean(ant.pluginFor(), false));
            
            tClass = Class.forName("de.berlios.jfindmyfiles.readingfiles.plugins.JPGPlugin");
            reader = (Reader) tClass.newInstance();
            cache.put(reader.pluginFor(), reader);
        } catch (MalformedURLException ex) {
            logger.log(Level.WARNING, "Unable to read plugin cache", ex);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.WARNING, "Unable to read plugin cache", ex);
        } catch (InstantiationException ex) {
            logger.log(Level.WARNING, "Unable to read plugin cache", ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.WARNING, "Unable to read plugin cache", ex);
        }
    }

    /**
     * Gets the available plugin, if any, for the given extension.
     * 
     * @param extension string representing the extension that identifies the 
     * reader.
     * 
     * @return Reader for the extension or null if non exists.
     * 
     * @see Hashtable.get(Object key)
     */
    public Reader readerFor(String extension) {
        return cache.get(extension);
    }

    /**
     * Provides the number of registered and loaded plugins.
     * 
     * @return int with the number of plugins loaded at creation time.
     */
    public int pluginCount() {
        return cache.size();
    }

    /**
     * Returns all loaded plugins.
     * 
     * @return List with all plugins.
     */
    public List<Reader> listAll() {
        ArrayList<Reader> temp = new ArrayList<Reader>();
        for (Reader r : cache.values()) {
            temp.add(r);
        }
        return temp;
    }
}
