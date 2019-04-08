package com.example.datastorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "FileSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRead = findViewById(R.id.button_read);
        btnRead.setOnClickListener(this);
        Button btnWrite = findViewById(R.id.button_write);
        btnWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_write:
                create();
                break;
            case R.id.button_read:
                List<Map<String, String>> list = findAll();
                for (Map<String, String> rows : list) {
                    Log.i(TAG, "================================");
                    Log.i(TAG, "日期：" + rows.get(SysConst.TABLE_FIELD_DATE));
                    Log.i(TAG, "摄入能量：" + rows.get(SysConst.TABLE_FIELD_INPUT));
                    Log.i(TAG, "消耗能量：" + rows.get(SysConst.TABLE_FIELD_OUTPUT));
                    Log.i(TAG, "体重：" + rows.get(SysConst.TABLE_FIELD_WEIGHT));
                    Log.i(TAG, "运动情况：" + rows.get(SysConst.TABLE_FIELD_AMOUNTEXERCISE));
                }
        }
    }

    private List<Map<String, String>> findAll() {
        FileInputStream in = null;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            in = this.openFileInput(SysConst.DATABASE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                Map<String, String> rows = new HashMap<String, String>();
                rows.put(SysConst.TABLE_FIELD_DATE, fields[0]);
                rows.put(SysConst.TABLE_FIELD_INPUT, fields[1]);
                rows.put(SysConst.TABLE_FIELD_OUTPUT, fields[2]);
                rows.put(SysConst.TABLE_FIELD_WEIGHT, fields[3]);
                rows.put(SysConst.TABLE_FIELD_AMOUNTEXERCISE, fields[4]);
                list.add(rows);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public void create() {
        FileOutputStream outputStream = null;
        try {
            StringBuffer rows = new StringBuffer();
            rows.append("1289645040579,1500大卡,3000大卡,90kg,5公里");
            rows.append("\n");
            rows.append("1289732522328,2500大卡,4000大卡,95kg,5公里");
            rows.append("\n");
            outputStream = this.openFileOutput(SysConst.DATABASE_NAME, MODE_PRIVATE);
            outputStream.write(rows.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
