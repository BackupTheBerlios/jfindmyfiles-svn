/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.sun.syndication.fetcher.impl;

import java.net.URL;

import junit.framework.TestCase;

import org.mortbay.http.BasicAuthenticator;
import org.mortbay.http.HashUserRealm;
import org.mortbay.http.HttpContext;
import org.mortbay.http.HttpServer;
import org.mortbay.http.SecurityConstraint;
import org.mortbay.http.SocketListener;
import org.mortbay.http.UserRealm;
import org.mortbay.http.handler.SecurityHandler;
import org.mortbay.jetty.servlet.ServletHandler;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherEvent;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.FetcherListener;

/**
 * @author nl
 */
public abstract class AbstractJettyTest extends TestCase {

	private HttpServer server;
	
	/**
	 * @param s
	 */
	public AbstractJettyTest(String s) {
		super(s);
	}

	protected HttpServer getServer() {
		return server;
	}

	protected abstract FeedFetcher getFeedFetcher();
	
	protected abstract FeedFetcher getFeedFetcher(FeedFetcherCache cache);
	
	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		setupServer();
	
		HttpContext context = createContext();
		
		ServletHandler servlets = createServletHandler();
		context.addHandler(servlets);
		
		server.addContext(context);		
		
		server.start();
	}

	/**
     * @throws InterruptedException
     */
    private void setupServer() throws InterruptedException {
        // Create the server
		if (server != null) {
			server.stop();
			server = null;
		}
		server = new HttpServer();
	
		// Create a port listener
		SocketListener listener=new SocketListener();
		listener.setPort(8080);
		server.addListener(listener);
    }

    /**
     * @return
     */
    private ServletHandler createServletHandler() {
        ServletHandler servlets = new ServletHandler();
		servlets.addServlet("FetcherTestServlet","/FetcherTestServlet/*","com.sun.syndication.fetcher.impl.FetcherTestServlet");
        return servlets;
    }

    /**
     * @return
     */
    private HttpContext createContext() {
        HttpContext context = new HttpContext();
		context.setContextPath("/rome/*");
        return context;
    }

    /**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		if (server != null) {
			server.stop();
			server.destroy();
			server = null;
		}
	}

	class FetcherEventListenerImpl implements FetcherListener {
		boolean polled = false;
		boolean retrieved = false;
		boolean unchanged = false;

		public void reset() {
			polled = false;
			retrieved = false;
			unchanged = false;
		}

		/**
		 * @see com.sun.syndication.fetcher.FetcherListener#fetcherEvent(com.sun.syndication.fetcher.FetcherEvent)
		 */
		public void fetcherEvent(FetcherEvent event) {
			String eventType = event.getEventType();
			if (FetcherEvent.EVENT_TYPE_FEED_POLLED.equals(eventType)) {
				System.err.println("\tEVENT: Feed Polled. URL = " + event.getUrlString());
				polled = true;
			} else if (FetcherEvent.EVENT_TYPE_FEED_RETRIEVED.equals(eventType)) {
				System.err.println("\tEVENT: Feed Retrieved. URL = " + event.getUrlString());
				retrieved = true;
			} else if (FetcherEvent.EVENT_TYPE_FEED_UNCHANGED.equals(eventType)) {
				System.err.println("\tEVENT: Feed Unchanged. URL = " + event.getUrlString());
				unchanged = true;
			}
		}
	}

	public void testRetrieveFeed() {
		FeedFetcher feedFetcher = getFeedFetcher();
		try {
			SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet/"));
			assertNotNull(feed);
			assertEquals("atom_0.3.feed.title", feed.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	public void testBasicAuthentication() {	    
		try {
            setupServer();
            
            HttpContext context = createContext();

            URL url = this.getClass().getResource("/testuser.properties");            
            UserRealm ur = new HashUserRealm("test", url.getFile());						
            context.setRealm(ur);

            BasicAuthenticator ba = new BasicAuthenticator();
            context.setAuthenticator(ba);		
            
            SecurityHandler sh =  new SecurityHandler();					
            context.addHandler(sh);

            SecurityConstraint sc = new SecurityConstraint();
            sc.setName("test");
            sc.addRole("*");
            sc.setAuthenticate(true);		
            context.addSecurityConstraint("/", sc);			
            
            ServletHandler servlets = createServletHandler();
            context.addHandler(servlets);
            
            server.addContext(context);		
            
            server.start();            
            
            FeedFetcher feedFetcher = getAuthenticatedFeedFetcher();
			SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet/"));
			assertNotNull(feed);
			assertEquals("atom_0.3.feed.title", feed.getTitle());
            
            
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }	    
	    
		
	}
	
	public abstract FeedFetcher getAuthenticatedFeedFetcher();
	
	/**
	 * Test getting a feed via a http 301 redirect
	 *
	 */
	public void testRetrieveRedirectedFeed() {
		FeedFetcher feedFetcher = getFeedFetcher();
		try {
			SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet?redirect=TRUE"));
			assertNotNull(feed);
			assertEquals("atom_0.3.feed.title", feed.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test error handling
	 *
	 */
	public void testErrorHandling() {
		FeedFetcher feedFetcher = getFeedFetcher();
		try {
			SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet?error=404"));
			fail("4xx error handling did not work correctly");
		} catch (FetcherException e) {
			// expect this exception
			assertEquals(404, e.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	
		try {
			SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet?error=500"));
			fail("5xx error handling did not work correctly");
		} catch (FetcherException e) {
			// expect this exception
			assertEquals(500, e.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	public void testUserAgent() {
		FeedFetcher feedFetcher = getFeedFetcher();
		//System.out.println(feedFetcher.getUserAgent());
		//System.out.println(System.getProperty("rome.fetcher.version", "UNKNOWN"));
		assertEquals("Rome Client (http://tinyurl.com/64t5n) Ver: " + System.getProperty("rome.fetcher.version", "UNKNOWN"), feedFetcher.getUserAgent());
	}

	/**
	 * Test events fired when there is no cache in use
	 *
	 */
	public void testFetchEvents() {
		FeedFetcher feedFetcher = getFeedFetcher();
		FetcherEventListenerImpl listener = new FetcherEventListenerImpl();
		feedFetcher.addFetcherEventListener(listener);
		try {
			SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet/"));
			assertNotNull(feed);
			assertTrue(listener.polled);
			assertTrue(listener.retrieved);
			assertFalse(listener.unchanged);
			listener.reset();
	
			// since there is no cache, the events fired should be exactly the same if
			// we re-retrieve the feed
			feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet/"));
			assertNotNull(feed);
			assertTrue(listener.polled);
			assertTrue(listener.retrieved);
			assertFalse(listener.unchanged);
			listener.reset();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/**
	 * Test events fired when there is a cache in use
	 *
	 */
	public void testFetchEventsWithCache() {
		FeedFetcherCache feedInfoCache = new HashMapFeedInfoCache();
		FeedFetcher feedFetcher = getFeedFetcher(feedInfoCache);
		FetcherEventListenerImpl listener = new FetcherEventListenerImpl();
		feedFetcher.addFetcherEventListener(listener);
		try {
			SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet/"));
			assertNotNull(feed);
			assertTrue(listener.polled);
			assertTrue(listener.retrieved);
			assertFalse(listener.unchanged);
			listener.reset();
	
			// Since the feed is cached, the second request should not
			// actually retrieve the feed
			feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet/"));
			assertNotNull(feed);
			assertTrue(listener.polled);
			assertFalse(listener.retrieved);
			assertTrue(listener.unchanged);
			listener.reset();
	
			// now simulate getting the feed after it has changed
			feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet?refreshfeed=TRUE"));
			assertNotNull(feed);
			assertTrue(listener.polled);
			assertTrue(listener.retrieved);
			assertFalse(listener.unchanged);
			listener.reset();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test handling of GZipped feed
	 *
	 */
	public void testGZippedFeed() {
	    FeedFetcher feedFetcher = getFeedFetcher();
		try {
			SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet?gzipfeed=TRUE"));
			assertNotNull(feed);
			assertEquals("atom_0.3.feed.title", feed.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	    
	}
	
	public void testDeltaEncoding() {
	    FeedFetcherCache feedInfoCache = new HashMapFeedInfoCache();
		FeedFetcher feedFetcher = getFeedFetcher(feedInfoCache);	    		
		try {
		    feedFetcher.setUsingDeltaEncoding(true);
		    
		    // first retrieval should just grab the default feed
			SyndFeed feed1 = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet?deltaencode=TRUE&refreshfeed=TRUE"));
			assertNotNull(feed1);
			assertEquals("atom_0.3.feed.title", feed1.getTitle());
			assertEquals(2, feed1.getEntries().size());
			SyndEntry entry1 = (SyndEntry) feed1.getEntries().get(0);
			assertEquals("atom_0.3.feed.entry[0].title", entry1.getTitle());
			
			// second retrieval should get only the new item
			/*
			 * This is breaking with Rome 0.5 ??
			 */ 
			SyndFeed feed2 = feedFetcher.retrieveFeed(new URL("http://localhost:8080/rome/FetcherTestServlet?deltaencode=TRUE&refreshfeed=TRUE"));					
			assertNotNull(feed2);
			assertEquals(FetcherTestServlet.DELTA_FEED_TITLE, feed2.getTitle());
			assertEquals(3, feed2.getEntries().size());
			entry1 = (SyndEntry) feed2.getEntries().get(0);
			assertEquals(FetcherTestServlet.DELTA_FEED_ENTRY_TITLE, entry1.getTitle());
			
			SyndEntry entry2 = (SyndEntry) feed2.getEntries().get(1);
			assertEquals("atom_0.3.feed.entry[0].title", entry2.getTitle());			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	    	    
	}
		
	
}
