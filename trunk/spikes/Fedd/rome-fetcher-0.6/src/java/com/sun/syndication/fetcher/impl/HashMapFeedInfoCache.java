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

import java.io.Serializable;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>A very simple implementation of the {@link com.sun.syndication.fetcher.impl.FeedFetcherCache} interface.</p>
 * 
 * <p>This implementation uses a HashMap to cache retrieved feeds. This implementation is
 * most suitible for sort term (client aggregator?) use, as the memory usage will increase
 * over time as the number of feeds in the cache increases.</p>
 * 
 * @author Nick Lothian
 *
 */
public class HashMapFeedInfoCache implements FeedFetcherCache, Serializable {
	static HashMapFeedInfoCache _instance;
	
	private Map infoCache;
	
	/**
	 * <p>Constructor for HashMapFeedInfoCache</p>
	 * 
	 * <p>Only use this if you want multiple instances of the cache. 
	 * Usually getInstance() is more appropriate.</p>
	 *
	 */
	public HashMapFeedInfoCache() {
		setInfoCache(Collections.synchronizedMap(new HashMap()));
	}

	/**
	 * Get the global instance of the cache
	 * @return an implementation of FeedFetcherCache
	 */
	public static synchronized FeedFetcherCache getInstance() {
		if (_instance == null) {
			_instance = new HashMapFeedInfoCache();			
		}
		return _instance;
	}

	protected Object get(Object key) {
		return infoCache.get(key);
	}

	/**
	 * @see extensions.io.FeedFetcherCache#getFeedInfo(java.net.URL)
	 */
	public SyndFeedInfo getFeedInfo(URL feedUrl) {
		return (SyndFeedInfo) get(feedUrl);
	}

	protected void put(Object key, Object value) {
		infoCache.put(key, value);
	}

	/**
	 * @see extensions.io.FeedFetcherCache#setFeedInfo(java.net.URL, extensions.io.SyndFeedInfo)
	 */
	public void setFeedInfo(URL feedUrl, SyndFeedInfo syndFeedInfo) {
		put(feedUrl, syndFeedInfo);		
	}

	protected Map getInfoCache() {
		return infoCache;
	}

	protected void setInfoCache(Map map) {
		infoCache = map;
	}

}
