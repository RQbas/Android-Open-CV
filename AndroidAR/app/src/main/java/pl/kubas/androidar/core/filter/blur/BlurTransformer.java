package pl.kubas.androidar.core.filter.blur;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class BlurTransformer {

    public Mat transform(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        //Custom 2D linear filter results in lags during camera preview

        Mat mRgba = inputFrame.rgba();
        Mat bufforMat = new Mat();
        Imgproc.resize(mRgba, bufforMat, new Size(120, 90));
        Imgproc.resize(bufforMat, mRgba, new Size(1280, 720));
        return mRgba;
    }
}
