package lincoln.imageframework.fresco;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import lincoln.imageframework.R;

/**
 * Created by lincoln on 16/9/22.
 */
public class FrescoLoaderUtil {

    public static void loader(SimpleDraweeView viewIvNormal){
        //自定义DraweeHierarchy
        GenericDraweeHierarchy hierarchy =  viewIvNormal.getHierarchy();
        hierarchy.setPlaceholderImage(R.mipmap.ic_launcher);

        RoundingParams roundingParams = hierarchy.getRoundingParams();
        roundingParams.setCornersRadius(10);
        hierarchy.setRoundingParams(roundingParams);
        hierarchy.setProgressBarImage(new ProgressBarDrawable());
    }
}
