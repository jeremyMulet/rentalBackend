package com.chatop.rentalbackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * Created by Jérémy MULET on 01/09/2023.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PictureResponse {

    private File picture;
}
