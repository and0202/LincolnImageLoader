package lincoln.imageframework.admaster;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.imageframework.R;
import lincoln.imageframework.main.activity.BaseActivity;

public class AdmasterActivity extends BaseActivity {


    @BindView(R.id.lincoln_recyclerview)
    RecyclerView lincolnRecyclerview;
    private List<String> list = new ArrayList<>();
    private RVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lincoln_image);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        list.addAll(Arrays.asList(arrays));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lincolnRecyclerview.setLayoutManager(layoutManager);
        adapter = new RVAdapter(this, list);
        lincolnRecyclerview.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        ImageLoader.getInstance(this).clearMemoryCache();
    }
}
