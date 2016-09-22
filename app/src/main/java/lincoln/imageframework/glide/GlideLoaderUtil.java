package lincoln.imageframework.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import lincoln.imageframework.R;

/**
 * Created by lincoln on 16/9/22.
 */
public class GlideLoaderUtil {
    public static void load(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).placeholder(R.drawable.loading_place).error(R.drawable.loading_error).into(imageView);

    }
}
