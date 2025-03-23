package org.example.app;

public enum ImageOperation {
    GRAYSCALE, NEGATIVE, CROP, RESIZE, GAUSSIAN_BLUR,
    EDGEDETECTION, ADJUSTBRIGHTNESS, ROTATEIMAGE,
    FLIPPING, MIRRORING, SEPIA, PIXELATION,
    SHARPEN, EMBOSS, INVERT, ADJUSTCONTRAST;

    // Get formatted filename based on operation
    public String getFileSuffix() {
        return switch (this) {
            // filter
            case GRAYSCALE -> "_GRAY";
            case NEGATIVE -> "_NEGATIVE";
            case GAUSSIAN_BLUR -> "_GAUSSIAN";
            case SEPIA -> "_SEPIA";
            case PIXELATION -> "_PIXELATION";
            case SHARPEN -> "_SHARPEN";
            case EMBOSS -> "_EMBOSS";
            case INVERT -> "_INVERT";
            case EDGEDETECTION -> "_EDGEDETECTED";
            //
            case ADJUSTBRIGHTNESS -> "_ADJUSTBRIGHTNESS";
            case ADJUSTCONTRAST -> "_ADJUSTCONTRAST";
            // tool
            case CROP -> "_CROPPED";
            case RESIZE -> "_RESIZED";
            case ROTATEIMAGE -> "_ROTATEIMAGE";
            case FLIPPING -> "_FLIPPING";
            case MIRRORING -> "_MIRRORING";
        };
    }
}
