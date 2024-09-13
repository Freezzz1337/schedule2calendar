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
        Mat matImage = bufferedImageToMat(inputImage);

        Mat grayImage = new Mat();
        Imgproc.cvtColor(matImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        Mat binaryImage = new Mat();
        Imgproc.adaptiveThreshold(grayImage, binaryImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
                Imgproc.THRESH_BINARY, 9, 7);

        Mat denoisedImage = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1, 1));
        Imgproc.morphologyEx(binaryImage, denoisedImage, Imgproc.MORPH_CLOSE, kernel);

        Mat scaledImage = scaleImage(denoisedImage, 4);

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
