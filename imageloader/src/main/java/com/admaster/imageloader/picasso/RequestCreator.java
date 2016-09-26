package com.admaster.imageloader.picasso;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.admaster.imageloader.ImageLoader;

/**
 * Created by lincoln on 16/9/26.
 */
public class RequestCreator {
    private int load_placeholder = 0;
    private int load_error = 0;
    private String fileName = null;
    private long disk_cache_max_size =0 ;
    private Uri uri;
    private Context context;

    //Default Value
    private long DEFAULT_DISK_CACHE_MAX_SIZE = 20*1024*1024;//磁盘缓存最大空间
    private String DEFAULT_FILENAME="admaster";

    RequestCreator(Context  context, Uri uri, int resourceId) {
        this.uri = uri;
        this.context = context;
    }

    public void into(ImageView imageView) {
        if (fileName == null){
            fileName = DEFAULT_FILENAME;
        }
        if (disk_cache_max_size == 0){
            disk_cache_max_size = DEFAULT_DISK_CACHE_MAX_SIZE;
        }

        ImageLoader.getInstance(context, "lincoln", disk_cache_max_size)
                .placeHolder(load_placeholder)
                .error(load_error)
                .displayImage(imageView, uri.getPath());
    }

    public RequestCreator placeholder(@DrawableRes int placeholderResId) {
        this.load_placeholder = placeholderResId;
        return this;
    }


    public RequestCreator error(@DrawableRes int errorResId) {
        this.load_error = errorResId;
        return this;
    }

    public RequestCreator fileName(String fileName){
        this.fileName = fileName;
        return this;
    }

    public RequestCreator diskMaxSize(long MByte){
        this.disk_cache_max_size = MByte*1024*1024;
        return this;
    }


}
