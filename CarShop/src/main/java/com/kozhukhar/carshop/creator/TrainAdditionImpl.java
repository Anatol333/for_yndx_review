package com.kozhukhar.carshop.creator;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;
import com.kozhukhar.carshop.entity.Car;
import com.kozhukhar.carshop.entity.Train;
import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop.entity.Truck;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrainAdditionImpl extends TransportAddition {

    private static final String TRAIN = "TRAIN";

    public Object createTransport(String transportInfo, Boolean mode, CreatorAnnotation creatorAnnotation) {
        if (transportInfo == null) {
            return createByReflection(mode, TRAIN, creatorAnnotation);
        }
        return createCommonTransport(transportInfo, mode, TRAIN, creatorAnnotation);
    }
}
