/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import com.sun.syndication.feed.synd.SyndFeed;
import java.awt.Image;
import java.beans.IntrospectionException;
import java.io.IOException;

//import org.openide.actions.DeleteAction;
import javax.swing.Action;
import org.openide.actions.DeleteAction;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Administrador
 */
public class OneFeedNode extends FilterNode {

    OneFeedNode(Node feedFileNode, SyndFeed feed) throws IOException, IntrospectionException {
        super(feedFileNode,
                new FeedChildren(feed),
                new ProxyLookup(
                new Lookup[]{Lookups.fixed(
            new Object[]{feed}),
            feedFileNode.getLookup()
        }));
    }

    @Override
    public String getDisplayName() {
        SyndFeed feed = getLookup().lookup(SyndFeed.class);
        return feed.getTitle();
    }

    @Override
    public Image getIcon(int type) {
        return Utilities.loadImage("spikes/Fedd/rss16.gif");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(0);
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{SystemAction.get(DeleteAction.class)};
    }
}
