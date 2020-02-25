package com.kozhukhar.carshop.creator;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;

public class TruckAdditionImpl extends TransportAddition {

    private static final String TRUCK = "TRUCK";

    public Object createTransport(String transportInfo, Boolean mode, CreatorAnnotation creatorAnnotation) {
        if (transportInfo == null) {
            return createByReflection(mode, TRUCK, creatorAnnotation);
        }
        return createCommonTransport(transportInfo, mode, TRUCK, creatorAnnotation);
    }

}
