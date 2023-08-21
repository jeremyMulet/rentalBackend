package com.chatop.rentalbackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Jérémy MULET on 21/08/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {

    private Long id;

    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    private String picture;

    private String description;

    private Long owner_id;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}