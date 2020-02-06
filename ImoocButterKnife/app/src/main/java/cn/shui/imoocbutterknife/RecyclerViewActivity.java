package cn.shui.imoocbutterknife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<String> mData = new ArrayList<String>(Arrays.asList("Simple Use", "RecyclerView Use"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        ButterKnife.bind(this);

        mRecyclerView.setAdapter(new RvAdapter(this, mData));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
