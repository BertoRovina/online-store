package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.services.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgFromFile(MultipartFile uploadFile){
        String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());

        if (!ext.equals("png") && !ext.equals("jpg")){
            throw new FileException("Only png or jpg images are accepted");
        }

        try {
            BufferedImage image = ImageIO.read(uploadFile.getInputStream());
            if(ext.equals("png")){
                image = pngToJpg(image);
            }
            return image;
        } catch (IOException e) {
            throw new FileException("Error while trying to read file");
        }
    }

    public BufferedImage pngToJpg(BufferedImage image) {
        BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage image, String extension){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo");
        }
    }

}
