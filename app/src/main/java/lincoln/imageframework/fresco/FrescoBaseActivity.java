package lincoln.imageframework.fresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
        viewIvNormal.setImageURI(Uri.parse(url));
    }

}
