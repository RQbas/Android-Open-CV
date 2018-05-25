package pl.kubas.androidar.context;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import pl.kubas.androidar.R;
import pl.kubas.androidar.core.ImageTemplateInitializer;

public class Origin extends Application {
    private static final Origin origin = new Origin();
    public static final String TAG = "AR";
    private static String directoryPath;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static Origin getOrigin(Context context) {
        return context == null ? null : (Origin) context.getApplicationContext();
    }

    public void askForPermission(Activity activity, String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            }
        }
    }

    public void showToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_IN);
        TextView textView = view.findViewById(android.R.id.message);
        textView.setTextColor(getResources().getColor(R.color.white));
        toast.show();
    }

    public void setupTemplateImages(AssetManager assetManager) {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "AR");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            directoryPath = folder.getPath();
            new ImageTemplateInitializer(assetManager, folder).execute();
        } else {
            showToast(this, "Problem with template directory");
        }

    }

    public String getDirectoryPath() {
        if (directoryPath != null || !directoryPath.isEmpty()) {
            return directoryPath;
        } else {
            return "";
        }
    }


}
