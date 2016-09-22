package lincoln.imageframework.lincoln.imageloader.downloader;

import android.graphics.Bitmap;

/**
 * Created by lincoln on 16/9/20.
 */
public interface DownloadCallback {
    void onSuccess(Bitmap bitmap);
    void onFailed();
    void showHolder();
}
