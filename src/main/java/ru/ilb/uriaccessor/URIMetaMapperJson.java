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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import ru.ilb.common.jaxb.util.JaxbUtil;

public class URIMetaMapperJson implements URIMetaMapper {

    private final JAXBContext jaxbContext;

    public URIMetaMapperJson() {
        try {
            this.jaxbContext = JAXBContext.newInstance(ru.ilb.uriaccessor.URIMeta.class);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public URIMeta unmarshall(String content) {
        return (URIMeta) JaxbUtil.unmarshal(jaxbContext, content, ru.ilb.uriaccessor.URIMeta.class, "application/json");
    }

    @Override
    public String marshall(URIMeta uriMeta) {
        return JaxbUtil.marshal(jaxbContext, uriMeta, "application/json").replaceAll("\n", "");
    }
}
