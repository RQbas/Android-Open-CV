package pl.kubas.androidar.core.color.detection;

import android.util.Log;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;

import java.util.List;

import pl.kubas.androidar.context.Origin;

public class ColorDetector implements CameraBridgeViewBase.CvCameraViewListener2 {

    private JavaCameraView cameraView;
    private ColorDetectorParam params;
    private ScreenTouchDetector screenTouchDetector;

    public ColorDetector() {
    }

    public void setUpColorDetector(JavaCameraView cameraView) {
        this.cameraView = cameraView;
        this.params = new ColorDetectorParam(cameraView.getHeight(), cameraView.getWidth());
        this.screenTouchDetector = new ScreenTouchDetector(params, cameraView);
    }


    public ScreenTouchDetector getScreenTouchDetector() {
        return screenTouchDetector;
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
    }

    @Override
    public void onCameraViewStopped() {
        params.getmRgba().release();
    }


    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        params.setmRgba(inputFrame.rgba());

        if (params.ismIsColorSelected()) {
            params.getmDetector().process(params.getmRgba());
            List<MatOfPoint> contours = params.getmDetector().getContours();
            Log.e(Origin.TAG, "Contours count: " + contours.size());
            Imgproc.drawContours(params.getmRgba(), contours, -1, params.getCONTOUR_COLOR());

            Mat colorLabel = params.getmRgba().submat(4, 68, 4, 68);
            colorLabel.setTo(params.getmBlobColorRgba());

            Mat spectrumLabel = params.getmRgba().submat(4, 4 + params.getmSpectrum().rows(), 70, 70 + params.getmSpectrum().cols());
            params.getmSpectrum().copyTo(spectrumLabel);
        }

        return params.getmRgba();
    }


    public void stopTransforming() {
        cameraView.setOnTouchListener(null);
        params.setmIsColorSelected(false);

    }

    public void startTransforming() {
        cameraView.setOnTouchListener(getScreenTouchDetector());
        cameraView.setCvCameraViewListener(this);
    }
}
