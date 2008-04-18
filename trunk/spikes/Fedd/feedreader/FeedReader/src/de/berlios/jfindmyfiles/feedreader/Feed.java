/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Administrador
 */
public class Feed implements Serializable {

    private static FeedFetcher s_feedFetcher = new HttpURLFeedFetcher(HashMapFeedInfoCache.getInstance());
    private transient SyndFeed m_syndFeed;
    private URL m_url;
    private String m_name;

    protected Feed() {
    }

    public Feed(String str) throws MalformedURLException {
        m_url = new URL(str);
        m_name = str;
    }

    public URL getURL() {
        return m_url;
    }

    public SyndFeed getSyndFeed() throws IOException {
        if (m_syndFeed == null) {
            try {
                m_syndFeed = s_feedFetcher.retrieveFeed(m_url);
                if (m_syndFeed.getTitle() != null) {
                    m_name = m_syndFeed.getTitle();
                }
            } catch (Exception ex) {
                throw new IOException(ex.getMessage());
            }
        }
        return m_syndFeed;
    }

    @Override
    public String toString() {
        return m_name;
    }
}
