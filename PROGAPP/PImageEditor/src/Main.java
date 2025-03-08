import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String inputPath = "D:/OOP - PROPTIT/IMAGE TEST/INPUT.jpg";

            // Read image
            BufferedImage originalImage = FileHandler.readImage(inputPath);

            // Apply processing
            //BufferedImage croppedImage = ImageProcessor.cropImage(originalImage, 50, 50, 200, 200);
            BufferedImage resizedImage = ImageProcessor.resizeImage(originalImage, 300, 300);
            BufferedImage blurredImage = ImageProcessor.applyGaussianBlur(originalImage);
            BufferedImage brightImage = ImageProcessor.adjustBrightness(originalImage, 1.5f);
            BufferedImage edgeImage = ImageProcessor.applyEdgeDetection(originalImage);
            BufferedImage grayscaleImage = ImageProcessor.convertToGrayscale(originalImage);
            BufferedImage negativeImage = ImageProcessor.convertToNegative(originalImage);
            BufferedImage rotateImage = ImageProcessor.rotateImage(originalImage);
            BufferedImage flipImage = ImageProcessor.flipImage(originalImage);
            BufferedImage mirrorImage = ImageProcessor.mirrorImage(originalImage);

            // Save processed images
            //FileHandler.writeImage(croppedImage, FileHandler.generateOutputPath(inputPath, ImageOperation.CROP), "jpg");
            FileHandler.writeImage(grayscaleImage, FileHandler.generateOutputPath(inputPath, ImageOperation.GRAYSCALE), "jpg");;
            FileHandler.writeImage(negativeImage, FileHandler.generateOutputPath(inputPath, ImageOperation.NEGATIVE), "jpg");
            FileHandler.writeImage(resizedImage, FileHandler.generateOutputPath(inputPath, ImageOperation.RESIZE), "jpg");
            FileHandler.writeImage(blurredImage, FileHandler.generateOutputPath(inputPath, ImageOperation.GAUSSIAN_BLUR), "jpg");
            FileHandler.writeImage(brightImage, FileHandler.generateOutputPath(inputPath, ImageOperation.ADJUSTBRIGHTNESS), "jpg");
            FileHandler.writeImage(edgeImage, FileHandler.generateOutputPath(inputPath, ImageOperation.EDGEDETECTION), "jpg");
            FileHandler.writeImage(rotateImage, FileHandler.generateOutputPath(inputPath, ImageOperation.ROTATEIMAGE), "jpg");
            FileHandler.writeImage(flipImage, FileHandler.generateOutputPath(inputPath, ImageOperation.FLIPPING), "jpg");
            FileHandler.writeImage(mirrorImage, FileHandler.generateOutputPath(inputPath, ImageOperation.MIRRORING), "jpg");

            System.out.println("Image processing completed!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
