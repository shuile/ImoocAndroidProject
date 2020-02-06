package cn.imooc.imoocpractice_android01.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import cn.imooc.imoocpractice_android01.R;

public class CreateFragment extends Fragment implements View.OnClickListener {

    private ImageView mBack;
    private ImageView mClose;
    private EditText mTitle;
    private EditText mContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        mBack = (ImageView) view.findViewById(R.id.back);
        mClose = (ImageView) view.findViewById(R.id.close);
        mTitle = (EditText) view.findViewById(R.id.title_et);
        mContent = (EditText) view.findViewById(R.id.content_et);
        mBack.setOnClickListener(this);
        mClose.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (onCreateFragmentListener != null) {
                    onCreateFragmentListener.onCreateFragmentClick();
                }
                break;
            case R.id.close:
                mTitle.setText("");
                mContent.setText("");
                break;
        }
    }

    private OnCreateFragmentListener onCreateFragmentListener;

    public void setOnCreateFragmentListener(OnCreateFragmentListener onCreateFragmentListener) {
        this.onCreateFragmentListener = onCreateFragmentListener;
    }

    public interface OnCreateFragmentListener {
        void onCreateFragmentClick();
    }
}
