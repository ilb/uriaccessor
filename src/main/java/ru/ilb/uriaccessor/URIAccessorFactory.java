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

public class URIAccessorFactory {

    public URIAccessor getURIAccessor(URI uri) {
        return getURIAccessor(uri, null);
    }
    public URIAccessor getURIAccessor(URI uri, String contentType) {
        switch (uri.getScheme()) {
            case "file":
                return new URIAccessorFile(uri, contentType);
            case "http":
            case "https":
                return new URIAccessorHttp(uri);
            default:
                throw new IllegalArgumentException(uri.getScheme() + " not implemented");
        }
    }
}
