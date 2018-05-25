package pl.kubas.androidar.core;

import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageTemplateInitializer extends AsyncTask<Void, Void, Void> {
    private File directory;
    private AssetManager assets;

    public ImageTemplateInitializer(AssetManager assetManager, File directory) {
        this.directory = directory;
        this.assets = assetManager;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            for (String imageName : assets.list("")) {
                File targetFile = new File(directory.getPath() + "/" + imageName);
                copyInputStreamToFile(assets.open(imageName), targetFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void copyInputStreamToFile(InputStream in, File file) {
        OutputStream out = null;

        try {
            out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
