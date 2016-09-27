package lincoln.imageframework.admaster;

import android.content.Context;
import android.widget.ImageView;

import com.admaster.imageloader.api.Picasso;
import com.admaster.imageloader.util.StringUtil;

import lincoln.imageframework.R;

/**
 * Created by lincoln on 16/9/22.
 */
public class LoaderUtil {
    private static final  long maxSize = 20*1024*1024;
    public static void load(Context context, ImageView imageView, String url) {
//        ImageLoader.getInstance(context,"lincoln",maxSize).displayImage(imageView,url);
        if (StringUtil.isUseable(url)){
            Picasso.whith(context)
                    .load(url)
                    .placeholder(R.drawable.loading_place)
                    .error(R.drawable.loading_error)
                    .into(imageView);
        }

    }

}
