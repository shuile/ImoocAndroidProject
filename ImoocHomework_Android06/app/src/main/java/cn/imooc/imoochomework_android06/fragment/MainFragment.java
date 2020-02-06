package cn.imooc.imoochomework_android06.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.imooc.imoochomework_android06.R;
import cn.imooc.imoochomework_android06.activity.LocationActivity;
import cn.imooc.imoochomework_android06.adapter.MainFirstAdapter;
import cn.imooc.imoochomework_android06.adapter.MainSecondAdapter;
import cn.imooc.imoochomework_android06.util.DataUtil;

import static android.app.Activity.RESULT_OK;
import static cn.imooc.imoochomework_android06.util.Constant.REQUESTCODE_MAIN_FRAGMENT_TO_LOCATION_ACTIVITY;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private Button mLocationBtn;

    private int[] mainFirstIcons = {R.drawable.fly1, R.drawable.car, R.drawable.autombile1,
                R.drawable.cake, R.drawable.food, R.drawable.watch, R.drawable.cp, R.drawable.phone};
    private String[] mainFirstName;
    private RecyclerView mMainFirstRv;

    private int[] mainSecondPic = {R.drawable.pro1, R.drawable.pro2, R.drawable.product3, R.drawable.product4};
    private String[] mainSecondName;
    private String[] mainSecondDes;
    private double[] mainSecondPrice = {39.9, 99.9, 12, 9.9, 99.9};
    private boolean[] mainSecondIsNew = {true, true, true, false, false};
    private int[] mainSecondSaleNumber = {4444, 3333, 2222, 1111, 666};
    private RecyclerView mMainSecondRv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLocationBtn = (Button) getView().findViewById(R.id.location_btn);
        mMainFirstRv = (RecyclerView) getView().findViewById(R.id.main_first_rv);
        mMainSecondRv = (RecyclerView) getView().findViewById(R.id.main_second_rv);

        mLocationBtn.setOnClickListener(this);

        mainFirstName = this.getActivity().getResources().getStringArray(R.array.main_first_name);
        mMainFirstRv.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        MainFirstAdapter mainFirstAdapter = new MainFirstAdapter(getActivity(), DataUtil.getMainFirstMenu(mainFirstIcons, mainFirstName));
        mMainFirstRv.setAdapter(mainFirstAdapter);

        mainSecondName = this.getActivity().getResources().getStringArray(R.array.food_name);
        mainSecondDes = this.getActivity().getResources().getStringArray(R.array.food_des);
        mMainSecondRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainSecondAdapter mainSecondAdapter = new MainSecondAdapter(getActivity(),
                DataUtil.getFood(mainSecondPic, mainSecondName, mainSecondDes, mainSecondPrice, mainSecondIsNew, mainSecondSaleNumber));
        mMainSecondRv.setAdapter(mainSecondAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.location_btn:
                startActivityForResult(new Intent(getActivity(), LocationActivity.class), REQUESTCODE_MAIN_FRAGMENT_TO_LOCATION_ACTIVITY);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTCODE_MAIN_FRAGMENT_TO_LOCATION_ACTIVITY:
                if (RESULT_OK != resultCode) {
                    return;
                }
                if (data == null) {
                    return;
                }
                mLocationBtn.setText(data.getStringExtra("location"));
                break;
            default:
                break;
        }
    }
}
