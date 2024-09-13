package com.example.schedule2calendar_backend.services;

import com.example.schedule2calendar_backend.util.ImagePreprocessor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
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
        instance.setPageSegMode(6);

        BufferedImage processedImage = imagePreprocessor.preprocessImage(image);

        saveDebugImage(processedImage, "2AfterImproving.png");

        try {
            return instance.doOCR(processedImage);
        } catch (TesseractException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void saveDebugImage(BufferedImage image, String fileName) {
        File file = new File(fileName);
        try {
            if (file.exists()) {
                file.delete();
            }
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//@Service
//public class OCRService {
//
//    private final ImagePreprocessor imagePreprocessor = new ImagePreprocessor();
//
//    public String extractTextFromImage(BufferedImage image) {
//        ITesseract instance = new Tesseract();
//        instance.setDatapath("D:\\TON 618\\Java-poject\\Schedule2Calendar\\schedule2calendar_backend\\src\\main\\java\\com\\example\\schedule2calendar_backend\\tessdata");
//        instance.setLanguage("pol");
//
//        BufferedImage processedImage = imagePreprocessor.preprocessImage(image);
//
//        //-----------------------------------------------------
//        File file = new File("2AfterImproving.png");
//        try {
//            if (file.exists()) {
//                file.delete();
//            }
//            ImageIO.write(processedImage, "png", file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //-----------------------------------------------------
//        try {
//            return instance.doOCR(processedImage);
//        } catch (TesseractException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//}

//-------------------------------------------

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