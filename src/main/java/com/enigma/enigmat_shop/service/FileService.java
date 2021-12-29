package com.enigma.enigmat_shop.service;

import com.enigma.enigmat_shop.entity.File;
import com.enigma.enigmat_shop.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileResponse create(MultipartFile multipartFile);

    File get(String id);
}
