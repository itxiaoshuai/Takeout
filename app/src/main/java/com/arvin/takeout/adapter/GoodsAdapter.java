package com.arvin.takeout.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.model.beans.GoodsInfo;
import com.arvin.takeout.ui.fragment.GoodsFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Arvin on 2017/8/9 20:09
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class GoodsAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<GoodsInfo> mGoodsInfoList;

    public void setGoodsInfoList(List<GoodsInfo> goodsInfoList) {
        mGoodsInfoList = goodsInfoList;
        notifyDataSetChanged();
    }

    private GoodsFragment mGoodsFragment;
    private Context mContext;

    public GoodsAdapter(GoodsFragment goodsFragment, Context context) {
        mGoodsFragment = goodsFragment;
        mContext = context;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        View headView = View.inflate(mContext,R.layout.item_type_header,null);
        ((TextView)headView).setText(mGoodsInfoList.get(position).getTypeName());
        return headView ;
    }

    @Override
    public long getHeaderId(int position) {
        return mGoodsInfoList.get(position).getTypeId();
    }

    @Override
    public int getCount() {
        if (mGoodsInfoList != null) {
            return mGoodsInfoList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mGoodsInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_goods, null);
        ViewHolder viewHolder = new ViewHolder(convertView);

        viewHolder.setData(mGoodsInfoList.get(position),position);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView mIvIcon;
        @InjectView(R.id.tv_name)
        TextView mTvName;
        @InjectView(R.id.tv_zucheng)
        TextView mTvZucheng;
        @InjectView(R.id.tv_yueshaoshounum)
        TextView mTvYueshaoshounum;
        @InjectView(R.id.tv_newprice)
        TextView mTvNewprice;
        @InjectView(R.id.tv_oldprice)
        TextView mTvOldprice;
        @InjectView(R.id.ib_minus)
        ImageButton mIbMinus;
        @InjectView(R.id.tv_count)
        TextView mTvCount;
        @InjectView(R.id.ib_add)
        ImageButton mIbAdd;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        public void setData(GoodsInfo goodsInfo, int position) {
        }
    }
}
