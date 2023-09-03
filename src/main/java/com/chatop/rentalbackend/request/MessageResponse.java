package com.chatop.rentalbackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jérémy MULET on 31/08/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private String message;
}
