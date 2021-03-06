package com.admaster.imageloader;

import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.admaster.imageloader.util.StringUtil;

/**
 * 在UI主线程中展示图片类
 * Created by lincoln on 16/9/21.
 */
public class DisplayeImageeRunnable implements Runnable {
    private Bitmap bitmap;
    private int bitmapId = 0;
    private ImageView imageView;
    private String url ;

    public DisplayeImageeRunnable(Bitmap bitmap, ImageView imageView,String url ) {
        this.bitmap = bitmap;
        this.imageView = imageView;
        this.url = url;
    }

    public DisplayeImageeRunnable(int bitmapId, ImageView imageView,String url) {
        this.bitmapId = bitmapId;
        this.imageView = imageView;
        this.url = url;
    }

    @Override
    public void run() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.d("lincoln", "displayerrunnable run In MainUiThread ");
            if (bitmap != null && !bitmap.isRecycled()) {
                String tagUrl = (String)imageView.getTag();
                if (StringUtil.isTheSame(tagUrl,url)){
                    imageView.setImageBitmap(bitmap);
                }
            }else{
                Log.d("lincoln", "displayerrunnable run bitmap is null or recycler ");
            }

            if (bitmapId != 0) {
//                imageView.setImageResource(bitmapId);
            }else{
//                imageView.setImageBitmap();
            }
        } else {
            Log.d("lincoln", "displayerrunnable run Not In MainUiThread ");
        }

    }
}
