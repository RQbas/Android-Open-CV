package pl.kubas.androidar.core.color.detection;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.opencv.android.JavaCameraView;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import pl.kubas.androidar.context.Origin;

public class ScreenTouchDetector implements View.OnTouchListener {
    private ColorDetectorParam params;
    private JavaCameraView cameraView;

    public ScreenTouchDetector(ColorDetectorParam params, JavaCameraView cameraView) {
        this.params = params;
        this.cameraView = cameraView;
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int cols = params.getmRgba().cols();
        int rows = params.getmRgba().rows();

        int xOffset = (cameraView.getWidth() - cols) / 2;
        int yOffset = (cameraView.getHeight() - rows) / 2;

        int x = (int) event.getX() - xOffset;
        int y = (int) event.getY() - yOffset;

        Log.i(Origin.TAG, "Touch image coordinates: (" + x + ", " + y + ")");

        if ((x < 0) || (y < 0) || (x > cols) || (y > rows)) return false;

        Rect touchedRect = new Rect();

        touchedRect.x = (x > 4) ? x - 4 : 0;
        touchedRect.y = (y > 4) ? y - 4 : 0;

        touchedRect.width = (x + 4 < cols) ? x + 4 - touchedRect.x : cols - touchedRect.x;
        touchedRect.height = (y + 4 < rows) ? y + 4 - touchedRect.y : rows - touchedRect.y;

        Mat touchedRegionRgba = params.getmRgba().submat(touchedRect);

        Mat touchedRegionHsv = new Mat();
        Imgproc.cvtColor(touchedRegionRgba, touchedRegionHsv, Imgproc.COLOR_RGB2HSV_FULL);

        // Calculate average color of touched region
        params.setmBlobColorHsv(Core.sumElems(touchedRegionHsv));
        int pointCount = touchedRect.width * touchedRect.height;
        for (int i = 0; i < params.getmBlobColorHsv().val.length; i++)
            params.getmBlobColorHsv().val[i] /= pointCount;

        params.setmBlobColorRgba(converScalarHsv2Rgba(params.getmBlobColorHsv()));

        Log.i(Origin.TAG, "Touched rgba color: (" + params.getmBlobColorRgba().val[0] + ", " + params.getmBlobColorRgba().val[1] +
                ", " + params.getmBlobColorRgba().val[2] + ", " + params.getmBlobColorRgba().val[3] + ")");

        params.getmDetector().setHsvColor(params.getmBlobColorHsv());

        Imgproc.resize(params.getmDetector().getSpectrum(), params.getmSpectrum(), params.getSPECTRUM_SIZE(), 0, 0, Imgproc.INTER_LINEAR);

        params.setmIsColorSelected(true);

        touchedRegionRgba.release();
        touchedRegionHsv.release();

        return false; // don't need subsequent touch events

    }

    private Scalar converScalarHsv2Rgba(Scalar hsvColor) {
        Mat pointMatRgba = new Mat();
        Mat pointMatHsv = new Mat(1, 1, CvType.CV_8UC3, hsvColor);
        Imgproc.cvtColor(pointMatHsv, pointMatRgba, Imgproc.COLOR_HSV2RGB_FULL, 4);

        return new Scalar(pointMatRgba.get(0, 0));
    }
}
