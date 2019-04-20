package com.example.mymap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

public class MainActivity extends AppCompatActivity {
    final private int MENU_TRAFFIC = Menu.FIRST;
    final private int MENU_NORMAL = Menu.FIRST + 1;
    final private int MENU_SATELLITE = Menu.FIRST + 2;

    MapView mapView = null;
    BaiduMap baiduMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MENU_TRAFFIC,0,R.string.traffic);
        menu.add(0,MENU_NORMAL,0,R.string.normal);
        menu.add(0,MENU_SATELLITE,0,R.string.satellite);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_TRAFFIC:
                if(baiduMap.isTrafficEnabled()){
                    baiduMap.setTrafficEnabled(false);
                }else{
                    baiduMap.setTrafficEnabled(true);
                }
                break;
            case MENU_NORMAL:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case MENU_SATELLITE:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
