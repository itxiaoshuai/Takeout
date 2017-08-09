package com.arvin.takeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.model.beans.GoodsTypeInfo;
import com.arvin.takeout.ui.fragment.GoodsFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Arvin on 2017/8/9 16:55
 * E-Mail Address：it_xiaoshuai@163.com
 */

/**
 * 用于展示商品类型的recycleview
 */
public class GoodsTypeAdapter extends RecyclerView.Adapter {
    private GoodsFragment mGoodsFragment;
    private Context mContext;//需要上下文
    private List<GoodsTypeInfo> mGoodsTypeInfoList;//需要数据

    public GoodsTypeAdapter(GoodsFragment goodsFragment, Context context) {
        mGoodsFragment = goodsFragment;
        mContext = context;
    }

    public void setGoodsTypeInfoList(List<GoodsTypeInfo> goodsTypeInfoList) {
        mGoodsTypeInfoList = goodsTypeInfoList;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_type, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(mGoodsTypeInfoList.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mGoodsTypeInfoList != null) {
            return mGoodsTypeInfoList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tvCount)
        TextView mTvCount;
        @InjectView(R.id.type)
        TextView mType;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(GoodsTypeInfo goodsTypeInfo, int position) {
            mType.setText(goodsTypeInfo.getName());
        }
    }

}
