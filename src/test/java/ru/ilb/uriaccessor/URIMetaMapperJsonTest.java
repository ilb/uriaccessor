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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author slavb
 */
public class URIMetaMapperJsonTest {

    public URIMetaMapperJsonTest() {
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
     * Test of unmarshall method, of class URIMetaMapperJson.
     */
    @Test
    @Disabled
    public void testUnmarshall() {
        System.out.println("unmarshall");
        String content = "{\"uri\": \"test\",\"contentType\":\"application/xml\"}";
        URIMetaMapperJson instance = new URIMetaMapperJson();
        URIMeta expResult = new URIMeta(URI.create("test"), "application/xml");
        URIMeta result = instance.unmarshall(content);
        assertEquals(expResult, result);
    }

    /**
     * Test of marshall method, of class URIMetaMapperJson.
     */
    @Test
    @Disabled
    public void testMarshall() {
        System.out.println("marshall");
        URIMeta uriMeta = new URIMeta(URI.create("test"), "application/xml");
        URIMetaMapperJson instance = new URIMetaMapperJson();
        String expResult = "{\"uri\": \"test\",\"contentType\":\"application/xml\"}";
        String result = instance.marshall(uriMeta);
        assertEquals(expResult, result);
    }

}
