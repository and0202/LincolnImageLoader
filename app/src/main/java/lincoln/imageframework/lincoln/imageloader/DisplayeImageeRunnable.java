package lincoln.imageframework.lincoln.imageloader;

import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import lincoln.imageframework.R;

/**
 * 展示图片类
 * Created by lincoln on 16/9/21.
 */
public class DisplayeImageeRunnable implements Runnable {
    private Bitmap bitmap;
    private int bitmapId = R.drawable.loading_error;
    private ImageView imageView;

    public DisplayeImageeRunnable(Bitmap bitmap, ImageView imageView) {
        this.bitmap = bitmap;
        this.imageView = imageView;
    }

    public DisplayeImageeRunnable(int bitmapId, ImageView imageView) {
        this.bitmapId = bitmapId;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.d("lincoln", "displayerrunnable run In MainUiThread ");
            if (bitmap != null && !bitmap.isRecycled()) {
                imageView.setImageBitmap(bitmap);
            }else{
                Log.d("lincoln", "displayerrunnable run bitmap is null or recycler ");
            }

            if (bitmapId != 0) {
//                imageView.setImageResource(bitmapId);
            }
        } else {
            Log.d("lincoln", "displayerrunnable run Not In MainUiThread ");
        }

    }
}
