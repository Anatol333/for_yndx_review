package com.kozhukhar.carshop_online.db.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    private Integer id;

    private Float price;

    private String status;

    private String message;

    private LocalDateTime dateTime;

    private Integer userID;

    private String payType;

    private List<OrderedProduct> products;

}
