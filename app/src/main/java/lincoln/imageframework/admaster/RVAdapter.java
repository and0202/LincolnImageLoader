package lincoln.imageframework.admaster;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.imageframework.R;

/**
 * Created by lincoln on 16/9/21.
 */
public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list = new ArrayList<>();
    private Context context;

    public RVAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lincoln_recyclerview_item, parent, false);
        return new NormalHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("lincoln", "count:" + getItemCount() + " position:" + position);
        NormalHolder normalHolder = (NormalHolder) holder;
        String url = list.get(position);

        //lincoln
        LoaderUtil.load(context, normalHolder.viewIvNormal, url);
    }

    public static class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view_iv_normal)
        ImageView viewIvNormal;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
