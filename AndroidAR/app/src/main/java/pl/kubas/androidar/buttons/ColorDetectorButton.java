package pl.kubas.androidar.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;

import pl.kubas.androidar.R;
import pl.kubas.androidar.adapters.ButtonTurnOff;
import pl.kubas.androidar.context.ActivityCameraCallback;
import pl.kubas.androidar.core.color.detection.ColorDetector;

public class ColorDetectorButton implements ButtonAction {
    private ColorDetector colorDetector;
    private Context applicationContext;
    private boolean active;
    private FloatingActionButton button;
    private ButtonTurnOff buttonTurnOff;

    ColorDetectorButton(ActivityCameraCallback activityCameraCallback) {
        this.applicationContext = activityCameraCallback.getContext();
        this.colorDetector = activityCameraCallback.getFunctionalities().getColorDetector();
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
    public void turnOnBehavior() {
        button.setBackgroundTintList(ColorStateList.valueOf(applicationContext.getResources().getColor(R.color.darkBlue)));
        colorDetector.startTransforming();
        active = true;
    }

    @Override
    public void turnOffBehavior() {
        if (button != null && active) {
            button.setBackgroundTintList(ColorStateList.valueOf(applicationContext.getResources().getColor(R.color.blue)));
            colorDetector.stopTransforming();
            active = false;
        }
    }

    @Override
    public void setTurnOffInterface(ButtonTurnOff turnOffInterface) {
        this.buttonTurnOff = turnOffInterface;
    }

    public Drawable getDrawable() {
        return ContextCompat.getDrawable(applicationContext, R.drawable.ic_color_detect);
    }
}
