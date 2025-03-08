import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileHandler {

    // Generate an output path based on input file and operation
    public static String generateOutputPath(String inputPath, ImageOperation operation) {
        File inputFile = new File(inputPath);
        String parentDirectory = inputFile.getParent();
        String fileName = inputFile.getName();

        // Remove extension
        int dotIndex = fileName.lastIndexOf(".");
        // String nameWithoutExt = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
        String nameWithoutExt = (dotIndex == -1) ? "OUTPUT" : "OUTPUT".substring(0, dotIndex);
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);

        // Generate new file name
        return parentDirectory + File.separator + nameWithoutExt + operation.getFileSuffix() + extension;
    }

    public static BufferedImage readImage(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }
        return ImageIO.read(file);
    }

    // Write image to file
    public static void writeImage(BufferedImage image, String outputPath, String format) throws IOException {
        File outputFile = new File(outputPath);
        if (!ImageIO.write(image, format, outputFile)) {
            throw new IOException("Failed to write image: " + outputPath);
        }
    }
}
