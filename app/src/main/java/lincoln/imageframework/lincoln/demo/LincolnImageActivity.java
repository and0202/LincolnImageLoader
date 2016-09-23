package lincoln.imageframework.lincoln.demo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.imageframework.R;
import lincoln.imageframework.activity.BaseActivity;
import lincoln.imageframework.lincoln.imageloader.ImageLoader;

public class LincolnImageActivity extends BaseActivity {


    @BindView(R.id.lincoln_recyclerview)
    RecyclerView lincolnRecyclerview;
    private List<String> list = new ArrayList<>();
    private LincolnRVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lincoln_image);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        for (int i =0;i<2;i++){
            list.addAll(Arrays.asList(arrays));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lincolnRecyclerview.setLayoutManager(layoutManager);
        adapter = new LincolnRVAdapter(this, list);
        lincolnRecyclerview.setAdapter(adapter);
//        ImageLoader downloadUtil = ImageLoader.getInstance(this);
//        downloadUtil.displayImage(viewIvNormal, url);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ImageLoader.getInstance(this).clearMemoryCache();
    }
}
