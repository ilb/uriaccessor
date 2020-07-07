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
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Map;
import org.json.JSONObject;

public abstract class URIAccessorImpl implements URIAccessor {

    protected final URI uri;

    protected URI localUri;

    protected Instant lastModified;

    protected String contentType;

    protected boolean builded;

    public URIAccessorImpl(URI uri) {
        this.uri = uri;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    /**
     * performs build once
     */
    protected void checkBuild() {
        try {
            if (!builded) {
                build();
            }
            builded = true;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * performs fetch of remote data to fill localUri, contentType, lastModified
     *
     * @throws java.io.IOException
     */
    protected abstract void build() throws IOException;

    @Override
    public URI getLocalUri() {
        checkBuild();
        return localUri;
    }

    @Override
    public String getContentType() {
        checkBuild();
        return contentType;
    }

    @Override
    public Instant getLastModified() {
        checkBuild();
        return lastModified;
    }

    @Override
    public String getUriCode() {
        return uri.toString().replaceAll("\\W+", "") + uri.hashCode();
    }

    @Override
    public Path getStorage() {
        String tempDir = System.getProperty("java.io.tmpdir");
        String userName = System.getProperty("user.name");
        return Paths.get(tempDir, "URIAccessorStorage-" + userName, getUriCode());
    }

    protected Path getStorageContent() {
        return getStorage().resolve("content");
    }

    protected Path getStorageMeta() {
        return getStorage().resolve("meta");
    }

    protected void writeContent(InputStream is) throws IOException {
        Files.copy(is, getStorageContent(), StandardCopyOption.REPLACE_EXISTING);
        FileTime lastModifiedTime = FileTime.from(lastModified);
        Files.setLastModifiedTime(getStorageContent(), lastModifiedTime);
    }

    protected void writeMeta(Map<String, String> meta) throws IOException {
        JSONObject json = new JSONObject(meta);
        Files.write(getStorageMeta(), json.toString().getBytes());
        FileTime lastModifiedTime = FileTime.from(lastModified);
        Files.setLastModifiedTime(getStorageMeta(), lastModifiedTime);
    }

    protected Map<String, String> readMeta() throws IOException {
        byte[] metaBytes = Files.readAllBytes(getStorageMeta());
        JSONObject json = new JSONObject(new String(metaBytes));
        return (Map<String, String>) (Object) json.toMap();
    }

}
