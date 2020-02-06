package cn.shui.moocshowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-vv";

    public static final String BROADCAST = "cn.shui.moocshowapp.getapplist";

    private EditText mEtSearch;
    private Button mBtnDeleteKeyInfo;
    private ListView mLvSearchResult;

    private AppAdapter mAdapter;
    private List<App> mAppList;

    private Handler mHandler = new Handler();
    private BroadcastReceiver receiver;

    private MainListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();

        initReceiver();

        initService();
    }

    private void initView() {
        mEtSearch = findViewById(R.id.et_search);
        mBtnDeleteKeyInfo = findViewById(R.id.btn_delete_key_info);
        mLvSearchResult = findViewById(R.id.lv_search_result);

        mAppList = new ArrayList<>();
        mAdapter = new AppAdapter(this, mAppList);
        mLvSearchResult.setAdapter(mAdapter);

        if (TextUtils.isEmpty(mEtSearch.getText().toString())) {
            mBtnDeleteKeyInfo.setVisibility(View.GONE);
        }
    }

    private void initEvent() {
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mListener != null) {
                    mListener.contentChanged(s.toString());
                }
                if (TextUtils.isEmpty(s.toString())) {
                    mBtnDeleteKeyInfo.setVisibility(View.GONE);
                } else {
                    mBtnDeleteKeyInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        mBtnDeleteKeyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearch.setText("");
                mBtnDeleteKeyInfo.setVisibility(View.GONE);
            }
        });
    }

    private void initReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    return;
                }
                String action = intent.getAction();
                if (action == null) {
                    return;
                }
                switch (action) {
                    case BROADCAST:

                        break;
                    default:
                        break;
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST);
        registerReceiver(receiver, filter);
    }

    private void initService() {
        startService(new Intent(this, AppService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, AppService.class));
        unregisterReceiver(receiver);
    }
}
