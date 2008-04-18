/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import com.sun.syndication.feed.synd.SyndEntry;
import java.beans.IntrospectionException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Action;
import org.openide.actions.OpenAction;
import org.openide.awt.HtmlBrowser.URLDisplayer;
import org.openide.cookies.OpenCookie;
import org.openide.nodes.BeanNode;
import org.openide.nodes.FilterNode;
import org.openide.util.Exceptions;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Administrador
 */
public class EntryBeanNode extends FilterNode {

    private SyndEntry entry;

    @SuppressWarnings(value = "unchecked")
    public EntryBeanNode(SyndEntry entry) throws IntrospectionException {
        super(new BeanNode(entry), Children.LEAF,
                Lookups.fixed(new Object[]{
            entry,
            new EntryOpenCookie(entry)
        }));
        this.entry = entry;
    }

    /** Using HtmlDisplayName ensures any HTML in RSS entry titles are
     * /**properly handled, escaped, entities resolved, etc. */
    @Override
    public String getHtmlDisplayName() {
        return entry.getTitle();
    }

    /** Making a tooltip out of the entry's description */
    @Override
    public String getShortDescription() {
        return entry.getDescription().getValue();
    }

    /** Providing the Open action on a feed entry */
    @Override
    public Action[] getActions(boolean popup) {
        return new Action[]{SystemAction.get(OpenAction.class)};
    }

    @Override
    public Action getPreferredAction() {
        return (SystemAction) getActions(false)[0];
    }

    /** Specifying what should happen when the user invokes the Open action */
    private static class EntryOpenCookie implements OpenCookie {

        private final SyndEntry entry;

        EntryOpenCookie(SyndEntry entry) {
            this.entry = entry;
        }

        public void open() {
            try {
                URLDisplayer.getDefault().showURL(new URL(entry.getUri()));
            } catch (MalformedURLException mue) {
                Exceptions.printStackTrace(mue);
            }
        }
    }
}
