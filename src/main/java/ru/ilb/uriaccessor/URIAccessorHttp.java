/*
 * Copyright 2020 slavb.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.ilb.uriaccessor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

public class URIAccessorHttp extends URIAccessorImpl {

    private final static String LAST_MODIFIED = "Last-modified";
    private final static String CONTENT_TYPE = "Content-type";
    private static final String CACHE_CONTROL = "Cache-control";

    public URIAccessorHttp(URI uri) {
        super(uri);
    }

    /**
     * performs build with http cache
     *
     * @throws java.io.IOException
     */
    @Override
    protected void build() throws IOException {
        Path storagePath = getStorage();
        Files.createDirectories(storagePath);
        Path contentPath = getStorageContent();
        localUri = contentPath.toUri();

        final HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
        if (Files.exists(contentPath)) {
            FileTime lastModifiedTime = Files.getLastModifiedTime(contentPath);
            conn.setIfModifiedSince(lastModifiedTime.toMillis());
        }
        // override default Accept [text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2]
        // to request raw xml responses
        conn.setRequestProperty("Accept", "*/*");

        conn.connect();

        Map<String, String> headers;
        switch (conn.getResponseCode()) {
            case HttpURLConnection.HTTP_OK:
            case HttpURLConnection.HTTP_NO_CONTENT:
                headers = conn.getHeaderFields().entrySet().stream()
                        .filter(me -> me.getKey() != null)
                        .collect(Collectors.toMap(me -> me.getKey(), me -> me.getValue().get(0)));

                lastModified = Instant.ofEpochMilli(conn.getLastModified());
                contentType = conn.getContentType();
                writeMeta(headers);
                writeContent(conn.getInputStream());

                // TODO: calculate last access time in future based on Cache-Control / Expiry to skip cache refresh
                // @see org.apache.cxf.jaxrs.client.cache.CacheControlClientReaderInterceptor.computeExpiry
                //Files.getFileAttributeView(uriPathData, BasicFileAttributeView.class).setTimes(lastModifiedTime, null, null);
                break;

            case HttpURLConnection.HTTP_NOT_MODIFIED:
                lastModified = Instant.ofEpochMilli(conn.getLastModified());
                headers = readMeta();
                contentType = headers.get(CONTENT_TYPE);
                break;
            default:
                throw new RuntimeException("HTTP response code " + conn.getResponseCode() + " not implemented");
        }
    }
}
