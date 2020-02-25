package com.kozhukhar.carshop.creator.type;

import com.kozhukhar.carshop.creator.CarAdditionImpl;
import com.kozhukhar.carshop.creator.TransportAddition;
import com.kozhukhar.carshop.creator.TransportAdditionImpl;
import com.kozhukhar.carshop.creator.TruckAdditionImpl;

import java.util.HashMap;
import java.util.Map;

public class TransportMap {

    private Map<Integer, TransportAddition> map;

    public TransportMap() {
        map = new HashMap<>();
        initMap();
    }

    private void initMap() {
        map.put(1, new TransportAdditionImpl());
        map.put(2, new CarAdditionImpl());
        map.put(3, new TruckAdditionImpl());
        map.put(4, new TransportAdditionImpl());
    }

    public TransportAddition getTransportByNumber(Integer number) {
        return map.get(number);
    }

}
