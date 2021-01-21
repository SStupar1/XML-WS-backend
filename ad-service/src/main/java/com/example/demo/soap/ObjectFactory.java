package com.example.demo.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private interface QNames {
        QName createGearshiftTypeResponse = new QName(CarEndpoint.NAMESPACE_URI, "createGearshiftTypeResponse");
    }

    @XmlElementDecl(namespace = CarEndpoint.NAMESPACE_URI, name = "createGearshiftTypeResponse")
    public JAXBElement<Long> createCreateGearshiftTypeResponse(Long value) {
        return new JAXBElement<>(QNames.createGearshiftTypeResponse, Long.class, null, value);
    }

}
