package cn.shui.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFragment extends Fragment {

    public static final String TITLE = "title";
    private String mTitle;

    public static TestFragment newInstance(String position) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);

        TextView textView = view.findViewById(R.id.text_view);
        textView.setText(mTitle);

        return view;
    }
}
