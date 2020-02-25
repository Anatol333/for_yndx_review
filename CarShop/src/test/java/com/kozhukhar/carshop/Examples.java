package com.kozhukhar.carshop;

import com.kozhukhar.carshop.entity.Car;
import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;

public class Examples {

    public static final Transport[] TRANSPORTS = {
            new Car(new TransportEmbedded("test1", "test1"), 200, 100000),
            new Car(new TransportEmbedded("test2", "test2"), 200, 100000),
            new Car(new TransportEmbedded("test3", "test3"), 200, 100000),
            new Car(new TransportEmbedded("test4", "test4"), 200, 100000),
            new Car(new TransportEmbedded("test5", "test5"), 200, 100000),
            new Car(new TransportEmbedded("test6", "test6"), 200, 100000),
    };

}
