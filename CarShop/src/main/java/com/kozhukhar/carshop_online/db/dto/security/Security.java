package com.kozhukhar.carshop_online.db.dto.security;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "Security")
@XmlAccessorType(XmlAccessType.FIELD)
public class Security {

    @XmlElementWrapper(name = "SecurityEntities")
    @XmlElement(name = "SecurityEntity")
    private List<SecurityEntity> securityEntities;

}
