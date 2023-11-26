package com.epf.back_end.interfaces.impl;

import com.epf.back_end.dao.ImageDao;
import com.epf.back_end.interfaces.ImageService;
import com.epf.back_end.models.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDao imageDao;
    @Override
    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()){
                int count = inflater.inflate(buffer);
                outputStream.write(buffer,0,count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            throw new RuntimeException(e);
        }
        return new byte[0];
    }

    @Override
    public Image getImageByName(String name) throws IOException {
        Optional<Image> retrievedImage = imageDao.findByTitle(name);
        if(retrievedImage.isPresent()){
            return new Image(retrievedImage.get().getTitle(),retrievedImage.get().getType(),decompressBytes(retrievedImage.get().getImage_data()));}
        else {
            throw new IOException();
        }
    }

    @Override
    public Image getImageById(Long id) throws IOException {
        Optional<Image> retrievedImage = imageDao.findById(id);
        if(retrievedImage.isPresent()){
            return new Image(retrievedImage.get().getTitle(),retrievedImage.get().getType(),decompressBytes(retrievedImage.get().getImage_data()));}
        else {
            throw new IOException();
        }
    }

    @Override
    public Image addImage(MultipartFile file) throws IOException {
        Image image = new Image(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        return imageDao.save(image);
    }

    @Override
    public Image updateImage(Long id, MultipartFile file) throws IOException {
        Optional<Image> existingImageOptional = imageDao.findById(id);
        if (existingImageOptional.isPresent()) {
            Image existingImage = existingImageOptional.get();

            existingImage.setTitle(file.getOriginalFilename());
            existingImage.setType(file.getContentType());
            existingImage.setImage_data(compressBytes(file.getBytes())); // Mettre à jour les données de l'image

            return imageDao.save(existingImage);
        } else {
            throw new RuntimeException("Image not found with id: " + id);
        }
    }

    @Override
    public List<Image> getAllImages() throws IOException {
        return imageDao.findAll();
    }


}
