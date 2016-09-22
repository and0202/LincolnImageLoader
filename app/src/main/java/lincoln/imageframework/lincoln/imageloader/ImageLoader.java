package lincoln.imageframework.lincoln.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lincoln.imageframework.R;
import lincoln.imageframework.lincoln.imageloader.cache.disk.DiskCache;
import lincoln.imageframework.lincoln.imageloader.cache.disk.DiskCacheConfigFactory;
import lincoln.imageframework.lincoln.imageloader.cache.memory.BaseMemoryCache;
import lincoln.imageframework.lincoln.imageloader.cache.memory.impl.WeakMemoryCache;
import lincoln.imageframework.lincoln.imageloader.downloader.DownloadCallback;
import lincoln.imageframework.lincoln.imageloader.downloader.DownloadRunnable;

/**
 * Created by lincoln on 16/9/20.
 */
public class ImageLoader {
    private int MAX_POOL_COUNT = 10;
    private static ImageLoader downloadUtil;
    private static Context mContext;

    private ExecutorService pool;
    private BaseMemoryCache memoryCache;
    private DiskCache diskCache;
    private Handler handler = new Handler(Looper.getMainLooper());

    private ImageLoader() {
        pool = Executors.newFixedThreadPool(MAX_POOL_COUNT);
        memoryCache = new WeakMemoryCache();
        diskCache = DiskCacheConfigFactory.createDefaultDiskCache();
    }

    public static ImageLoader getInstance(Context context) {
        if (downloadUtil == null) {
            downloadUtil = new ImageLoader();
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
        startRunnable(new DownloadRunnable(url, callback, diskCache,memoryCache));
    }


    public void displayImage(final ImageView imageView, final String url) {
        displayImage(imageView, url, new DownloadCallback() {
            @Override
            public void onFailed() {
                Log.d("lincoln", "onfailed");
                handler.post(new DisplayeImageeRunnable(R.drawable.loading_error, imageView));
            }

            @Override
            public void onSuccess(Bitmap bitmap) {
                if (bitmap == null || bitmap.isRecycled()) {
                    Log.d("lincoln", "onSuccess run bitmap is null or recycler ");
                    return;
                }
                Log.d("lincoln", " onSuccess:");

                DisplayeImageeRunnable displayerRunnable = new DisplayeImageeRunnable(bitmap, imageView);
                handler.post(displayerRunnable);

            }
        });
    }

    private void startRunnable(DownloadRunnable runnable) {
        pool.submit(runnable);
    }


    public void clearMemoryCache(){
        memoryCache.clear();
    }
}
