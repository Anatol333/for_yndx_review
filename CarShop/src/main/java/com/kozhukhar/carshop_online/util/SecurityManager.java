package com.kozhukhar.carshop_online.util;

import com.kozhukhar.carshop_online.db.dto.security.Security;
import com.kozhukhar.carshop_online.db.dto.security.SecurityEntity;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kozhukhar.carshop_online.web.resource_tag.PagePaths.SECURITY_FILE_PATH;

public class SecurityManager {

    private final Map<String, List<String>> securityMap;

    public SecurityManager() throws AppException {
        Map<String, List<String>> newSecurityMap = new HashMap<>();
        initSecurityMap(newSecurityMap);
        securityMap = newSecurityMap;
    }

    private void initSecurityMap(Map<String, List<String>> newSecurityMap) throws AppException {
        try {
            JAXBContext jc = JAXBContext.newInstance(Security.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Security security = (Security) unmarshaller.unmarshal(new File(SECURITY_FILE_PATH));

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            for (SecurityEntity securityEntity : security.getSecurityEntities()) {
                newSecurityMap.put(securityEntity.getUrl(), securityEntity.getRoles());
            }
        } catch (JAXBException ex) {
            throw new AppException(Messages.SECURITY_WAS_NOT_INITIALIZED);
        }
    }

    public List<String> getRolesByUrl(String url) {
        return securityMap.get(url);
    }

    public Map<String, List<String>> getSecurityMap() {
        return securityMap;
    }
}
