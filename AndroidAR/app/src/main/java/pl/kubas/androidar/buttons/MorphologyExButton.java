package pl.kubas.androidar.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;

import pl.kubas.androidar.R;
import pl.kubas.androidar.adapters.ButtonTurnOff;
import pl.kubas.androidar.context.ActivityCameraCallback;
import pl.kubas.androidar.core.filter.morphology.MorphologyExApplier;

public class MorphologyExButton implements ButtonAction {
    private MorphologyExApplier morphologyExApplier;
    private ButtonTurnOff buttonTurnOff;
    private Context applicationContext;
    private FloatingActionButton button;
    private boolean active;

    MorphologyExButton(ActivityCameraCallback activityCameraCallback) {
        this.applicationContext = activityCameraCallback.getContext();
        this.morphologyExApplier = activityCameraCallback.getFunctionalities().getMorphologyExApplier();
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
        morphologyExApplier.startTransforming();
        active = true;
    }

    @Override
    public void turnOffBehavior() {
        if (button != null && active) {
            button.setBackgroundTintList(ColorStateList.valueOf(applicationContext.getResources().getColor(R.color.blue)));
            morphologyExApplier.stopTransforming();
            active = false;
        }

    }

    @Override
    public void setTurnOffInterface(ButtonTurnOff turnOffInterface) {
        this.buttonTurnOff = turnOffInterface;
    }

    public Drawable getDrawable() {
        return ContextCompat.getDrawable(applicationContext, R.drawable.ic_contour);
    }
}
