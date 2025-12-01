package com.tensei.order_bot.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    Long id;

    String productName;

    String text;

    LocalDateTime date;

    Double amount;

}
