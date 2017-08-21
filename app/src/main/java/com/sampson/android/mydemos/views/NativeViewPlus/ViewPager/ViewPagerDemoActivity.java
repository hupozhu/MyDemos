package com.sampson.android.mydemos.views.NativeViewPlus.ViewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.sampson.android.mydemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyang on 2017/4/10.
 */

public class ViewPagerDemoActivity extends AppCompatActivity {

    private ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_activity);

        initView();
    }

    private void initView() {
        initImgList();
        pager = (ViewPager) findViewById(R.id.pager);
        DemoPagerAdpater adpater = new DemoPagerAdpater(this);
        adpater.setImgUrls(imgList);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mWidth = metrics.widthPixels;
        float heightRatio = 0.565f;  //高是宽的 0.565 ,根据图片比例

        int mWidthPading = mWidth / 5;

        //设置阴影大小，即vPage  左右两个图片相距边框  maxFactor + 0.3*CornerRadius   *2
        //设置阴影大小，即vPage 上下图片相距边框  maxFactor*1.5f + 0.3*CornerRadius
        int maxFactor = mWidth / 25;
        adpater.setMaxElevationFactor(maxFactor);

        //因为我们adapter里的cardView CornerRadius已经写死为10dp，所以0.3*CornerRadius=3
        //设置Elevation之后，控件宽度要减去 (maxFactor + dp2px(3)) * heightRatio
        //heightMore 设置Elevation之后，控件高度 比  控件宽度* heightRatio  多出的部分
        float heightMore = (1.5f * maxFactor + dp2px(3)) - (maxFactor + dp2px(3)) * heightRatio;
        int mHeightPading = (int) (mWidthPading * heightRatio - heightMore);

        pager.setLayoutParams(new RelativeLayout.LayoutParams(mWidth, (int) (mWidth * heightRatio)));
        pager.setPadding(mWidthPading, mHeightPading, mWidthPading, mHeightPading);
        pager.setClipToPadding(false);

        pager.setAdapter(adpater);

        ShadowTransformer transformer = new ShadowTransformer();
        transformer.attachViewPager(pager, adpater);
        transformer.setZoomIn(0.2f);


    }

    private List<String> imgList;

    public void initImgList() {
        imgList = new ArrayList<>();
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490984457722&di=6d7d3e20e07fc833fc606089d01132e6&imgtype=0&src=http%3A%2F%2Fimgst.izhangheng.com%2F2016%2F08%2Fnight-beauty-girl-3.jpg");
        imgList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2946550071,381041431&fm=11&gp=0.jpg");
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490984320392&di=8290126f83c2a2c0d45be41e3f88a6d0&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201307%2F19%2F152440r9ov9ololkzdcz7d.jpg");
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490984407478&di=729b187f4939710e8b2436f9f1306dff&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201505%2F05%2F172352jrr66rda0dwdwdwz.jpg");
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
