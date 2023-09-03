package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.request.PictureResponse;
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

//    @GetMapping("/{fileName}")
//    public ResponseEntity<PictureResponse> getPicture(@PathVariable String fileName) {
//        return ResponseEntity.ok(PictureResponse.builder()
//                .picture()
//                .build());
//    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getPicture(@PathVariable String fileName) throws IOException {
        Resource imgFile = new ClassPathResource("pictures/" + fileName);

        // Determine the content type of the image
        String contentType = determineContentType(fileName);

        if (contentType == null) {
            // Handle the case where the file type is unsupported
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
        // Add more image types if needed

        return null;  // or throw an exception if you prefer
    }
}
