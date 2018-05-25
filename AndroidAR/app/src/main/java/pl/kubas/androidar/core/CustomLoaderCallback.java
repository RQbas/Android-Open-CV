package pl.kubas.androidar.core;

import android.util.Log;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;

import pl.kubas.androidar.context.ActivityCameraCallback;
import pl.kubas.androidar.context.Origin;
import pl.kubas.androidar.core.color.detection.ColorDetector;
import pl.kubas.androidar.core.color.grayscale.GrayscaleApplier;
import pl.kubas.androidar.core.filter.blur.BlurApplier;
import pl.kubas.androidar.core.filter.morphology.MorphologyExApplier;
import pl.kubas.androidar.core.mask.layer.ImageDetector;

public class CustomLoaderCallback extends BaseLoaderCallback {
    private ColorDetector colorDetector;
    private ImageDetector imageDetector;
    private GrayscaleApplier grayscaleApplier;
    private MorphologyExApplier morphologyExApplier;
    private BlurApplier blurApplier;
    private ActivityCameraCallback activityCameraCallback;

    public CustomLoaderCallback(ActivityCameraCallback activityCameraCallback) {
        super(activityCameraCallback.getContext());
        this.activityCameraCallback = activityCameraCallback;
        this.imageDetector = activityCameraCallback.getFunctionalities().getImageDetector();
        this.colorDetector = activityCameraCallback.getFunctionalities().getColorDetector();
        this.grayscaleApplier = activityCameraCallback.getFunctionalities().getGrayscaleApplier();
        this.morphologyExApplier = activityCameraCallback.getFunctionalities().getMorphologyExApplier();
        this.blurApplier = activityCameraCallback.getFunctionalities().getBlurApplier();
    }


    @Override
    public void onManagerConnected(int status) {
        switch (status) {
            case LoaderCallbackInterface.SUCCESS: {
                Log.i(Origin.TAG, "OpenCV loaded successfully");
                activityCameraCallback.getCameraView().enableView();
                colorDetector.setUpColorDetector(activityCameraCallback.getCameraView());
                imageDetector.setUpImageDetector(activityCameraCallback);
                grayscaleApplier.setGrayscaleApplier(activityCameraCallback);
                blurApplier.setBlurApplier(activityCameraCallback);
                morphologyExApplier.setMorphologyExApplier(activityCameraCallback);
            }
            break;
            default: {
                super.onManagerConnected(status);
            }
            break;
        }
    }
}
