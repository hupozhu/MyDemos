package com.sampson.android.mydemos.views.NativeViewPlus.ViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sampson.android.mydemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyang on 2017/4/11.
 */

public class DemoPagerAdpater extends PagerAdapter implements CardAdapter {

    private List<String> mDatas;
    private List<CardView> mViews;

    private Context mContext;
    private int maxCardElevation = 9;

    public DemoPagerAdpater(Context context) {
        mDatas = new ArrayList<>();
        mViews = new ArrayList<>();

        mContext = context;
    }

    public void setImgUrls(List<String> datas) {
        mDatas.clear();
        mDatas.addAll(datas);

        for (int i = 0; i < mDatas.size(); i++) {
            mViews.add(null);
        }
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public void setMaxElevationFactor(int evaluateFactor) {
        this.maxCardElevation = evaluateFactor;
    }

    @Override
    public int getMaxElevationFactor() {
        return maxCardElevation;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewpager_cardview_item, null);
        container.addView(view);

        bind(mDatas.get(position), view);

        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(maxCardElevation);

        mViews.set(position, cardView);
        return view;
    }

    private void bind(String imgUrl, View view) {
        ImageView iv = (ImageView) view.findViewById(R.id.item_iv);
        Glide.with(mContext).load(imgUrl).into(iv);
    }
}
