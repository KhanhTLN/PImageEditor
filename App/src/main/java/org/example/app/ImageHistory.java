package org.example.app;

import java.awt.image.BufferedImage;
import java.util.Stack;

public class ImageHistory {
    private Stack<BufferedImage> undoStack = new Stack<>();
    private Stack<BufferedImage> redoStack = new Stack<>();

    // Save new state and clear redo stack
    public void saveState(BufferedImage image) {
        undoStack.push(deepCopy(image));
        redoStack.clear();  // Redo stack must be cleared after a new operation
    }

    // Undo: move current state to redo and return previous state
    public BufferedImage undo(BufferedImage currentImage) {
        if (!undoStack.isEmpty()) {
            redoStack.push(deepCopy(currentImage));
            return undoStack.pop();
        }
        return currentImage; // No undo available
    }

    // Redo: move last undone state back to undo
    public BufferedImage redo(BufferedImage currentImage) {
        if (!redoStack.isEmpty()) {
            undoStack.push(deepCopy(currentImage));
            return redoStack.pop();
        }
        return currentImage; // No redo available
    }

    // Check if undo/redo is possible
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    // Deep copy BufferedImage to avoid reference issues
    private BufferedImage deepCopy(BufferedImage bi) {
        BufferedImage copy = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                copy.setRGB(x, y, bi.getRGB(x, y)); // Ensure pixel-level copying
            }
        }
        return copy;
    }
}
