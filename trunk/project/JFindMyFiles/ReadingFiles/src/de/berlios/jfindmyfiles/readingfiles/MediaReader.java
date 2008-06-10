/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.readingfiles;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import de.berlios.jfindmyfiles.catalog.entities.Media;
import java.io.File;
import java.util.Stack;
import java.util.Vector;
import org.hibernate.Session;
import org.openide.util.Lookup;

/**
 *
 * @author Knitter
 */
public class MediaReader {

    private Vector<ReadingListener> listeners;
    private MediaReader me = this;
    private boolean abort = false;

    public MediaReader() {
        listeners = new Vector<ReadingListener>();
    }

    public void read(final File file, final boolean sha, final boolean isMedia) {

        new Thread(new Runnable() {

            public void run() {
                fireReadingStarted(new ReadingEvent(me, "", null));
                Session s = Lookup.getDefault().lookup(CatalogEngine.class).sessionFactory.getCurrentSession();
                s.beginTransaction();
                Stack<File> directories = new Stack<File>();
                File current, listing[];
                long totalSize = 0L, childSize = 0L;
                FileWrapper fw, child;
                int z;
                Media container = null;
                if (isMedia) {
                    container = new Media();
                }

                if (file.isDirectory()) {//NOTE: this protections may be dropped
                    fw = new FileWrapper(file.getName(),
                            file.getAbsolutePath(), 0, file.isFile(), file.isHidden(),
                            file.lastModified(), container, null);

                    if ((listing = file.listFiles()) != null) {
                        for (z = listing.length; z-- > 0;) {
                            if (listing[z].isDirectory()) {
                                directories.push(listing[z]);
                            } else {
                                totalSize += listing[z].length();
                                fw.addChild(new FileWrapper(listing[z].getName(),
                                        listing[z].getAbsolutePath(),
                                        listing[z].length(), listing[z].isFile(),
                                        listing[z].isHidden(),
                                        listing[z].lastModified(), container, fw));
                            }
                        }
                    }

                    while (!directories.empty() && !abort) {
                        current = directories.pop();
                        childSize = 0;
                        child = new FileWrapper(current.getName(),
                                current.getAbsolutePath(), 0, current.isFile(),
                                current.isHidden(), current.lastModified(),
                                container, null);

                        if ((listing = current.listFiles()) != null) {
                            for (z = listing.length; z-- > 0;) {
                                if (listing[z].isDirectory()) {
                                    directories.push(listing[z]);
                                } else {
                                    childSize += listing[z].length();
                                    child.addChild(new FileWrapper(listing[z].getName(),
                                            listing[z].getAbsolutePath(),
                                            listing[z].length(), listing[z].isFile(),
                                            listing[z].isHidden(),
                                            listing[z].lastModified(), container, fw));
                                }
                            }
                        }
                        child.setSize(childSize);
                    }
                    fw.setSize(totalSize);
                    if (abort) {
                        s.getTransaction().rollback();
                    }
                    s.getTransaction().commit();
                }
                fireReadingStopped(new ReadingEvent(me, "", container));
            }
        }).start();

    }

    public void abort() {
        abort = true;
        fireReadingAborted(new ReadingEvent(this, "", null));
    }

    public void addListener(ReadingListener l) {
        if (listeners == null) {
            listeners = new Vector<ReadingListener>();
        }

        listeners.add(l);
    }

    public void removeListener(ReadingListener l) {
        if (listeners != null) {
            listeners.remove(l);
        }
    }
   
    private void fireReadingStarted(ReadingEvent evt) {
        for (ReadingListener l : listeners) {
            l.readingStarted(evt);
        }
    }

    private void fireReadingStopped(ReadingEvent evt) {
        for (ReadingListener l : listeners) {
            l.readingStopped(evt);
        }
    }
    
    private void fireReadingFile(ReadingEvent evt) {
        for (ReadingListener l : listeners) {
            l.readingFile(evt);
        }
    }

    private void fireReadingAborted(ReadingEvent evt) {
        for (ReadingListener l : listeners) {
            l.readingAborted(evt);
        }
    }
}
