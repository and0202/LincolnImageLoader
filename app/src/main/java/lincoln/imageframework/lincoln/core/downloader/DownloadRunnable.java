package lincoln.imageframework.lincoln.core.downloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lincoln on 16/9/20.
 */
public class DownloadRunnable implements Runnable {
    public String urlString ;
    public DownloadCallback callback;

    public DownloadRunnable( String urlString,DownloadCallback callback) {
        this.callback = callback;
        this.urlString = urlString;
    }

    @Override
    public void run() {
        try {
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
