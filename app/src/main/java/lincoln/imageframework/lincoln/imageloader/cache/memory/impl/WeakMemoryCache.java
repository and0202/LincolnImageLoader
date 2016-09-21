package lincoln.imageframework.lincoln.imageloader.cache.memory.impl;

import android.graphics.Bitmap;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import lincoln.imageframework.lincoln.imageloader.cache.memory.BaseMemoryCache;

/**
 * Created by lincoln on 16/9/21.
 */
public class WeakMemoryCache extends BaseMemoryCache{
    @Override
    protected Reference<Bitmap> createReference(Bitmap value) {
        return new  WeakReference<Bitmap>(value);
    }
}
