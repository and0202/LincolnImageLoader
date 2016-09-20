package lincoln.imageframework.lincoln.core.downloader;

import android.graphics.Bitmap;

/**
 * Created by lincoln on 16/9/20.
 */
public interface DownloadCallback {
    void onSuccess(Bitmap bitmap);
    void onFailed();
}
