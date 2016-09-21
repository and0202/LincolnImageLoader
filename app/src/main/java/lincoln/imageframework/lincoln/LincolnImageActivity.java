package lincoln.imageframework.lincoln;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.imageframework.R;
import lincoln.imageframework.activity.BaseActivity;
import lincoln.imageframework.lincoln.core.downloader.DownloaderUtil;

public class LincolnImageActivity extends BaseActivity {


    @BindView(R.id.view_iv_normal)
    ImageView viewIvNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lincoln_image);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        DownloaderUtil downloadUtil = DownloaderUtil.getInstance(this);
        downloadUtil.displayImage(viewIvNormal,url);
    }


}
