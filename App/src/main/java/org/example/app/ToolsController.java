package org.example.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ToolsController {

     @FXML ImageView imageView;
     @FXML MenuItem openImage;
     @FXML Pane imagePane;
     @FXML MenuItem saveImage;
    @FXML private Slider brightnessSlider;
    @FXML private Slider contrastSlider;
    @FXML Button undoButton;
    @FXML Button redoButton;
    @FXML private ComboBox<String> filterComboBox;
    @FXML private ComboBox<String> toolsComboBox;
    @FXML private ComboBox<String> adjustmentsComboBox;



    private BufferedImage currentImage;
     private ImageHistory imageHistory = new ImageHistory();


    private Image convertToJavaFXImage(BufferedImage bufferedImage) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", output);
            ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
            return new Image(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    public void undo() {
        if (imageHistory.canUndo()) {
            currentImage = imageHistory.undo(currentImage);
            imageView.setImage(convertToJavaFXImage(currentImage));
        }
    }

    @FXML
    public void redo() {
        if (imageHistory.canRedo()) {
            currentImage = imageHistory.redo(currentImage);
            imageView.setImage(convertToJavaFXImage(currentImage));
        }
    }


    @FXML
    public void openImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
        );

        File file = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (file != null) {
            try {
                currentImage = ImageIO.read(file);
                imageView.setImage(convertToJavaFXImage(currentImage));
                imageHistory.saveState(currentImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveImage() {
        if (currentImage == null) {
            System.out.println("No image to save!");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"),
                new FileChooser.ExtensionFilter("BMP Files", "*.bmp")
        );

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                String format = getFileExtension(file);
                if (format == null) {
                    System.out.println("Unsupported file format.");
                    return;
                }
                ImageIO.write(currentImage, format, file);
                System.out.println("Image saved: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to save image.");
            }
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');
        if (lastIndex > 0) {
            return name.substring(lastIndex + 1).toLowerCase();
        }
        return null;
    }

    @FXML
    private void exitApp() {
        // Check if an image has been modified and not saved
        if (currentImage != null) {
            // Show confirmation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Unsaved Changes");
            alert.setHeaderText("You have unsaved changes!");
            alert.setContentText("Do you want to save before exiting?");

            // Add Save, Don't Save, and Cancel buttons
            ButtonType saveButton = new ButtonType("Save");
            ButtonType dontSaveButton = new ButtonType("Don't Save");
            ButtonType cancelButton = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(saveButton, dontSaveButton, cancelButton);

            // Show the alert and wait for user response
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == saveButton) {
                    // If the user chooses to save, call saveImage()
                    saveImage();
                } else if (result.get() == cancelButton) {
                    // If the user cancels, do nothing and return
                    return;
                }
            }
        }

        // Exit the application
        System.exit(0);
    }

    //3 combo box
    // filter
    @FXML
    private void applySelectedFilter() {
        if (currentImage == null) return;

        imageHistory.saveState(currentImage);
        String selectedFilter = filterComboBox.getValue();

        if (selectedFilter.equals("Grayscale")) {
            currentImage = ImageProcessor.convertToGrayscale(currentImage);
        } else if (selectedFilter.equals("Blur")) {
            currentImage = ImageProcessor.applyGaussianBlur(currentImage);
        } else if (selectedFilter.equals("Negative")) {
            currentImage = ImageProcessor.convertToNegative(currentImage);
        } else if (selectedFilter.equals("Sepia")) {
            currentImage = ImageProcessor.applySepia(currentImage);
        } else if (selectedFilter.equals("Pixelation")) {
            currentImage = ImageProcessor.applyPixelation(currentImage, 10);
        } else if (selectedFilter.equals("Sharpen")) {
            currentImage = ImageProcessor.applySharpen(currentImage);
        } else if (selectedFilter.equals("Emboss")) {
            currentImage = ImageProcessor.applyEmboss(currentImage);
        } else if (selectedFilter.equals("Invert")) {
            currentImage = ImageProcessor.applyInvert(currentImage);
        } else if (selectedFilter.equals("Edge Detection")) {
            currentImage = ImageProcessor.applyEdgeDetection(currentImage);
        }

        imageView.setImage(convertToJavaFXImage(currentImage));
    }


    //tools
    @FXML
    private void applySelectedTool() {
        if (currentImage == null) return;

        imageHistory.saveState(currentImage);
        String selectedTool = toolsComboBox.getValue();

        if (selectedTool == null) return;

        switch (selectedTool) {
            case "Rotate" -> currentImage = ImageProcessor.rotateImage(currentImage);
            case "Flip" -> currentImage = ImageProcessor.flipImage(currentImage);
            case "Mirror" -> currentImage = ImageProcessor.mirrorImage(currentImage);
        }

        imageView.setImage(convertToJavaFXImage(currentImage));
    }



    //adjustments
    @FXML
    public void initialize() {
        brightnessSlider.valueProperty().addListener((obs, oldVal, newVal) -> adjustBrightness());
        contrastSlider.valueProperty().addListener((obs, oldVal, newVal) -> adjustContrast());
    }

    @FXML
    public void adjustBrightness() {
        if (currentImage == null) return;
        float brightnessFactor = (float) brightnessSlider.getValue();  // Get brightness level from slider
        imageHistory.saveState(currentImage);
        currentImage = ImageProcessor.adjustBrightness(currentImage, brightnessFactor);
        imageView.setImage(convertToJavaFXImage(currentImage));
    }

    @FXML
    public void adjustContrast() {
        if (currentImage == null) return;
        float contrastFactor = (float) contrastSlider.getValue();  // Get contrast level from slider
        imageHistory.saveState(currentImage);
        currentImage = ImageProcessor.adjustContrast(currentImage, contrastFactor);
        imageView.setImage(convertToJavaFXImage(currentImage));
    }
}
