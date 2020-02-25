package com.kozhukhar.carshop.anotation;

import com.kozhukhar.carshop.creator.TransportAddition;
import com.kozhukhar.carshop.creator.TransportAdditionImpl;
import com.kozhukhar.carshop.entity.embeded.TransportEmbedded;
import org.junit.Before;
import org.junit.Test;

public class CreatorAnnotationTest {

    private CreatorAnnotation creatorAnnotation;

    @Before
    public void setUp() throws Exception {
        creatorAnnotation = new CreatorAnnotation();
    }

    @Test
    public void testAnnotationTranslate() {
        TransportEmbedded transportEmbedded = new TransportEmbedded();
        creatorAnnotation.regObject(transportEmbedded);

        transportEmbedded.setModel("Some Model");
        transportEmbedded.setName("Some Name");
    }

    @Test
    public void testAdditionAnnotationTranslate() {
        TransportAddition transportAddition = new TransportAdditionImpl();
        transportAddition.createTransport(null, false, new CreatorAnnotation());
    }
}