/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.readingfiles;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import de.berlios.jfindmyfiles.catalog.entities.Media;
import de.berlios.jfindmyfiles.catalog.entities.Type;
import de.berlios.jfindmyfiles.readingfiles.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Stack;
import java.util.Vector;
import jonelo.jacksum.JacksumAPI;
import jonelo.jacksum.algorithm.AbstractChecksum;
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

    public void read(final File file, final boolean sha, final boolean isMedia, final String mediaName, final DiskGroup group, final Type type) {

        new Thread(new Runnable() {

            public void run() {
                fireReadingStarted(new ReadingEvent(me, "", null));
                Session s = Lookup.getDefault().lookup(CatalogEngine.class).sessionFactory.getCurrentSession();
                s.beginTransaction();
                Stack<CompositeFile> directories = new Stack<CompositeFile>();
                File current, listing[];
                CompositeFile cf;
                long childSize = 0L, totalSize = 0L;
                FileWrapper fw, temp;
                int z;
                Type t = new Type("test");
                s.save(t);
                Media container = new Media();
                container.setName(mediaName);
                container.setType(t);
                s.save(container);

                if ((listing = file.listFiles()) != null) {
                    for (z = listing.length; z-- > 0;) {
                        if (listing[z].isDirectory()) {
                            directories.push(new CompositeFile(listing[z], null));
                        } else {
                            fireReadingFile(new ReadingEvent(me, listing[z].getAbsolutePath(), null));
                            totalSize += listing[z].length();
                            temp = new FileWrapper(listing[z].getName(),
                                    listing[z].getAbsolutePath(),
                                    listing[z].length(), listing[z].isFile(),
                                    listing[z].isHidden(),
                                    listing[z].lastModified(), container, null,
                                    (sha ? FileUtils.calculateSHA1HashString(listing[z]) : ""), 
                                    FileUtils.findExtension(listing[z].getName()));
                            s.save(temp);
                            container.addFile(temp);
                        }
                    }
                }

                while (!directories.empty() && !abort) {
                    cf = directories.pop();
                    current = cf.file;
                    childSize = 0;
                    fw = new FileWrapper(current.getName(),
                            current.getAbsolutePath(), 0, current.isFile(),
                            current.isHidden(), current.lastModified(),
                            container, cf.parent, "", "");//NOTE: should we hash a directory?
                    s.save(fw);
                    if ((listing = current.listFiles()) != null) {
                        for (z = listing.length; z-- > 0;) {
                            if (listing[z].isDirectory()) {
                                directories.push(new CompositeFile(listing[z], fw));
                            } else {
                                fireReadingFile(new ReadingEvent(me, listing[z].getAbsolutePath(), null));
                                childSize += listing[z].length();
                                temp = new FileWrapper(listing[z].getName(),
                                        listing[z].getAbsolutePath(),
                                        listing[z].length(), listing[z].isFile(),
                                        listing[z].isHidden(),
                                        listing[z].lastModified(), container, fw,
                                        (sha ? FileUtils.calculateSHA1HashString(listing[z]) : ""), 
                                        FileUtils.findExtension(listing[z].getName()));
                                s.save(temp);
                                fw.addChild(temp);
                            }
                        }
                    }
                    fw.setSize(childSize);
                    totalSize += childSize;
                }
                //TODO: capacity and other properties
                if (abort) {
                    s.getTransaction().rollback();
                }
                s.getTransaction().commit();
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
