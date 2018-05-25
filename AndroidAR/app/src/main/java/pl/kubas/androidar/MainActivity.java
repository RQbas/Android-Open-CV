package pl.kubas.androidar;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.SurfaceView;

import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kubas.androidar.adapters.ButtonAdapter;
import pl.kubas.androidar.buttons.ButtonService;
import pl.kubas.androidar.context.ActivityCameraCallback;
import pl.kubas.androidar.context.Origin;
import pl.kubas.androidar.core.CustomLoaderCallback;
import pl.kubas.androidar.core.Functionalities;

public class MainActivity extends AppCompatActivity implements ActivityCameraCallback {

    @BindView(R.id.buttons_recyclerview)
    RecyclerView buttonsRecyclerView;

    @BindView(R.id.camera_preview)
    JavaCameraView cameraView;

    private Functionalities functionalities = new Functionalities();
    private CustomLoaderCallback customLoaderCallback;
    private Origin origin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setupOrigin();
        ButterKnife.bind(this);
        initRecyclerView();
        setUpCamera();
        setupImageDirectory();
    }

    private void setupImageDirectory() {
        origin.setupTemplateImages(getAssets());
    }


    private void setUpCamera() {
        cameraView.setVisibility(SurfaceView.VISIBLE);
    }

    private void setupOrigin() {
        origin = Origin.getOrigin(this);
        origin.askForPermission(this, "android.permission.CAMERA", 200);
        origin.askForPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE", 112);
        origin.showToast(this, "Application requires landscape mode");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (cameraView != null)
            cameraView.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadOpenCV();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraView != null)
            cameraView.disableView();
    }

    private void loadOpenCV() {
        customLoaderCallback = new CustomLoaderCallback(this);
        if (!OpenCVLoader.initDebug()) {
            Log.d(Origin.TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, this, customLoaderCallback);
        } else {
            Log.d(Origin.TAG, "OpenCV library found inside package. Using it!");
            customLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    private void initRecyclerView() {
        buttonsRecyclerView.setHasFixedSize(true);
        buttonsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ButtonAdapter buttonAdapter = new ButtonAdapter(ButtonService.obtainButtons(this));
        buttonsRecyclerView.setAdapter(buttonAdapter);
    }


    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public JavaCameraView getCameraView() {
        return this.cameraView;
    }

    @Override
    public Functionalities getFunctionalities() {
        return this.functionalities;
    }
}
