/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pluginapi;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Hashtable;

/**
 *
 * @author Knitter
 */
public class PluginCache {

    private Hashtable<String, Reader> cache;

    public PluginCache() throws MalformedURLException {
        cache = new Hashtable<String, Reader>();
        loadPlugins();
    }

    private void loadPlugins() throws MalformedURLException {
        try {
            //class for name
            //class.newInstance
            //do something
            //Load Internal plugins
            //Reader r = new AntMovieCatalogerPlugin();

            //Class c = Class.forName("pluginapi.AntMovieCatalogerPlugin");
            /*URL u = new URL("");
            URLClassLoader ucl = new URLClassLoader(new URL[] {}, ClassLoader.getSystemClassLoader());
            String s = "pluginapi.AntMovieCatalogerPlugin";
            
            Class c = Class.forName(s, true, ucl);
            Reader r = (Reader) c.newInstance();
            cache.put(r.pluginFor(), r);*/

            //File f = new File("C:\\Documents and Settings\\Knitter\\Desktop\\PLGIN\\");
            //URLClassLoader ucl = new URLClassLoader(new URL[]{f.toURI().toURL()}, ClassLoader.getSystemClassLoader());
            //String s = "pluginapi.AntMovieCatalogerPlugin";
            String s = "pluginapi.JPGPlugin";
            
            //Class c = Class.forName(s, true, ucl);
            Class c = Class.forName(s);
            Reader r = (Reader) c.newInstance();
            cache.put(r.pluginFor(), r);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public Reader readerFor(String extension) {
        return cache.get(extension);
    }
}
