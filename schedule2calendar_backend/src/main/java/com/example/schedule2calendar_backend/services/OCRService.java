package com.example.schedule2calendar_backend.services;

import com.example.schedule2calendar_backend.util.ImagePreprocessor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class OCRService {

    private final ImagePreprocessor imagePreprocessor = new ImagePreprocessor();

    public String extractTextFromImage(BufferedImage image) {
        ITesseract instance = new Tesseract();
        instance.setDatapath("D:\\TON 618\\Java-poject\\Schedule2Calendar\\schedule2calendar_backend\\src\\main\\java\\com\\example\\schedule2calendar_backend\\tessdata");
        instance.setLanguage("pol");

        // Предварительная обработка изображения
        BufferedImage processedImage = imagePreprocessor.preprocessImage(image);

        // Сохранение обработанного изображения
        saveProcessedImage(processedImage, "path/to/save/processed_image.png"); // Укажите нужный путь и имя файла

        // Извлечение текста из обработанного изображения
        try {
            return instance.doOCR(processedImage);
        } catch (TesseractException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void saveProcessedImage(BufferedImage image, String filePath) {
        File file = new File("img1.png");
        try {
            // Удаление файла, если он уже существует
            if (file.exists()) {
                file.delete();
            }
            // Сохранение изображения в файл
            ImageIO.write(image, "png", file); // Вы можете указать другой формат, если нужно
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//@Service
//public class OCRService {
//    public String extractTextFromImage(BufferedImage image) {
//        ITesseract instance = new Tesseract();
//
//        instance.setDatapath("D:\\TON 618\\Java-poject\\Schedule2Calendar\\schedule2calendar_backend\\src\\main\\java\\com\\example\\schedule2calendar_backend\\tessdata");
//        instance.setLanguage("pol");
//
//        BufferedImage processedImage = new ImagePreprocessor().preprocessImage(image);
//
//        try {
//            return instance.doOCR(processedImage);
//        } catch (TesseractException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//}