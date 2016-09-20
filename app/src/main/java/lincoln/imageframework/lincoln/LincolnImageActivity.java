package lincoln.imageframework.lincoln;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.imageframework.R;
import lincoln.imageframework.activity.BaseActivity;
import lincoln.imageframework.lincoln.core.downloader.DownloadCallback;
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
        DownloaderUtil.downloadImage(url, new DownloadCallback() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                Log.d("lincoln","onSuccess");
                viewIvNormal.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed() {
                Log.d("lincoln","onFailed");
                viewIvNormal.setImageResource(R.mipmap.ic_launcher);
            }
        });

    }


}
