package lincoln.imageframework.lincoln.core.downloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lincoln.imageframework.R;
import lincoln.imageframework.lincoln.core.cache.disk.DiskCache;
import lincoln.imageframework.lincoln.core.cache.disk.DiskCacheConfigFactory;
import lincoln.imageframework.lincoln.core.cache.memory.BaseMemoryCache;
import lincoln.imageframework.lincoln.core.cache.memory.impl.WeakMemoryCache;
import lincoln.imageframework.lincoln.core.cache.util.DisplayerRunnable;

/**
 * Created by lincoln on 16/9/20.
 */
public class DownloaderUtil {
    private int MAX_POOL_COUNT = 10;
    private static DownloaderUtil downloadUtil;
    private static Context mContext;

    private ExecutorService pool;
    private BaseMemoryCache memoryCache;
    private DiskCache diskCache;
    private Handler handler = new Handler(Looper.getMainLooper());

    private DownloaderUtil() {
        pool = Executors.newFixedThreadPool(MAX_POOL_COUNT);
        memoryCache = new WeakMemoryCache();
        diskCache = DiskCacheConfigFactory.createDefaultDiskCache();
    }

    public static DownloaderUtil getInstance(Context context) {
        if (downloadUtil == null) {
            downloadUtil = new DownloaderUtil();
            mContext = context;
        }
        return downloadUtil;
    }


    public void displayImage(ImageView imageView, String url, DownloadCallback callback) {
        //从内存缓存中读取
        Bitmap bitmapMemory = memoryCache.get(url);
        if (bitmapMemory != null) {
            Log.d("lincoln", "image from memory cache ");
            callback.onSuccess(bitmapMemory);
            return;
        }
        //从本地磁盘读取

        //从网络读取
        startRunnable(new DownloadRunnable(url, callback, diskCache));
    }


    public void displayImage(final ImageView imageView, final String url) {
        displayImage(imageView, url, new DownloadCallback() {
            @Override
            public void onFailed() {
                Log.d("lincoln", "onfailed");
                handler.post(new DisplayerRunnable(R.mipmap.ic_launcher, imageView));
            }

            @Override
            public void onSuccess(Bitmap bitmap) {
                if (bitmap == null || bitmap.isRecycled()) {
                    Log.d("lincoln", "onSuccess run bitmap is null or recycler ");
                    return;
                }
                Log.d("lincoln", " onSuccess:");

                DisplayerRunnable displayerRunnable = new DisplayerRunnable(bitmap, imageView);
                handler.post(displayerRunnable);

                try {
                    memoryCache.put(url, bitmap);
                    boolean reuslt = diskCache.save(url, bitmap);
                    Log.d("lincoln", "disk cache save " + reuslt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void startRunnable(DownloadRunnable runnable) {
        pool.submit(runnable);
    }
}
