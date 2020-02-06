package cn.shui.imoocbutterknife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView mListBiew;

    private List<String> mData = new ArrayList<String>(Arrays.asList("Simple Use", "RecyclerView Use"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);

        mListBiew.setAdapter(new CategoryAdapter(this, mData));
    }

    @OnItemClick(R.id.listview)
    public void itemClicked(int position) {
        Toast.makeText(this, "position: " + position, Toast.LENGTH_SHORT).show();
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, MainActivity.class);
                break;
            case 1:
                intent = new Intent(this, RecyclerViewActivity.class);
                break;
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
