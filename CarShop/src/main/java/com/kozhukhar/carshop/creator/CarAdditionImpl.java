package com.kozhukhar.carshop.creator;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;

public class CarAdditionImpl extends TransportAddition {

    private static final String CAR = "CAR";

    public Object createTransport(String transportInfo, Boolean mode, CreatorAnnotation creatorAnnotation) {
        if (transportInfo == null) {
            return createByReflection(mode, CAR, creatorAnnotation);
        }
        return createCommonTransport(transportInfo, mode, CAR, creatorAnnotation);
    }
}
