package pl.kubas.androidar.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import org.opencv.android.JavaCameraView;

import pl.kubas.androidar.R;
import pl.kubas.androidar.adapters.ButtonTurnOff;
import pl.kubas.androidar.context.ActivityCameraCallback;
import pl.kubas.androidar.core.mask.layer.ImageDetector;

public class ImageDetectorButton implements ButtonAction {
    private JavaCameraView cameraView;
    private ImageDetector imageDetector;
    private Context applicationContext;
    private boolean active;
    private ButtonTurnOff buttonTurnOff;
    private FloatingActionButton button;

    ImageDetectorButton(ActivityCameraCallback activityCameraCallback) {
        this.applicationContext = activityCameraCallback.getContext();
        this.cameraView = activityCameraCallback.getCameraView();
        this.imageDetector = activityCameraCallback.getFunctionalities().getImageDetector();
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
        Toast.makeText(applicationContext, "On", Toast.LENGTH_SHORT).show();
        imageDetector.startTransforming();
        active = true;
    }

    @Override
    public void turnOffBehavior() {
        if (button != null && active) {
            button.setBackgroundTintList(ColorStateList.valueOf(applicationContext.getResources().getColor(R.color.blue)));
            Toast.makeText(applicationContext, "Off", Toast.LENGTH_SHORT).show();
            imageDetector.stopTransforming();
            active = false;
        }
    }

    @Override
    public void setTurnOffInterface(ButtonTurnOff turnOffInterface) {
        this.buttonTurnOff = turnOffInterface;
    }

    public Drawable getDrawable() {
        return ContextCompat.getDrawable(applicationContext, R.drawable.ic_images);
    }
}
