/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import java.beans.IntrospectionException;
import java.io.IOException;
import javax.tools.FileObject;

import org.openide.cookies.InstanceCookie;
import org.openide.loaders.DataFolder;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class RssFolderChildren extends FilterNode.Children {

    RssFolderChildren(Node rssFolderNode) {
        super(rssFolderNode);
    }

    @Override
    protected Node[] createNodes(Node key) {
        Node n = key;

        //If we can find a data folder, then we create an RssNode,
        //if not, we look for the feed and then create a OneFeedNode:
        try {
            if (n.getLookup().lookup(DataFolder.class) != null) {
                return new Node[]{new RssNode(n)};
            } else {
                Feed feed = getFeed(n);
                if (feed != null) {
                    return new Node[]{
                        new OneFeedNode(n, feed.getSyndFeed())
                    };
                } else {
                    // best effort
                    return new Node[]{new FilterNode(n)};
                }
            }
        } catch (IOException ioe) {
            Exceptions.printStackTrace(ioe);
        } catch (IntrospectionException exc) {
            Exceptions.printStackTrace(exc);
        }
        // Some other type of Node (gotta do something)
        return new Node[]{new FilterNode(n)};
    }

    /** Looking up a feed */
    private static Feed getFeed(Node node) {
        InstanceCookie ck = node.getCookie(InstanceCookie.class);
        if (ck == null) {
            throw new IllegalStateException("Bogus file in feeds folder: " + node.getLookup().lookup(FileObject.class));
        }
        try {
            return (Feed) ck.instanceCreate();
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
}
