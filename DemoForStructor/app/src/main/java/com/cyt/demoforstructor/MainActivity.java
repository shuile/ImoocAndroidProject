package com.cyt.demoforstructor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cyt.demoforstructor.bean.GankBean;
import com.cyt.demoforstructor.network.GankApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button requestBtn;
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestBtn = (Button) findViewById(R.id.btn_request);
        resultTv = (TextView) findViewById(R.id.tv_result);

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://gank.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                GankApi api = retrofit.create(GankApi.class);
                Call<GankBean> call = api.getAndroidInfo();
                call.enqueue(new Callback<GankBean>() {
                    @Override
                    public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                        GankBean.ResultsBean bean = response.body().getResults().get(0);
                        resultTv.setText(bean.toString());
                    }

                    @Override
                    public void onFailure(Call<GankBean> call, Throwable t) {

                    }
                });
            }
        });
    }
}
