package com.kozhukhar.carshop_online.db.dto;

import lombok.Data;

@Data
public final class OrderedProduct {

    private final Integer productId;

    private final String name;

    private final Integer numbers;

    private final Float price;

}
