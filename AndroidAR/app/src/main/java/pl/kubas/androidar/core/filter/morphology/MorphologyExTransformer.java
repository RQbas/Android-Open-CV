package pl.kubas.androidar.core.filter.morphology;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MorphologyExTransformer {
    private Mat mRgba;


    public Mat transform(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        Imgproc.morphologyEx(mRgba, mRgba, Imgproc.MORPH_GRADIENT, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(4, 4)));
        return mRgba;
    }

}
