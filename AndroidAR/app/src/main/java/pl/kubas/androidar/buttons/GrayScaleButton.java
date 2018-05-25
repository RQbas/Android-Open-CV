package pl.kubas.androidar.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;

import org.opencv.android.JavaCameraView;

import pl.kubas.androidar.R;
import pl.kubas.androidar.adapters.ButtonTurnOff;
import pl.kubas.androidar.context.ActivityCameraCallback;
import pl.kubas.androidar.core.color.grayscale.GrayscaleApplier;

public class GrayScaleButton implements ButtonAction {
    private JavaCameraView cameraView;
    private GrayscaleApplier grayscaleApplier;
    private Context applicationContext;
    private boolean active;
    private FloatingActionButton button;
    private ButtonTurnOff buttonTurnOff;

    GrayScaleButton(ActivityCameraCallback activityCameraCallback) {
        this.applicationContext = activityCameraCallback.getContext();
        this.cameraView = activityCameraCallback.getCameraView();
        this.grayscaleApplier = activityCameraCallback.getFunctionalities().getGrayscaleApplier();
    }

    @Override
    public void performAction(FloatingActionButton button) {
        this.button = button;
        if (!active) {
            if (buttonTurnOff != null) {
                buttonTurnOff.turnOffEveryButton();
            }
            button.setBackgroundTintList(ColorStateList.valueOf(applicationContext.getResources().getColor(R.color.darkBlue)));
            grayscaleApplier.startTransforming();
            active = true;
        } else {
            turnOffBehavior();
        }
    }

    @Override
    public void turnOnBehavior() {
    }

        @Override
    public void turnOffBehavior() {
        if (button != null && active) {
            button.setBackgroundTintList(ColorStateList.valueOf(applicationContext.getResources().getColor(R.color.blue)));
            grayscaleApplier.stopTransforming();
            active = false;
        }
    }

    @Override
    public void setTurnOffInterface(ButtonTurnOff turnOffInterface) {
        this.buttonTurnOff = turnOffInterface;
    }

    public Drawable getDrawable() {
        return ContextCompat.getDrawable(applicationContext, R.drawable.ic_grayscale);
    }
}
