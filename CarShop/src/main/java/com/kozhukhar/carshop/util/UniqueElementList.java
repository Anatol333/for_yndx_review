package com.kozhukhar.carshop.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

public class UniqueElementList<T> extends ArrayList<T> {

    @Override
    public boolean add(T element) {
        if (contains(element)) throw new IllegalStateException();
        return super.add(element);
    }

    @Override
    public void add(int index, T element) {
        if (contains(element)) throw new IllegalStateException();
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.stream().anyMatch(this::contains)) throw new IllegalStateException();
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (collection.stream().anyMatch(this::contains)) throw new IllegalStateException();
        return super.addAll(index, collection);
    }

    @Override
    public boolean contains(Object currentObject) {
        if (isMyCompareCondition(currentObject)) return false;
        return super.contains(currentObject);
    }

    /**
     * If class object is equals but class name is not,
     * it's condition for adding similar element.
     *
     * @param object
     * @return
     */
    private boolean isMyCompareCondition(Object object) {
        return IntStream.range(0, toArray().length)
                .anyMatch(i -> !object.getClass().getName()
                        .equals(toArray()[i].getClass().getName())
                        && object.equals(toArray()[i]));
    }
}
