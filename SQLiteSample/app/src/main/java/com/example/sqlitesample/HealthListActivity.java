package com.example.sqlitesample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
    private DBHelper mDBHelper;

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
        menu.add(0, ADD_MENU_ID, 1, R.string.add).setIcon(android.R.drawable.ic_menu_add);
        menu.add(0, CONF_MENU_ID, 2, R.string.conf).setIcon(android.R.drawable.ic_menu_compass);
        return super.onCreateOptionsMenu(menu);
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

//        SQLiteDatabase db = mDBHelper.getReadableDatabase();
//        String[] colums = new String[]{SysConst.TABLE_FIELD_DATE,
//        SysConst.TABLE_FIELD_INPUT,SysConst.TABLE_FIELD_OUTPUT,
//        SysConst.TABLE_FIELD_WEIGHT,
//        SysConst.TABLE_FIELD_AMOUNTEXERCISE};
//
//        Cursor mCursor = db.query(SysConst.TABLE_NAME, colums, null, null, null, null,
//                SysConst.TABLE_FIELD_DATE + "asc");
//        while(mCursor.moveToNext()){
//            Log.d(TAG,mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_INPUT)));
//            Log.d(TAG,mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_OUTPUT)));
//        }
//        startManagingCursor(mCursor);
//        SimpleCursorAdapter mCursorAdapter = new SimpleCursorAdapter(this,R.layout.listitem,mCursor,
//                colums,newint[]{R.id.date,R.id.input,R.id.output,R.id.weight,R.id.amountExercise});
//        mListView.setAdapter(mCursorAdapter);
    }
}
