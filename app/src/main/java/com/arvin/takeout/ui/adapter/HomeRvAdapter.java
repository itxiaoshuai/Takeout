package com.arvin.takeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arvin.takeout.R;
import com.arvin.takeout.module.beans.Seller;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Arvin on 2017/7/27 17:12
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Seller> mOtherSellers;
    private List<Seller> mNearbySellers;


    public static final int GROUP_SIZE = 10;
    public static final int TYPE_TITLE = 0; //头部
    public static final int TYPE_DIVISION = 1; //附近商家和普通商家以分割线隔开，普通商家数量超过一组长度，中间需要再添加分割线
    public static final int TYPE_SELLER = 2;


    public HomeRvAdapter(Context context, List<Seller> nearbySellers) {
        mNearbySellers = nearbySellers;
        mContext = context;


    }

    public void setDatas(List<Seller> nearbySellers, List<Seller> otherSellers) {
        mNearbySellers = nearbySellers;
        mOtherSellers = otherSellers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else if (position == mNearbySellers.size() + 1) {
            //附近商家排列完成后就是第一个分割线
            return TYPE_DIVISION;
        } else if ((position - 1 - mNearbySellers.size()) % (GROUP_SIZE + 1) == 0) {
            //头部、附近1、附近2、分割线1、（普通1，普通2，分割线），（普通3，普通4，分割线），（普通5，普通6，分割线），（普通7）
            //普通商家内部的分割线
            return TYPE_DIVISION;
        } else {
            return TYPE_SELLER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case TYPE_TITLE:
                itemView = View.inflate(mContext, R.layout.item_title, null);
                return new TitleHolder(itemView);
            case TYPE_DIVISION:
                itemView = View.inflate(mContext, R.layout.item_division, null);
                return new DivisionHolder(itemView);
            case TYPE_SELLER:
                itemView = View.inflate(mContext, R.layout.item_seller, null);
                return new SellerHolder(itemView);
            default:
                Toast.makeText(mContext, "竟然有第四种holder", Toast.LENGTH_SHORT).show();
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_TITLE:
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.setData("");
                break;
            case TYPE_DIVISION:
                DivisionHolder divisionHolder = (DivisionHolder) holder;
                divisionHolder.setData("");
                break;
            case TYPE_SELLER:
                SellerHolder sellerHolder = (SellerHolder) holder;
                //TODO:区分附近商家和普通商家
                int index;
                if (position < mNearbySellers.size() + 1) {
                    index = position - 1;
                    Seller nearbyData = mNearbySellers.get(index);
                    sellerHolder.setData(nearbyData);
                } else {
                    //减去头、附近、第一个分割线
                    index = position - 1 - mNearbySellers.size() - 1;
                    //减去如果达到第二个或者更后面的分割线
                    index -= index / (GROUP_SIZE + 1);
                    Seller otherData = mOtherSellers.get(index);
                    sellerHolder.setData(otherData);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        //如果所有商家都为空的时候
        if (mNearbySellers.size() == 0 && mOtherSellers.size() == 0) {
            return 0;
        }
        //如果只有头部的时候
        int count = 1;
        if (mNearbySellers != null) {
            count += mNearbySellers.size();
            count += 1;//附近商家后的分割线
        }
        //然后加上普通商家
        count += mOtherSellers.size();
        //在加上普通商家间的分割线
        int divsionCount = mOtherSellers.size() / GROUP_SIZE;
        if (mOtherSellers.size() % GROUP_SIZE == 0) {//如果刚好整除
            divsionCount -= 1;
        }
        count += divsionCount;
        return count;
    }

    class SellerHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tvCount)
        TextView mTvCount;
        @InjectView(R.id.tv_title)
        TextView mTvTitle;
        @InjectView(R.id.ratingBar)
        RatingBar mRatingBar;
        @InjectView(R.id.tv_home_sale)
        TextView mTvHomeSale;
        @InjectView(R.id.tv_home_send_price)
        TextView mTvHomeSendPrice;
        @InjectView(R.id.tv_home_distance)
        TextView mTvHomeDistance;

        SellerHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(Seller seller) {
            mTvTitle.setText(seller.getName());
        }
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.slider)
        SliderLayout mSliderLayout;

        TitleHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(String data) {
            testData(mContext);
        }

        HashMap<String, String> url_maps = new HashMap<String, String>();

        private void testData(Context context) {
            //重复加载导致图片增多的问题
            mSliderLayout.removeAllSliders();
            url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
            url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
            url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
            url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

            for (String desc : url_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(itemView.getContext());
                textSliderView
                        .description(desc)
                        .image(url_maps.get(desc));
                mSliderLayout.addSlider(textSliderView);
            }
        }
    }

    class DivisionHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_division_title)
        TextView mTvDivisionTitle;

        DivisionHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(String data) {
            mTvDivisionTitle.setText(data);
        }
    }
}
