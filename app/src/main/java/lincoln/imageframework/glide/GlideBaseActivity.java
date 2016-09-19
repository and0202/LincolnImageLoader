package lincoln.imageframework.glide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.imageframework.R;
import lincoln.imageframework.activity.BaseActivity;

/**
 * Created by lincoln on 16/9/19.
 */
public class GlideBaseActivity extends BaseActivity {
    @BindView(R.id.view_iv_normal)
    ImageView viewIvNormal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader_base);
        ButterKnife.bind(this);

        initData();
    }

    public void initData(){
        Glide.with(this).load(url).into(viewIvNormal);
    }

}
