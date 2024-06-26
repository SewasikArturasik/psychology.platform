package com.psychology.product.service.impl;

import com.psychology.product.repository.ImageRepository;
import com.psychology.product.repository.model.ImageDAO;
import com.psychology.product.service.ImageService;
import com.psychology.product.util.exception.BadRequestException;
import com.psychology.product.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public ImageDAO createImage(MultipartFile multipartFile) {
        ImageDAO image = new ImageDAO();
        image.setName(multipartFile.getName());
        image.setContentType(multipartFile.getContentType());
        image.setSize(multipartFile.getSize());
        try {
            image.setContent(multipartFile.getBytes());
        } catch (IOException e) {
            throw new BadRequestException("Could not read image file");
        }
        image.setCreatedTimestamp(Instant.now());
        return imageRepository.save(image);
    }

    @Override
    public Optional<ImageDAO> getImageById(UUID id) {
        return Optional.ofNullable(imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image not found")));
    }
}
