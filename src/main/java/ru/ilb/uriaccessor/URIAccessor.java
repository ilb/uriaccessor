/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ilb.uriaccessor;

import java.net.URI;
import java.nio.file.Path;
import java.time.Instant;

/**
 * Resolves {@link URI} to local temp storage
 *
 * @author slavb
 */
public interface URIAccessor {

    /**
     * URI for which this accessor built
     *
     * @return
     */
    URI getUri();

    /**
     * get local uri (may be same as input uri for file:// scheme, for network resources may be local copy)
     *
     * @return
     */
    URI getLocalUri();

    Instant getLastModified();

    /**
     * reads uri contents
     *
     * @return
     */
    byte[] getContent();

    /**
     * content type of uri
     *
     * @return
     */
    String getContentType();

    /**
     * unique uri code
     *
     * @return
     */
    String getUriCode();

    /**
     * path to storage folder
     *
     * @return
     */
    Path getStorage();

}
