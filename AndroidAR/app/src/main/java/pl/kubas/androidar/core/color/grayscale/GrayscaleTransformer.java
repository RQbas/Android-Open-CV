package pl.kubas.androidar.core.color.grayscale;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class GrayscaleTransformer {
    private Size sizeRgba;
    private int width;
    private int height;
    private Mat mRgba;


    public Mat transformToGrayscale(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        sizeRgba = inputFrame.rgba().size();
        width = (int) sizeRgba.width;
        height = (int) sizeRgba.height;
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        Imgproc.cvtColor(inputFrame.gray(), mRgba, Imgproc.COLOR_GRAY2RGBA, 4);
        return mRgba;
    }
}
