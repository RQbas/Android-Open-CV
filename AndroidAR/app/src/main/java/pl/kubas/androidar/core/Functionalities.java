package pl.kubas.androidar.core;

import pl.kubas.androidar.core.color.detection.ColorDetector;
import pl.kubas.androidar.core.color.grayscale.GrayscaleApplier;
import pl.kubas.androidar.core.filter.morphology.MorphologyExApplier;
import pl.kubas.androidar.core.filter.blur.BlurApplier;
import pl.kubas.androidar.core.mask.layer.ImageDetector;

public class Functionalities {
    private ColorDetector colorDetector = new ColorDetector();
    private ImageDetector imageDetector = new ImageDetector();
    private GrayscaleApplier grayscaleApplier = new GrayscaleApplier();
    private BlurApplier blurApplier = new BlurApplier();
    private MorphologyExApplier morphologyExApplier = new MorphologyExApplier();

    public ColorDetector getColorDetector() {
        return colorDetector;
    }

    public ImageDetector getImageDetector() {
        return imageDetector;
    }

    public GrayscaleApplier getGrayscaleApplier() {
        return grayscaleApplier;
    }

    public BlurApplier getBlurApplier() {
        return blurApplier;
    }

    public MorphologyExApplier getMorphologyExApplier() {
        return morphologyExApplier;
    }
}
