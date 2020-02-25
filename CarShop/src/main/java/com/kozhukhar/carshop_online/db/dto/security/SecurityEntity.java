package com.kozhukhar.carshop_online.db.dto.security;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityEntity {

    @XmlElement(name = "url-pattern")
    private String url;

    @XmlElementWrapper(name = "user-roles")
    @XmlElement(name = "role")
    private List<String> roles;

}
