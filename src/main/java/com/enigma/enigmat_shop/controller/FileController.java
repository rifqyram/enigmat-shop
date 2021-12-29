package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.entity.File;
import com.enigma.enigmat_shop.response.FileResponse;
import com.enigma.enigmat_shop.service.FileService;
import com.enigma.enigmat_shop.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            },
            produces = "application/json"
    )
    public ResponseEntity<WebResponse<FileResponse>> createNewFile(
            @RequestPart(name = "file") MultipartFile multipartFile) {
        FileResponse fileResponse = fileService.create(multipartFile);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new WebResponse<>("Successfully upload file", fileResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        File file = fileService.get(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + file.getName() + "\"")
                .body(file.getData());
    }
}
