package com.example.schedule2calendar_backend.util;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ImagePreprocessor {

    static {
        nu.pattern.OpenCV.loadLocally();
    }

    public BufferedImage preprocessImage(BufferedImage inputImage) {
        // Convert BufferedImage to Mat
        Mat matImage = bufferedImageToMat(inputImage);

        // Convert to black and white
        Mat grayImage = new Mat();
        Imgproc.cvtColor(matImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Changing the image scale
        Mat scaledImage = scaleImage(grayImage, 3);

        // Convert Mat back to BufferedImage
        BufferedImage processedImage = matToBufferedImage(scaledImage);

        return processedImage;
    }

    private Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(mat.width(), mat.height(), type);
        mat.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
        return image;
    }

    private Mat scaleImage(Mat src, double scaleFactor) {
        Mat resizedImage = new Mat();
        Imgproc.resize(src, resizedImage, new Size(src.width() * scaleFactor, src.height() * scaleFactor), 0, 0, Imgproc.INTER_CUBIC);
        return resizedImage;
    }
}
