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

import java.net.URI;
import java.nio.file.Path;

/**
 *
 * @author slavb
 */
public interface URIStorage {

    /**
     * get uri code of uri
     *
     * @param uri
     * @return
     */
    public String getUriCode(URI uri);

    /**
     * translate uriCode to uri using storage
     *
     * @param uriCode
     * @return
     */
    public URI getUri(String uriCode);

    /**
     * register uri in storage for back-resolving (uriCode to uri)
     *
     * @param uri
     * @return registered uriCode of uri
     */
    public String registerUri(URI uri);

    /**
     * register uri in storage for back-resolving (uriCode to uri)
     *
     * @param uri
     * @param contentType
     * @return registered uriCode of uri
     */
    public String registerUri(URI uri, String contentType);

    /**
     * get uri storage path
     *
     * @param uri
     * @return
     */
    public Path getStorage(URI uri);
    
        /**
     * get uri meta
     *
     * @return
     */
    public URIMeta getUriMeta();
}
