package pl.kubas.androidar.core.mask.layer;

import android.content.Context;
import android.util.Log;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Mat;

import pl.kubas.androidar.context.ActivityCameraCallback;
import pl.kubas.androidar.context.Origin;

public class ImageDetector implements CameraBridgeViewBase.CvCameraViewListener2 {

    private JavaCameraView cameraView;
    private ImageDetectorParam params;
    private Context context;

    public ImageDetector() {
    }

    public void setUpImageDetector(ActivityCameraCallback cameraCallback) {
        this.cameraView = cameraCallback.getCameraView();
        this.params = new ImageDetectorParam();
        this.context = cameraCallback.getContext();
    }


    @Override
    public void onCameraViewStarted(int width, int height) {
        Log.i(Origin.TAG, "Camera started");
    }

    @Override
    public void onCameraViewStopped() {
        Log.i(Origin.TAG, "Camera stopped");
    }


    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return inputFrame.rgba();
    }


    public void stopTransforming() {
        if (cameraView != null)
            cameraView.setCvCameraViewListener((CameraBridgeViewBase.CvCameraViewListener2) null);
    }

    public void startTransforming() {
        if (cameraView != null)
            cameraView.setCvCameraViewListener(this);
    }

}
