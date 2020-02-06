package cn.shui.viewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TabViewPagerActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private ViewPager mViewPager;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_pager);

        mViewPager = findViewById(R.id.view_pager);

        final Fragment[] fragments = new Fragment[] {
                TestFragment.newInstance("home"),
                TestFragment.newInstance("message"),
                TestFragment.newInstance("me")
        };

        // 初始化总布局
        mTabHost = findViewById(R.id.tab_host);
        mTabHost.setup();

        // 三个Tab 做处理

        int[] titleIDs = {R.string.home, R.string.message, R.string.me};
        int[] drawableIDs = {R.drawable.main_tab_icon_home,
                R.drawable.main_tab_icon_message,
                R.drawable.main_tab_icon_me};

        // data < -- > view
        for (int index = 0; index < titleIDs.length; index++) {
            View view = getLayoutInflater().inflate(R.layout.main_tab_layout, null, false);
            ImageView myIcon = view.findViewById(R.id.main_tab_icon);
            TextView title = view.findViewById(R.id.main_tab_text);

            View tab = view.findViewById(R.id.tab_bg);

            myIcon.setImageResource(drawableIDs[index]);
            title.setText(titleIDs[index]);

            tab.setBackgroundColor(getResources().getColor(R.color.white));

            mTabHost.addTab(mTabHost.newTabSpec(getString(titleIDs[index]))
                .setIndicator(view)
                .setContent(this)
            );
        }

        // 三个fragment组成的viewpager

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments[i];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (mTabHost != null) {
                    mTabHost.setCurrentTab(i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (mTabHost != null) {
                    int position = mTabHost.getCurrentTab();
                    mViewPager.setCurrentItem(position);
                }
            }
        });
    }

    @Override
    public View createTabContent(String tag) {
        View view = new View(this);
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
    }
}
