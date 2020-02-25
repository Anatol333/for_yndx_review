package com.kozhukhar.carshop.creator;

import com.kozhukhar.carshop.anotation.CreatorAnnotation;
import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TransportAddition {

    public Integer getInt() {
        Random random = new Random();
        return Integer.valueOf(String.format("%04d", random.nextInt(10000)));
    }

    Transport createCommonTransport(String transportInfo, Boolean mode, String typeOfTransport, CreatorAnnotation creatorAnnotation) {
        TransportEmbedded transportEmbedded = new TransportEmbedded();
        Transport transport = new Transport();

        if (!mode) {
            transport.setPrice(getInt());
            transportEmbedded.setName(typeOfTransport);
            transportEmbedded.setModel(String.valueOf(getInt()));
            transport.setKey(transportEmbedded);
        } else {
            Pattern p = Pattern.compile("(.*) (.*) (.*) (.*)$");
            Matcher m = p.matcher(transportInfo);
            if (m.find()) {
                transport.setPrice(Integer.valueOf(m.group(4)));
                transportEmbedded.setName(m.group(2));
                transportEmbedded.setModel(m.group(3));
                transport.setKey(transportEmbedded);
            }
        }

        return transport;
    }

    Transport createByReflection(Boolean mode, String typeOfTransport, CreatorAnnotation creatorAnnotation) {
        TransportEmbedded transportEmbedded = new TransportEmbedded();
        Transport transport = new Transport();

        creatorAnnotation.setMode(mode);
        creatorAnnotation.setType(typeOfTransport);
        creatorAnnotation.regObject(transportEmbedded, transport);
        transport.setKey(transportEmbedded);
        return transport;
    }

    public abstract Object createTransport(String transportInfo, Boolean modeGeneration, CreatorAnnotation creatorAnnotation);
}
