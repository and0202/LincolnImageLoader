package lincoln.imageframework.lincoln.imageloader.cache.disk;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.utils.IoUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lincoln on 16/9/21.
 */
public interface DiskCache {

    File getDirectory();

    File get(String imageUrl);

    boolean save(String imageUrl, Bitmap bitmap) throws IOException;

    boolean save(String imageUri, InputStream imageStream, IoUtils.CopyListener listener) throws IOException;


    boolean remove(String imageUrl);

    void close();

    void clear();
}

