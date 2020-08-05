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
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author slavb
 */
public class URIAccessorFileTest {

    public URIAccessorFileTest() {
    }

    /**
     * Test of build method, of class URIAccessorFile.
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        URI uri = this.getClass().getResource("test.pdf").toURI();
        assertEquals("file", uri.getScheme(), "input should have file:// scheme");

        URIAccessorFile instance = new URIAccessorFile(uri);
        assertEquals("application/pdf", instance.getContentType());
        assertEquals(Files.getLastModifiedTime(Paths.get(uri)).toInstant(), instance.getLastModified());

        instance = new URIAccessorFile(uri,"application/x-pdf");
        assertEquals("application/x-pdf", instance.getContentType());
    }
}
