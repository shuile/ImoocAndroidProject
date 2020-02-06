package cn.imooc.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 列表Fragment
 */
public class ListFragment extends Fragment {

    public static final String BUNDLE_TITLE = "bundle_title";
    private String mTitle;
    private User mUser;

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public class User {

    }

    public static ListFragment newInstance(String title, User user) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        fragment.setArguments(bundle);
        fragment.setmUser(user);
        return fragment;
    }

    public static ListFragment newInstance(String title) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(BUNDLE_TITLE);
        }
    }

    // 创建视图
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // new view
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(mTitle);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onClick(mTitle);
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //设置接口的方法
    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        mOnTitleClickListener = onTitleClickListener;
    }

    //定义变量
    private OnTitleClickListener mOnTitleClickListener;

    //定义接口
    public interface OnTitleClickListener {
        void onClick(String title);
    }
}
