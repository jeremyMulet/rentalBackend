package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.request.PictureResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by Jérémy MULET on 01/09/2023.
 */
@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
public class PictureController {

    @Operation(summary = "Retrieve a picture by its filename")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Picture fetched successfully")
    })
    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getPicture(@PathVariable String fileName) throws IOException {
        Resource imgFile = new ClassPathResource("pictures/" + fileName);
        String contentType = determineContentType(fileName);

        if (contentType == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imgFile);
    }

    private String determineContentType(String fileName) {
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        }

        return null;
    }
}
