package com.caxcot.imoocgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ExampleActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);

        gridView = (GridView) findViewById(R.id.gridView);

        List<String> strList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            strList.add("慕课网" + i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_gridview, strList);
        gridView.setAdapter(arrayAdapter);
    }
}
