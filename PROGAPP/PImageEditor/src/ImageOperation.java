public enum ImageOperation {
    GRAYSCALE, NEGATIVE, CROP, RESIZE, GAUSSIAN_BLUR,
    EDGEDETECTION, ADJUSTBRIGHTNESS, ROTATEIMAGE,
    FLIPPING, MIRRORING;

    // Get formatted filename based on operation
    public String getFileSuffix() {
        return switch (this) {
            case GRAYSCALE -> "_GRAY";
            case NEGATIVE -> "_NEGATIVE";
            case CROP -> "_CROPPED";
            case RESIZE -> "_RESIZED";
            case GAUSSIAN_BLUR -> "_GAUSSIAN";
            case EDGEDETECTION -> "_EDGEDETECTED";
            case ADJUSTBRIGHTNESS -> "_ADJUSTBRIGHTNESS";
            case ROTATEIMAGE -> "_ROTATEIMAGE";
            case FLIPPING -> "_FLIPPING";
            case MIRRORING -> "_MIRRORING";
        };
    }
}
