package com.chatop.rentalbackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Jérémy MULET on 20/08/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormDataRental {

    private String name;

    private String surface;

    private String price;

    private String picture;

    private String description;
}
