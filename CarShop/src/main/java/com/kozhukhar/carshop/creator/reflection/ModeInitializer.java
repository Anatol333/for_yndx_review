package com.kozhukhar.carshop.creator.reflection;

public class ModeInitializer {

    public ReflectionCreator getReflectionCreator(Boolean mode, String typeOfTransport) {
        if (mode) {
            return new ConsoleReflectionCreator();
        }
        RandomReflectionCreator creator = new RandomReflectionCreator();
        creator.setType(typeOfTransport);
        return creator;
    }

}
