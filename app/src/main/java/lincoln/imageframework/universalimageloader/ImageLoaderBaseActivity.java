package lincoln.imageframework.universalimageloader;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.imageframework.R;
import lincoln.imageframework.main.activity.BaseActivity;

public class ImageLoaderBaseActivity extends BaseActivity {
    @BindView(R.id.view_iv_normal)
    ImageView viewIvNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader_base);
        ButterKnife.bind(this);


        initData();

    }


    public void initData() {
//        UniversalLoaderUtil.initImageLoader(this);
//        UniversalLoaderUtil.load(this,viewIvNormal,url);
    }




}
