package com.example.sqlitesample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HealthListActivity extends AppCompatActivity {
    private static final String TAG = "HealthListActivity";

    public static final int ADD_MENU_ID = Menu.FIRST;
    public static final int CONF_MENU_ID = Menu.FIRST + 1;

    private ListView mListView;
    private DateFormat mDateFormat;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSharedPreferences = getSharedPreferences(SysConst.PREFS_CONF, MODE_PRIVATE);
        String dateConf = mSharedPreferences.getString(SysConst.DATE_KEY, "YYYY-MM-DD");
        if ("YYYY-MM-DD".equals(dateConf)) {
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            mDateFormat = new SimpleDateFormat(("yyyy/MM/dd"));
        }
        findAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, ADD_MENU_ID, 1, R.string.add).setIcon(android.R.drawable.ic_menu_add);
        menu.add(0, CONF_MENU_ID, 2, R.string.conf).setIcon(android.R.drawable.ic_menu_compass);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ADD_MENU_ID:
                return true;
            case CONF_MENU_ID:
                Intent itconf = new Intent(this, HealthConfigActivity.class);
                startActivity(itconf);
        }
        return super.onOptionsItemSelected(item);
    }

    private void findAll() {
        Date now = new Date();
        Date date = new Date(now.getTime() + 923456785);
        String[] sdate = {mDateFormat.format(now), mDateFormat.format(date)};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sdate);
        mListView.setAdapter(adapter);
    }
}
