/*
 * Copyright 2020 andrewsych.
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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author andrewsych
 */
public class URIStorageImplTest {

    public URIStorageImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of registerUri method, of class URIStorageImpl.
     * @throws java.io.IOException
     */
    @Test
    public void testRegisterUri_URI_String() throws IOException, URISyntaxException {
        System.out.println("registerUri");
        URI uri = this.getClass().getResource("test1.pdf").toURI();
        final URIStorageFactory uriStorageFactory = new URIStorageFactory();

        String contentType = "application/json";

        uriStorageFactory.getURIStorage().registerUri(uri, contentType);
        URIAccessorFactory factory = new URIAccessorFactory();

        URIAccessor acc = factory.getURIAccessor(uri);
        String expResult = "application/json";
        assertEquals(expResult, acc.getContentType());
    }

}
