package lincoln.imageframework.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import lincoln.imageframework.fresco.FrescoBaseActivity;
import lincoln.imageframework.glide.GlideBaseActivity;
import lincoln.imageframework.lincoln.LincolnImageActivity;
import lincoln.imageframework.picasso.PicassoBaseActivity;
import lincoln.imageframework.universalimageloader.ImageLoaderBaseActivity;

/**
 * Created by lincoln on 16/9/19.
 */
public class MainAdapter extends RecyclerView.Adapter {
    String[] arrays = new String[]{"ImageLoader", "Picasso","Glide","Fresco","Self"};
    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return arrays.length;
    }

    NormalHolder normalHolder;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("lincoln", "NormalHolder");
        Button button = new Button(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        normalHolder = new NormalHolder(button);
        return normalHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        normalHolder.button.setText(arrays[position]);
        normalHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(context, ImageLoaderBaseActivity.class);
                        break;
                    case 1:
                        intent.setClass(context, PicassoBaseActivity.class);
                        break;
                    case 2:
                        intent.setClass(context, GlideBaseActivity.class);
                        break;
                    case 3:
                        intent.setClass(context, FrescoBaseActivity.class);
                        break;
                    case 4:
                        intent.setClass(context, LincolnImageActivity.class);
                        break;
                }
                context.startActivity(intent);

            }
        });
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        Button button;

        public NormalHolder(View itemView) {
            super(itemView);
            this.button = (Button) itemView;

        }
    }
}
