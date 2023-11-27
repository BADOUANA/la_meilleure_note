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
        // Create a Deflater object for compressing data
        Deflater deflater = new Deflater();
        // Set the input data for compression
        deflater.setInput(data);
        deflater.finish();

        // Create a ByteArrayOutputStream to store the compressed data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Create a buffer to hold chunks of compressed data
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            // Compress a chunk of data into the buffer and get the count of compressed bytes
            int count = deflater.deflate(buffer);
            // Write the compressed data from the buffer to the ByteArrayOutputStream
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
        // Create an Inflater object for decompressing data
        Inflater inflater = new Inflater();
        // Set the input data for decompression
        inflater.setInput(data);

        // Create a ByteArrayOutputStream to store the decompressed data
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Create a buffer to hold chunks of decompressed data
        byte[] buffer = new byte[1024];
        try {
            // Continue decompressing data until the Inflater has finished
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
            // If the image is found, return a new Image instance with the decompressed data
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
        // Create a new Image instance with the compressed data from the file and save it to the database
        Image image = new Image(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        return imageDao.save(image);
    }

    @Override
    public Image updateImage(Long id, MultipartFile file) throws IOException {
        Optional<Image> existingImageOptional = imageDao.findById(id);
        if (existingImageOptional.isPresent()) {
            Image existingImage = existingImageOptional.get();
            // If the existing image is found, update its properties with information from the new file

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
