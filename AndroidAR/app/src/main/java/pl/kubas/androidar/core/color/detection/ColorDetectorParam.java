package pl.kubas.androidar.core.color.detection;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

public class ColorDetectorParam {
    private boolean mIsColorSelected = false;
    private Mat mRgba;
    private Scalar mBlobColorRgba;
    private Scalar mBlobColorHsv;
    private ColorDetectorTransformer mDetector;
    private Mat mSpectrum;
    private Size SPECTRUM_SIZE;
    private Scalar CONTOUR_COLOR;


    public ColorDetectorParam(int height, int width) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mDetector = new ColorDetectorTransformer();
        mSpectrum = new Mat();
        mBlobColorRgba = new Scalar(255);
        mBlobColorHsv = new Scalar(255);
        SPECTRUM_SIZE = new Size(200, 64);
        CONTOUR_COLOR = new Scalar(255, 0, 0, 255);
    }

    public boolean ismIsColorSelected() {
        return mIsColorSelected;
    }

    public void setmIsColorSelected(boolean mIsColorSelected) {
        this.mIsColorSelected = mIsColorSelected;
    }

    public Mat getmRgba() {
        return mRgba;
    }

    public void setmRgba(Mat mRgba) {
        this.mRgba = mRgba;
    }

    public Scalar getmBlobColorRgba() {
        return mBlobColorRgba;
    }

    public void setmBlobColorRgba(Scalar mBlobColorRgba) {
        this.mBlobColorRgba = mBlobColorRgba;
    }

    public Scalar getmBlobColorHsv() {
        return mBlobColorHsv;
    }

    public void setmBlobColorHsv(Scalar mBlobColorHsv) {
        this.mBlobColorHsv = mBlobColorHsv;
    }

    public ColorDetectorTransformer getmDetector() {
        return mDetector;
    }

    public void setmDetector(ColorDetectorTransformer mDetector) {
        this.mDetector = mDetector;
    }

    public Mat getmSpectrum() {
        return mSpectrum;
    }

    public void setmSpectrum(Mat mSpectrum) {
        this.mSpectrum = mSpectrum;
    }

    public Size getSPECTRUM_SIZE() {
        return SPECTRUM_SIZE;
    }

    public void setSPECTRUM_SIZE(Size SPECTRUM_SIZE) {
        this.SPECTRUM_SIZE = SPECTRUM_SIZE;
    }

    public Scalar getCONTOUR_COLOR() {
        return CONTOUR_COLOR;
    }

    public void setCONTOUR_COLOR(Scalar CONTOUR_COLOR) {
        this.CONTOUR_COLOR = CONTOUR_COLOR;
    }
}
