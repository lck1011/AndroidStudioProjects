package com.example.listviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "ListViewSample";
    String[] DATA = {"北京市", "天津市", "上海", "乌鲁木齐", "重庆", "拉萨", "银川", "呼和浩特", "包头",
            "南宁", "合肥", "六安", "阜阳", "蚌埠", "淮南", "大庆"};
    int[] icons = {R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EfficientAdapter adapter = new EfficientAdapter(this, R.layout.listview_item, DATA, icons);

        ListView listview = findViewById(R.id.ListView01);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "选择:" + DATA[position]);
            }
        });
    }
}
