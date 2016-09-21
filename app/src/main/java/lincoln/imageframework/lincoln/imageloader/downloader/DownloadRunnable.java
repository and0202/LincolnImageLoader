package lincoln.imageframework.lincoln.imageloader.downloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import lincoln.imageframework.lincoln.imageloader.cache.disk.DiskCache;
import lincoln.imageframework.lincoln.imageloader.cache.memory.BaseMemoryCache;

/**
 * Created by lincoln on 16/9/20.
 */
public class DownloadRunnable implements Runnable {
    public String urlString ;
    public DownloadCallback callback;
    private DiskCache diskCache;
    private BaseMemoryCache memoryCache;

    public DownloadRunnable(String urlString, DownloadCallback callback, DiskCache diskCache, BaseMemoryCache memoryCache) {
        this.callback = callback;
        this.urlString = urlString;
        this.diskCache = diskCache;
        this.memoryCache = memoryCache;
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

    private void loadFromHttp( ){
        try {
            Log.d("lincoln","image from http");
            URL url  = new URL(urlString);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();

            InputStream in = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);

            try {
                memoryCache.put(urlString, bitmap);
                boolean reuslt = diskCache.save(urlString, bitmap);
                Log.d("lincoln", "disk cache save " + reuslt);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
