package cn.shui.viewpagerdemo;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ImageViewPagerAdapter extends AppCompatActivity {

    public static final int INIT_POSITION = 1;
    private ViewPager mViewPager;

    private int[] mLayoutIDs = {
            R.layout.view_first,
            R.layout.view_second,
            R.layout.view_third
    };
    private List<View> views;
    private List<View> mViews;
    
    private ViewGroup mDotViewGroup;
    private List<ImageView> mDotViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_adapter);

        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mDotViewGroup = findViewById(R.id.dot_ll);
        // 初始化数据
        mViews = new ArrayList<>();
        for (int index = 0; index < mLayoutIDs.length; index++) {
//            View view = getLayoutInflater().inflate(mLayoutID, null);
//            mViews.add(view);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            mViews.add(imageView);
            
            ImageView dot = new ImageView(this);
            dot.setImageResource(R.mipmap.ic_launcher);
            dot.setMaxWidth(100);
            dot.setMaxHeight(100);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(80, 80);
            layoutParams.leftMargin = 20;
            dot.setLayoutParams(layoutParams);
            dot.setEnabled(false);
            mDotViewGroup.addView(dot);
            mDotViews.add(dot);
        }
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setCurrentItem(INIT_POSITION);
        setDotViews(INIT_POSITION);
        
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                
            }

            @Override
            public void onPageSelected(int i) {
                setDotViews(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setDotViews(int i) {
        for (int index = 0; index < mDotViews.size(); index++) {
            mDotViews.get(index).setImageResource(i == index ? R.mipmap.diglett : R.mipmap.ic_launcher);
        }
    }

    PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mLayoutIDs.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View child = mViews.get(position);
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViews.get(position));
        }
    };
}
