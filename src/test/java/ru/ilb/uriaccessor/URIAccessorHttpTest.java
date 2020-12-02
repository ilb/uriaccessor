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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.ilb.testhttpserver.TestHttpServerFile;

/**
 *
 * @author slavb
 */
public class URIAccessorHttpTest {

    public URIAccessorHttpTest() {
    }

    /**
     * Test of build method, of class URIAccessorHttp.
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        URI endpointAddress = URI.create("http://localhost:52341/api/endpoint?rnd=" + Math.random());

        URIAccessorHttp instance = new URIAccessorHttp(endpointAddress);

        Path source = Paths.get(this.getClass().getResource("test.pdf").toURI());
        try (TestHttpServerFile th = new TestHttpServerFile(endpointAddress.toURL(), source)) {
            assertEquals("file", instance.getLocalUri().getScheme(), "local file:// uri expected");
            assertEquals("application/octet-stream", instance.getContentType());
            assertEquals(Files.getLastModifiedTime(source).toInstant().truncatedTo(ChronoUnit.SECONDS), instance.getLastModified());
        }

    }

}
