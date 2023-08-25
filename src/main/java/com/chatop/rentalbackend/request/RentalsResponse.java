package com.chatop.rentalbackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


/**
 * Created by Jérémy MULET on 25/08/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalsResponse {
    private ArrayList<RentalResponse> rentals;
}
