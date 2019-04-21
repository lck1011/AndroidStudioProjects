package com.example.mylocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final String TAG = "MyLocation";

    private TextView mLatitude, mLongitude, mAltitude;

    //定位服务管理类
    private LocationManager mLocationManager;
    //授权请求编码
    private static final int PERMISSION_REQUEST_CODE = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLatitude = (TextView) findViewById(R.id.latitude);
        mLongitude = (TextView) findViewById(R.id.longitude);
        mAltitude = (TextView) findViewById(R.id.altitude);

        //判断是否授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //请求授权
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        } else {
            //已经授权
            startLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授权成功
                    startLocation();
                }
            }
        }
    }

    private void startLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //LocationManager.GPS_PROVIDER
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        }
        mLocationManager.requestLocationUpdates(getBestProvider(),
                1000, 0, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationManager != null)
            mLocationManager.removeUpdates(this);
        mLocationManager = null;
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double altitude = location.getAltitude();

        String msg = String.format("经度：%f, 纬度：%f, 海拔高度：%f", longitude, latitude, altitude);
        Log.i(TAG, msg);

        mLatitude.setText(String.valueOf(latitude));
        mLongitude.setText(String.valueOf(longitude));
        mAltitude.setText(String.valueOf(altitude));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i(TAG, "onStatusChanged...");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i(TAG, "onProviderEnabled...");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i(TAG, "onProviderDisabled...");
    }

    // 获得符合条件的位置信息提供者
    private String getBestProvider() {
        Criteria criteria = new Criteria(); // 位置信息提供者条件
        criteria.setAccuracy(Criteria.ACCURACY_FINE);   //高精度
        criteria.setCostAllowed(true);                  //允许产生资费
        criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

        //String bestProvider = LocationManager.GPS_PROVIDER;
        String bestProvider = mLocationManager.getBestProvider(criteria, true);

        return bestProvider;
    }
}