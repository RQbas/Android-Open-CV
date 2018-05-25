package pl.kubas.androidar.buttons;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;

import pl.kubas.androidar.adapters.ButtonTurnOff;

public interface ButtonAction {

    void performAction(FloatingActionButton button);

    void turnOffBehavior();

    void turnOnBehavior();

    void setTurnOffInterface(ButtonTurnOff turnOffInterface);

    Drawable getDrawable();
}
