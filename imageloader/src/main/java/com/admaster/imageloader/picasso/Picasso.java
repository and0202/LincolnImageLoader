package com.admaster.imageloader.picasso;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by lincoln on 16/9/26.
 */
public class Picasso {
    private Context context;
    //TODO New Code
    private Picasso(Context context) {
        this.context = context.getApplicationContext();
    }
    public RequestCreator load(@Nullable String uri){
        if (uri == null) {
            return new RequestCreator(context, Uri.parse(uri), 0);
        }
        if (uri.trim().length() == 0) {
            throw new IllegalArgumentException("Path must not be empty.");
        }
        return load(Uri.parse(uri));
    }

    public RequestCreator load(@Nullable Uri uri) {
        return new RequestCreator(context, uri, 0);
    }


    static volatile Picasso singleton = null;

    public static Picasso whith(Context context){
        if (context == null){
            throw new IllegalArgumentException("Contex == null");
        }

        if (singleton ==null){
            synchronized (Picasso.class){
                if (singleton == null){
                    singleton = new Picasso(context);
                }
            }
        }
        return singleton;
    }


    public static class Builder{
        private  Context context;

        public Builder(Context context){
            if (context == null){
                throw new IllegalArgumentException("Context must not be null");
            }
            this.context = context.getApplicationContext();
        }

        public Picasso build() {
            Context context = this.context;
            return new Picasso(context);
        }
    }
}
