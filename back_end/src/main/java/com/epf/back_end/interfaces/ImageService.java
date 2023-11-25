package com.epf.back_end.interfaces;

import com.epf.back_end.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    byte[] compressBytes(byte[] data);

    byte[] decompressBytes(byte[] data);

    Image getImageByName(String name) throws IOException;

    Image getImageById(Long id) throws IOException;

    Image addImage(MultipartFile file)throws IOException;

}
