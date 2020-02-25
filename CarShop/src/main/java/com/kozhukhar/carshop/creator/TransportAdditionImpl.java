package com.kozhukhar.carshop.creator;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;

public class TransportAdditionImpl extends TransportAddition {

    private static final String TRANSPORT = "TRANSPORT";

    public Object createTransport(String transportInfo, Boolean mode, CreatorAnnotation creatorAnnotation) {
        if (transportInfo == null) {
            return createByReflection(mode, TRANSPORT, creatorAnnotation);
        }
        return createCommonTransport(transportInfo, mode, TRANSPORT, creatorAnnotation);
    }
}
