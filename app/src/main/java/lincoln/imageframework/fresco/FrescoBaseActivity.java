package lincoln.imageframework.fresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.imageframework.R;
import lincoln.imageframework.activity.BaseActivity;

/**
 * Created by lincoln on 16/9/19.
 */
public class FrescoBaseActivity extends BaseActivity {

    @BindView(R.id.view_iv_normal)
    SimpleDraweeView viewIvNormal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fresco_base);
        ButterKnife.bind(this);
//        Fresco.initialize(this);

        initData();
    }

    public void initData() {

//        ControllerListener listener = new BaseControllerListener(){
//            @Override
//            public void onFailure(String id, Throwable throwable) {
//                super.onFailure(id, throwable);
//            }
//
//            @Override
//            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
//                super.onFinalImageSet(id, imageInfo, animatable);
//            }
//
//            @Override
//            public void onIntermediateImageFailed(String id, Throwable throwable) {
//                super.onIntermediateImageFailed(id, throwable);
//            }
//
//            @Override
//            public void onIntermediateImageSet(String id, Object imageInfo) {
//                super.onIntermediateImageSet(id, imageInfo);
//            }
//
//            @Override
//            public void onRelease(String id) {
//                super.onRelease(id);
//            }
//
//            @Override
//            public void onSubmit(String id, Object callerContext) {
//                super.onSubmit(id, callerContext);
//            }
//        };
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(url)
//                .setTapToRetryEnabled(true)
//                .setOldController(viewIvNormal.getController())
//                .setControllerListener(listener)
//                .build();
//
//        viewIvNormal.setController(controller);
//        //自定义DraweeHierarchy
//        GenericDraweeHierarchy hierarchy =  viewIvNormal.getHierarchy();
//        hierarchy.setPlaceholderImage(R.mipmap.ic_launcher);
//
//        RoundingParams roundingParams = hierarchy.getRoundingParams();
//        roundingParams.setCornersRadius(10);
//        hierarchy.setRoundingParams(roundingParams);
//        hierarchy.setProgressBarImage(new ProgressBarDrawable());


        viewIvNormal.setImageURI(Uri.parse(url));
    }

    public void Test(){
        DraweeView d ;
    }
}
