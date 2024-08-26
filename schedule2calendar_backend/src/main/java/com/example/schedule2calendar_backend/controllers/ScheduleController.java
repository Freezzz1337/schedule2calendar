package com.example.schedule2calendar_backend.controllers;

import com.example.schedule2calendar_backend.dto.GraphImageDTO;
import com.example.schedule2calendar_backend.services.ExcelWriterService;
import com.example.schedule2calendar_backend.services.ImageDecoderService;
import com.example.schedule2calendar_backend.services.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
public class ScheduleController {
    private final ExcelWriterService excelWriterService;
    private final OCRService ocrService;
    private final ImageDecoderService imageDecoderService;

    @Autowired
    public ScheduleController(ExcelWriterService excelWriterService, OCRService ocrService, ImageDecoderService imageDecoderService) {
        this.excelWriterService = excelWriterService;
        this.ocrService = ocrService;
        this.imageDecoderService = imageDecoderService;
    }

    @PostMapping("/graph/post-image")
    public void graphImage(@RequestBody GraphImageDTO graphImageDTO) {
        try {
            BufferedImage image = imageDecoderService.decodeBase64ToImage(graphImageDTO.getGraphImage());
            String scheduleText = ocrService.extractTextFromImage(image);
            byte[] excelFile = excelWriterService.createExcelFile(scheduleText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @PostMapping("/graph/post-image")
//    public void graphImage(@RequestBody GraphImageDTO graphImageDTO){
//        excelWriterService.graphImage(graphImageDTO);
//    }
}
