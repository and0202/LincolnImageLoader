package lincoln.imageframework.picasso;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import lincoln.imageframework.R;
import lincoln.imageframework.lincoln.imageloader.util.StringUtil;

/**
 * Created by lincoln on 16/9/22.
 */
public class PicassoLoaderUtil {
    public static void load(Context context, ImageView imageView, String url) {
        if (StringUtil.isUseable(url)) {
            Picasso.with(context).load(url).placeholder(R.drawable.loading_place).error(R.drawable.loading_error).into(imageView);
        }
    }
}
