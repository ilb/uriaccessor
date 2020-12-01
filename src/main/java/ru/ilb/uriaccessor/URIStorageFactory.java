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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author slavb
 */
public class URIStorageFactory {

    /**
     * base path for all uri
     */
    private final Path path;

    public URIStorageFactory(Path path) {
        this.path = path;
    }

    public URIStorageFactory() {
        String tempDir = System.getProperty("java.io.tmpdir");
        String userName = System.getProperty("user.name");
        this.path = Paths.get(tempDir, "URIStorage-" + userName);
        try {
            Files.createDirectories(path);
        } catch (IOException ex) {
            throw new URIAccessorException(ex);
        }

    }

    public URIStorage getURIStorage() {
        return new URIStorageImpl(path);
    }
}
