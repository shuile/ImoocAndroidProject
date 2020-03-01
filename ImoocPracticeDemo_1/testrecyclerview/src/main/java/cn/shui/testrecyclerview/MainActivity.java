package cn.shui.testrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        // 线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 网格布局

        // 横向排列ItemView
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 数据反向展示
//        layoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyRecyclerViewAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // itemView点击事件监听
        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "第" + position + "条数据被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 添加数据
     * @param view
     */
    public void onAddDataClick(View view) {
        List<String> data = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String s = "第" + i + "条数据";
            data.add(s);
        }

        mAdapter.setDataSource(data);
    }

    /**
     * 切换布局
     * @param view
     */
    public void onChangeLayoutClick(View view) {
        // 从线性布局切换为网格布局
        if (mRecyclerView.getLayoutManager().getClass() == LinearLayoutManager.class) {
            // 网格布局
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else if (mRecyclerView.getLayoutManager().getClass() == GridLayoutManager.class) {
            // 瀑布流布局
            StaggeredGridLayoutManager staggeredGridLayoutManager
                    = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        } else {
            // 线性布局
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
        }
    }

    /**
     * 插入一条数据
     * @param view
     */
    public void onInsertDataClick(View view) {
        mAdapter.addData(1);
    }

    /**
     * 删除一条数据
     * @param view
     */
    public void onRemoveDataClick(View view) {
        mAdapter.removeData(1);
    }
}
