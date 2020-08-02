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
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.codec.digest.DigestUtils;

public class URIStorageImpl implements URIStorage {

    private final Path path;
    
    private final URIMetaMapperJson uriMapper = new URIMetaMapperJson();

    public URIStorageImpl(Path path) {
        this.path = path;
    }

    @Override
    public String getUriCode(URI uri) {
        return DigestUtils.sha1Hex(uri.toString());
//        return uri.toString().replaceAll("\\W+", "") + uri.hashCode();
    }

//    @Override
//    public URI getUri(String uriCode) {
//        throw
//    }
    @Override
    public Path getStorage(URI uri) {
        return path.resolve(getUriCode(uri));
    }

    @Override
    public URI getUri(String uriCode) {
        try {
            // read original uri from storage
            // possible use another storage
            byte[] readAllBytes = Files.readAllBytes(path.resolve(uriCode + ".meta"));
            URIMeta uriMeta = uriMapper.unmarshall(new String(readAllBytes));
            return uriMeta.getUri();
        } catch (IOException ex) {
            throw new URIAccessorException(ex);
        }
    }

    @Override
    public String registerUri(URI uri, String contentType) {
        try {
            String uriCode = getUriCode(uri);
            Path uriPath = path.resolve(getUriCode(uri) + ".meta");
            URIMeta uriMeta = new URIMeta(uri, contentType);
            uriMapper.marshall(uriMeta);
            Files.write(uriPath, uriMapper.marshall(uriMeta).getBytes());
            return uriCode;
        } catch (IOException ex) {
            throw new URIAccessorException(ex);
        }
    }

}
