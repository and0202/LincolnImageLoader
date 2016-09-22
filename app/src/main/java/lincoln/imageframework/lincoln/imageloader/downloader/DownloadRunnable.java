package lincoln.imageframework.lincoln.imageloader.downloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import lincoln.imageframework.lincoln.imageloader.cache.disk.DiskCache;
import lincoln.imageframework.lincoln.imageloader.cache.memory.MemoryCache;
import lincoln.imageframework.lincoln.imageloader.util.BitmapUtil;
import lincoln.imageframework.lincoln.imageloader.util.StringUtil;

/**
 * Created by lincoln on 16/9/20.
 */
public class DownloadRunnable implements Runnable {
    public String urlString ;
    public DownloadCallback callback;
    private DiskCache diskCache;
    private MemoryCache memoryCache;
    private ImageView imageView;

    public DownloadRunnable(ImageView imageView, String urlString, DownloadCallback callback, DiskCache diskCache, MemoryCache memoryCache) {
        this.callback = callback;
        this.urlString = urlString;
        this.diskCache = diskCache;
        this.memoryCache = memoryCache;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        String imageViewTag =(String) imageView.getTag();
        if (!StringUtil.isTheSame(imageViewTag,urlString)){
            Log.d("lincoln","not the same imageviewTag,Cancle it ");
            return;
        }
        if (!loadFromDisk()){
//            callback.showHolder();
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
                    boolean saveMemoryResult = memoryCache.put(urlString, bitmapDisk);
                    Log.d("lincoln", "memory cache save:"+saveMemoryResult);

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
            String imageViewTag =(String) imageView.getTag();
            if (!StringUtil.isTheSame(imageViewTag,urlString)){
                Log.d("lincoln","not the same imageviewTag,Cancle it ");
                return;
            }


                Log.d("lincoln","image from http");
            URL url  = new URL(urlString);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();

            InputStream in = connection.getInputStream();
            diskCache.save(urlString,in,null);
            File bitmapFile = diskCache.get(urlString);
            String bitmapPath = bitmapFile.getAbsolutePath();
            Bitmap bitmap = BitmapUtil.getSmallBitmap(bitmapPath,200,200);
            if (bitmap != null){
                callback.onSuccess(bitmap);
            }else {
                callback.onFailed();
            }
            try {
                boolean saveMemoryResult = memoryCache.put(urlString, bitmap);
                boolean reuslt = diskCache.save(urlString, bitmap);
                Log.d("lincoln", "memory cache save:"+saveMemoryResult+" disk cache save " + reuslt);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailed();

        }
    }
}
