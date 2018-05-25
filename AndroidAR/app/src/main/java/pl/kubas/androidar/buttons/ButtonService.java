package pl.kubas.androidar.buttons;

import java.util.ArrayList;
import java.util.List;

import pl.kubas.androidar.context.ActivityCameraCallback;

public class ButtonService {

    public static List<ButtonAction> obtainButtons(ActivityCameraCallback activityCameraCallback) {
        List<ButtonAction> buttonActionList = new ArrayList<>();
        buttonActionList.add(new CameraActiveButton(activityCameraCallback));
        buttonActionList.add(new CameraSwitchButton(activityCameraCallback));
        buttonActionList.add(new ColorDetectorButton(activityCameraCallback));
        buttonActionList.add(new GrayScaleButton(activityCameraCallback));
        buttonActionList.add(new BlurFilterButton(activityCameraCallback));
        buttonActionList.add(new MorphologyExButton(activityCameraCallback));
        buttonActionList.add(new ImageDetectorButton(activityCameraCallback));
        return buttonActionList;
    }

}
