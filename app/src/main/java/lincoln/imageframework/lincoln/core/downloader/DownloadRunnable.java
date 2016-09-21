package lincoln.imageframework.lincoln.core.downloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import lincoln.imageframework.lincoln.core.cache.disk.DiskCache;

/**
 * Created by lincoln on 16/9/20.
 */
public class DownloadRunnable implements Runnable {
    public String urlString ;
    public DownloadCallback callback;
    private DiskCache diskCache;

    public DownloadRunnable(String urlString, DownloadCallback callback, DiskCache diskCache) {
        this.callback = callback;
        this.urlString = urlString;
        this.diskCache = diskCache;
    }

    @Override
    public void run() {
        if (!loadFromDisk()){
            loadFromHttp();
        }

    }

    private boolean  loadFromDisk(){
        boolean result = false;
        try {
            if (diskCache.get(urlString) != null) {
                Bitmap bitmapDisk = BitmapFactory.decodeStream(new FileInputStream(diskCache.get(urlString)));
                if (bitmapDisk != null) {
                    Log.d("lincoln", "image from disk cache: ");
                    callback.onSuccess(bitmapDisk);
                    result = true;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private void loadFromHttp(){
        try {
            Log.d("lincoln","image from http");
            URL url  = new URL(urlString);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();

            InputStream in = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            if (bitmap != null){
                callback.onSuccess(bitmap);
            }else {
                callback.onFailed();
            }

        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailed();

        }
    }
}
