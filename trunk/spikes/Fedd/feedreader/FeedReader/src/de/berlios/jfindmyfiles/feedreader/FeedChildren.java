/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import java.beans.IntrospectionException;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class FeedChildren extends Children.Keys {

    private final SyndFeed feed;

    public FeedChildren(SyndFeed feed) {
        this.feed = feed;
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    protected void addNotify() {
        setKeys(feed.getEntries());
    }

    public Node[] createNodes(Object key) {

        //Return new article-level nodes:
        try {
            return new Node[]{
                new EntryBeanNode((SyndEntry) key)
            };

        } catch (final IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
            //Should never happen, no reason for it to fail above:
            return new Node[]{new AbstractNode(Children.LEAF) {

            @Override
            public String getHtmlDisplayName() {
                return "" + ex.getMessage() + "";
            }
            }
            };
        }
    }
}
