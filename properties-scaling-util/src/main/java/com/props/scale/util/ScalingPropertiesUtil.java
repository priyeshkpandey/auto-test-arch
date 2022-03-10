package com.props.scale.util;

import org.apache.commons.collections4.map.LRUMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ScalingPropertiesUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ScalingPropertiesUtil.class);

    private static int MAX_CACHE_SIZE_PROPERTIES = 20;
    private static final LRUMap<String, String> lruMap = new LRUMap<>(MAX_CACHE_SIZE_PROPERTIES);

    private static int MAX_CACHE_SIZE_FILES = 2;
    private static final LRUMap<String, Properties> lruMapFiles = new LRUMap<>(MAX_CACHE_SIZE_FILES);

    public static String getFromPath(final String propPath, final String key) throws Exception {
        final String internalKey = propPath + "_" + key;
        if (isCacheHit(internalKey)) {
            return lruMap.get(internalKey);
        }
        final String value = getPropertiesFromPath(propPath).getProperty(internalKey);
        putInProps(internalKey, value);
        return value;
    }

    private static Properties getPropertiesFromPath(final String propPath) throws Exception {
        if (isCacheHitFiles(propPath)) {
            return lruMapFiles.get(propPath);
        }
        Properties props = new Properties();
        InputStream propsFis = Thread.currentThread().getContextClassLoader().getResourceAsStream(propPath);
        if (null == propsFis) {
            LOG.debug("Trying input stream with ClassLoader");
            propsFis = ClassLoader.getSystemResourceAsStream(propPath);
        }
        if (null == propsFis) {
            LOG.debug("Trying input stream with File");
            propsFis = new FileInputStream(propPath);
        }
        props.load(propsFis);
        putInFiles(propPath, props);
        return props;
    }

    private static synchronized void putInProps(final String key, final String value) {
        if (null != value && !value.isEmpty()) {
            lruMap.put(key, value);
        }
    }

    private static synchronized void putInFiles(final String propPath, final Properties props) {
        if (null != props) {
            lruMapFiles.put(propPath, props);
        }
    }

    private static boolean isCacheHit( final String key) {
        return lruMap.containsKey(key);
    }

    private static boolean isCacheHitFiles(final String keyFileName) {
        return lruMapFiles.containsKey(keyFileName);
    }
}
