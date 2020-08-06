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

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author slavb
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class URIMeta implements Serializable{

    protected final URI uri;

    protected final String contentType;

    public URIMeta(URI uri, String contentType) {
        this.uri = uri;
        this.contentType = contentType;
    }
    
    public URIMeta() {
        this.uri = null;
        this.contentType = null;
    }
    
    public URI getUri() {
        return uri;
    }

    public String getContentType() {
        return contentType;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof URIMeta)) {
            return false;
        }
        URIMeta uriMeta = (URIMeta) o;
        if(!uriMeta.getContentType().equals(this.contentType))
            return false;
        return uriMeta.getUri().equals(this.uri);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.uri);
        hash = 83 * hash + Objects.hashCode(this.contentType);
        return hash;
    }

}
