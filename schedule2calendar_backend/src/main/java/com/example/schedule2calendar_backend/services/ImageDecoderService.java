package com.example.schedule2calendar_backend.services;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class ImageDecoderService {

    public BufferedImage decodeBase64ToImage(String base64String) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes)) {
            BufferedImage image = ImageIO.read(bis);
            saveImageToFile(image, "path/to/save/image.png");
            return image;
        }
    }

    private void saveImageToFile(BufferedImage image, String filePath) throws IOException {
        File file = new File("img2.png");
        if (file.exists()) {
            file.delete();
        }
        ImageIO.write(image, "png", file);
    }
}




//@Service
//public class ImageDecoderService {
//    public BufferedImage decodeBase64ToImage(String base64String) throws IOException {
//        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
//        try (ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes)) {
//            return ImageIO.read(bis);
//        }
//    }
//}