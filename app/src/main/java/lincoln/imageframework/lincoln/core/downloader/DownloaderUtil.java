package lincoln.imageframework.lincoln.core.downloader;

/**
 * Created by lincoln on 16/9/20.
 */
public class DownloaderUtil {

    public static void downloadImage(String url,DownloadCallback callback){
        new Thread(new DownloadRunnable(url,callback)).start();
    }
}
