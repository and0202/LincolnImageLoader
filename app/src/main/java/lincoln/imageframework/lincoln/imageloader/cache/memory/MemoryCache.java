package lincoln.imageframework.lincoln.imageloader.cache.memory;

import android.graphics.Bitmap;

import java.util.Collection;

/**
 * Created by lincoln on 16/9/21.
 */
public interface MemoryCache {
    boolean put(String key, Bitmap value);
    Bitmap get(String key);
    Bitmap remove(String key);
    Collection<String> keys();
    void clear();
}
