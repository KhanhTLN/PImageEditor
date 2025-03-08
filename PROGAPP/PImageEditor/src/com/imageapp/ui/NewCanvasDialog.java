

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import java.util.Optional;
import javafx.scene.control.ButtonBar.ButtonData;

public class NewCanvasDialog {

    /**
     * Shows a dialog to create a new canvas
     * @return pair of width and height, or null if canceled
     */
    public static Pair<Double, Double> showDialog() {
        // Create the custom dialog
        Dialog<Pair<Double, Double>> dialog = new Dialog<>();
        dialog.setTitle("New Canvas");
        dialog.setHeaderText("Create a new canvas with custom dimensions");

        // Set the button types
        ButtonType createButtonType = new ButtonType("Create", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        // Create the width and height labels and fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField widthField = new TextField("800");
        widthField.setPromptText("Width");
        TextField heightField = new TextField("600");
        heightField.setPromptText("Height");

        ComboBox<String> presetComboBox = new ComboBox<>();
        presetComboBox.getItems().addAll(
                "Custom",
                "800 × 600 (Standard)",
                "1280 × 720 (HD)",
                "1920 × 1080 (Full HD)",
                "3840 × 2160 (4K UHD)"
        );
        presetComboBox.setValue("Custom");

        grid.add(new Label("Presets:"), 0, 0);
        grid.add(presetComboBox, 1, 0);
        grid.add(new Label("Width:"), 0, 1);
        grid.add(widthField, 1, 1);
        grid.add(new Label("Height:"), 0, 2);
        grid.add(heightField, 1, 2);

        // Set up preset change listener
        presetComboBox.setOnAction(e -> {
            String selected = presetComboBox.getValue();
            switch (selected) {
                case "800 × 600 (Standard)":
                    widthField.setText("800");
                    heightField.setText("600");
                    break;
                case "1280 × 720 (HD)":
                    widthField.setText("1280");
                    heightField.setText("720");
                    break;
                case "1920 × 1080 (Full HD)":
                    widthField.setText("1920");
                    heightField.setText("1080");
                    break;
                case "3840 × 2160 (4K UHD)":
                    widthField.setText("3840");
                    heightField.setText("2160");
                    break;
                default:
                    // Custom - do nothing
                    break;
            }
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the width field by default
        widthField.requestFocus();

        // Convert the result
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createButtonType) {
                try {
                    double width = Double.parseDouble(widthField.getText());
                    double height = Double.parseDouble(heightField.getText());

                    // Validate dimensions
                    if (width <= 0 || height <= 0 || width > 10000 || height > 10000) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Dimensions");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter valid dimensions (1-10000).");
                        alert.showAndWait();
                        return null;
                    }

                    return new Pair<>(width, height);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter valid numbers for width and height.");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        // Show the dialog and return the result
        Optional<Pair<Double, Double>> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
