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

/**
 *
 * @author slavb
 */
public class URIMeta {
    protected final URI uri;

    protected final String contentType;

    public URIMeta(URI uri, String contentType) {
        this.uri = uri;
        this.contentType = contentType;
    }

    public URI getUri() {
        return uri;
    }

    public String getContentType() {
        return contentType;
    }

}
