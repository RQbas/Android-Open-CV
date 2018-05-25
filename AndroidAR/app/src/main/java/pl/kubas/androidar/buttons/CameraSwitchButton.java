package pl.kubas.androidar.buttons;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;

import org.opencv.android.JavaCameraView;

import pl.kubas.androidar.R;
import pl.kubas.androidar.adapters.ButtonTurnOff;
import pl.kubas.androidar.context.ActivityCameraCallback;

public class CameraSwitchButton implements ButtonAction {
    private JavaCameraView cameraView;
    private Context applicationContext;
    private int mCameraId = 0;

    CameraSwitchButton(ActivityCameraCallback activityCameraCallback) {
        this.applicationContext = activityCameraCallback.getContext();
        this.cameraView = activityCameraCallback.getCameraView();
    }

    @Override
    public void performAction(FloatingActionButton button) {
        mCameraId = mCameraId ^ 1;
        cameraView.disableView();
        cameraView.setCameraIndex(mCameraId);
        cameraView.enableView();
    }

    @Override
    public void turnOffBehavior() {
    }

    @Override
    public void turnOnBehavior() {

    }

    @Override
    public void setTurnOffInterface(ButtonTurnOff turnOffInterface) {
    }

    public Drawable getDrawable() {
        return ContextCompat.getDrawable(applicationContext, R.drawable.ic_switch);
    }
}
