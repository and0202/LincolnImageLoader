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
import lincoln.imageframework.lincoln.imageloader.cache.memory.MemoryCache;
import lincoln.imageframework.lincoln.imageloader.cache.memory.impl.LruMemoryCache;
import lincoln.imageframework.lincoln.imageloader.config.DisplayConfigFactory;
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
    private MemoryCache memoryCache;
    private DiskCache diskCache;
    private Handler handler = new Handler(Looper.getMainLooper());
    private DisplayImageConfig displayImageConfig;

    private ImageLoader() {
        pool = Executors.newFixedThreadPool(MAX_POOL_COUNT);
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        Log.d("lincoln","memory:"+maxMemory);
        memoryCache = new LruMemoryCache(maxMemory/8);
        diskCache = DiskCacheConfigFactory.createDefaultDiskCache();
        if (displayImageConfig == null){
            displayImageConfig = DisplayConfigFactory.getDefaultDisplayImageConfig();
        }
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
        imageView.setImageResource(R.drawable.loading_place);
        //从硬盘读取，没有则网络读取
        startRunnable(new DownloadRunnable(imageView,displayImageConfig,url, callback, diskCache,memoryCache));
    }


    public void displayImage(final ImageView imageView, final String url) {
        //解决错位、闪烁的问题
        imageView.setTag(url);
        displayImage(imageView, url, new DownloadCallback() {
            @Override
            public void onFailed() {
                Log.d("lincoln", "onfailed");
                handler.post(new DisplayeImageeRunnable(R.drawable.loading_error, imageView,url));
            }

            @Override
            public void onSuccess(Bitmap bitmap) {
                if (bitmap == null || bitmap.isRecycled()) {
                    Log.d("lincoln", "onSuccess run bitmap is null or recycler ");
                    return;
                }
                Log.d("lincoln", " onSuccess:"+url);

                DisplayeImageeRunnable displayerRunnable = new DisplayeImageeRunnable(bitmap, imageView,url);
                handler.post(displayerRunnable);

            }

            @Override
            public void showHolder() {
                DisplayeImageeRunnable displayerRunnable = new DisplayeImageeRunnable(R.drawable.loading_place, imageView,url);
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

    public void setImageWidth(int width,int height){
        DisplayImageConfig config  = new DisplayImageConfig(width,height);
        this.displayImageConfig = config;
    }

}
