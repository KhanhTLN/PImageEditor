package org.example.app;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;

public class ImageProcessor {

    // Convert image to grayscale
    public static BufferedImage convertToGrayscale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;
                int gray = (red + green + blue) / 3;  // Simple average method

                int newPixel = (gray << 16) | (gray << 8) | gray;
                grayImage.setRGB(x, y, newPixel);
            }
        }
        return grayImage;
    }


    // Convert image to negative
    public static BufferedImage convertToNegative(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage negativeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = inputImage.getRGB(x, y);
                Color color = new Color(rgb);

                // Invert colors
                Color negativeColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());

                negativeImage.setRGB(x, y, negativeColor.getRGB());
            }
        }

        return negativeImage;
    }

    // Apply Gaussian Blur
    public static BufferedImage applyGaussianBlur(BufferedImage inputImage) {
        float[] matrix = {
                1/16f, 2/16f, 1/16f,
                2/16f, 4/16f, 2/16f,
                1/16f, 2/16f, 1/16f
        };
        Kernel kernel = new Kernel(3, 3, matrix);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(inputImage, null);
    }

    // Crop image
    public static BufferedImage cropImage(BufferedImage inputImage, int x, int y, int width, int height) {
        return inputImage.getSubimage(x, y, width, height);
    }

    // Resize image
    public static BufferedImage resizeImage(BufferedImage inputImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(inputImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resizedImage;
    }

    // Adjust Brightness
    // 1.5f	Brighter image (50% increase)
    // 2.0f	Much brighter (doubles brightness)
    // 0.8f	Darker image (20% decrease)
    // 0.5f	Very dark image (50% decrease)
    // currently, 1.5f for testing, using Slider for UI later
    public static BufferedImage adjustBrightness(BufferedImage inputImage, float scaleFactor) {
        RescaleOp rescaleOp = new RescaleOp(scaleFactor, 0, null);
        return rescaleOp.filter(inputImage, null);
    }

    // Apply Edge Detection (Sobel Filter)
    public static BufferedImage applyEdgeDetection(BufferedImage inputImage) {
        float[] edgeKernel = {
                -1, -1, -1,
                -1,  8, -1,
                -1, -1, -1
        };
        Kernel kernel = new Kernel(3, 3, edgeKernel);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(inputImage, null);
    }

    // rotate 90 degree
    public static BufferedImage rotateImage(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage rotatedImage = new BufferedImage(height, width, inputImage.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int newX = y;
                int newY = width - 1 - x;
                rotatedImage.setRGB(newX, newY, inputImage.getRGB(x, y));
            }
        }
        return rotatedImage;
    }

    // Flipping
    // ABCD -> DCBA
    public static BufferedImage flipImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flippedImage = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                flippedImage.setRGB(width - 1 - x, y, image.getRGB(x, y));
            }
        }
        return flippedImage;
    }

    // Mirroring
    // ABCD -> ABBA
    public static BufferedImage mirrorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage mirroredImage = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {  // Process only left half
                int pixel = image.getRGB(x, y);
                mirroredImage.setRGB(x, y, pixel);  // Keep original left side
                mirroredImage.setRGB(width - 1 - x, y, pixel);  // Copy left side to right
            }
        }
        return mirroredImage;
    }

    public static BufferedImage applySepia(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage sepiaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int tr = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int tg = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int tb = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                tr = Math.min(255, tr);
                tg = Math.min(255, tg);
                tb = Math.min(255, tb);

                sepiaImage.setRGB(x, y, new Color(tr, tg, tb).getRGB());
            }
        }
        return sepiaImage;
    }

    public static BufferedImage applyPixelation(BufferedImage image, int pixelSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage pixelatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = pixelatedImage.createGraphics();

        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                int rgb = image.getRGB(x, y);
                g2d.setColor(new Color(rgb));
                g2d.fillRect(x, y, pixelSize, pixelSize);
            }
        }

        g2d.dispose();
        return pixelatedImage;
    }

    public static BufferedImage applySharpen(BufferedImage image) {
        float[] sharpenKernel = {
                0, -1,  0,
                -1,  5, -1,
                0, -1,  0
        };
        Kernel kernel = new Kernel(3, 3, sharpenKernel);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(image, null);
    }

    public static BufferedImage applyEmboss(BufferedImage image) {
        float[] embossKernel = {
                -2, -1, 0,
                -1,  1, 1,
                0,  1, 2
        };
        Kernel kernel = new Kernel(3, 3, embossKernel);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(image, null);
    }

    public static BufferedImage applyInvert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage invertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int red = 255 - ((rgb >> 16) & 0xff);
                int green = 255 - ((rgb >> 8) & 0xff);
                int blue = 255 - (rgb & 0xff);

                invertedImage.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
        return invertedImage;
    }

    public static BufferedImage adjustContrast(BufferedImage image, float contrastFactor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage contrastedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);

                int red = (int) ((color.getRed() - 128) * contrastFactor + 128);
                int green = (int) ((color.getGreen() - 128) * contrastFactor + 128);
                int blue = (int) ((color.getBlue() - 128) * contrastFactor + 128);

                // Clamping values to 0-255
                red = Math.max(0, Math.min(255, red));
                green = Math.max(0, Math.min(255, green));
                blue = Math.max(0, Math.min(255, blue));

                contrastedImage.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
        return contrastedImage;
    }

}
