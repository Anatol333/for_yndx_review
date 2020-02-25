package com.kozhukhar.carshop.util;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class HashWrapper {

    private String stringField;

    private HashType customHash;

    public HashWrapper(String stringField) {
        this.stringField = stringField;
    }

    public HashWrapper(String stringField, HashType customHash) {
        this.stringField = stringField;
        this.customHash = customHash;
    }

    @Override
    public int hashCode() {
        if(customHash==null) return Objects.hash(stringField);
        return customHash.getHashForGeneration(stringField);
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public HashType getCustomHash() {
        return customHash;
    }

    public void setCustomHash(HashType customHash) {
        this.customHash = customHash;
    }

    @Override
    public String toString() {
        return "HashWrapper{" +
                "stringField='" + stringField + '\'' +
                '}';
    }
}
