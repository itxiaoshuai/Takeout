package com.arvin.takeout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arvin.takeout.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Arvin on 2017/7/27 17:12
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
    public HomeRvAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    /**
     * 每次有新的数据就刷新一次
     * @param datas
     */
    public void setDatas(List<String> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    private Context mContext;
    private List<String> mDatas;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_home_common, null);
        HomeItemHolder viewHolder = new HomeItemHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String data=mDatas.get(position);
        HomeItemHolder homeItemHolder= (HomeItemHolder) holder;
        homeItemHolder.setData(data);
    }

    @Override
    public int getItemCount() {
        if (mDatas!=null){
            return mDatas.size();
        }
        return 0;
    }

    class HomeItemHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv)
        TextView mTv;

        HomeItemHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(String data) {
            mTv.setText(data);
        }
    }
}
