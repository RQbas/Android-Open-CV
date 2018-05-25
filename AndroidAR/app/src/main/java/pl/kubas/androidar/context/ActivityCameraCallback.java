package pl.kubas.androidar.context;

import android.content.Context;

import org.opencv.android.JavaCameraView;

import pl.kubas.androidar.core.Functionalities;
import pl.kubas.androidar.core.color.detection.ColorDetector;
import pl.kubas.androidar.core.color.grayscale.GrayscaleApplier;
import pl.kubas.androidar.core.mask.layer.ImageDetector;

public interface ActivityCameraCallback {

    Context getContext();

    JavaCameraView getCameraView();

    Functionalities getFunctionalities();

}
