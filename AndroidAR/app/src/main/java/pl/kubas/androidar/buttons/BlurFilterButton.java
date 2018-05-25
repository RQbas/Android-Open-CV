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
import pl.kubas.androidar.core.filter.blur.BlurApplier;

public class BlurFilterButton implements ButtonAction {
    private JavaCameraView cameraView;
    private BlurApplier blurApplier;
    private Context applicationContext;
    private FloatingActionButton button;
    private boolean active;
    private ButtonTurnOff buttonTurnOff;

    BlurFilterButton(ActivityCameraCallback activityCameraCallback) {
        this.applicationContext = activityCameraCallback.getContext();
        this.cameraView = activityCameraCallback.getCameraView();
        this.blurApplier = activityCameraCallback.getFunctionalities().getBlurApplier();
    }

    @Override
    public void performAction(FloatingActionButton button) {
        this.button = button;
        if (!active) {
            if (buttonTurnOff != null) {
                buttonTurnOff.turnOffEveryButton();
            }
            turnOnBehavior();
        } else {
            turnOffBehavior();
        }
    }

    @Override
    public void turnOffBehavior() {
        if (button != null && active) {
            button.setBackgroundTintList(ColorStateList.valueOf(applicationContext.getResources().getColor(R.color.blue)));
            blurApplier.stopTransforming();
            active = false;
        }
    }

    @Override
    public void turnOnBehavior() {
        button.setBackgroundTintList(ColorStateList.valueOf(applicationContext.getResources().getColor(R.color.darkBlue)));
        blurApplier.startTransforming();
        active = true;
    }

    @Override
    public void setTurnOffInterface(ButtonTurnOff turnOffInterface) {
        this.buttonTurnOff = turnOffInterface;
    }


    public Drawable getDrawable() {
        return ContextCompat.getDrawable(applicationContext, R.drawable.ic_blur);
    }


}
