package com.example.sqlitesample;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;

public class HealthListActivity extends AppCompatActivity {
    private static final String TAG = "HealthListActivity";

    public static final int ADD_MENU_ID = Menu.FIRST;
    public static final int CONF_MENU_ID = Menu.FIRST + 1;

    private ListView mListView;
    private Cursor mCursor;
    private SimpleCursorAdapter mCursorAdapter;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DBHelper(this);

        mListView = (ListView) findViewById(R.id.listview);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View v, int pos, long id) {
                showListDialog(pos);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                Intent itadd = new Intent(this, HealthAddActivity.class);
                startActivity(itadd);
                return true;
            case CONF_MENU_ID:
                //todo
        }
        return super.onOptionsItemSelected(item);
    }

    private void findAll() {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] colums = new String[]{SysConst.TABLE_FIELD_DATE,
                SysConst.TABLE_FIELD_INPUT, SysConst.TABLE_FIELD_OUTPUT,
                SysConst.TABLE_FIELD_WEIGHT,
                SysConst.TABLE_FIELD_AMOUNTEXERCISE};

        mCursor = db.query(SysConst.TABLE_NAME, colums, null, null, null, null,
                SysConst.TABLE_FIELD_DATE + " asc");

        while (mCursor.moveToNext()) {
            Log.d(TAG, mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_INPUT)));
            Log.d(TAG, mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_OUTPUT)));
        }

        startManagingCursor(mCursor);
        mCursorAdapter = new SimpleCursorAdapter(this, R.layout.listitem, mCursor,
                colums, new int[]{R.id.date, R.id.input, R.id.output,
                R.id.weight, R.id.amountExercise});

        mListView.setAdapter(mCursorAdapter);
    }

    void showListDialog(int pos) {

        mCursor.moveToPosition(pos);

        final String selectId = mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_DATE));

        new AlertDialog.Builder(HealthListActivity.this)
                .setTitle(selectId).setItems(
                R.array.select_dialog_items,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            DialogInterface dialog,
                            int which) {

                        if (which == 0) {// 修改
                            Intent it = new Intent(
                                    HealthListActivity.this,
                                    HealthModActivity.class);

                            it.putExtra(SysConst.TABLE_FIELD_DATE, selectId);

                            it.putExtra(SysConst.TABLE_FIELD_INPUT,
                                    mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_INPUT)));

                            it.putExtra(SysConst.TABLE_FIELD_OUTPUT,
                                    mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_OUTPUT)));

                            it.putExtra(SysConst.TABLE_FIELD_WEIGHT,
                                    mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_WEIGHT)));

                            it.putExtra(SysConst.TABLE_FIELD_AMOUNTEXERCISE,
                                    mCursor.getString(mCursor.getColumnIndex(SysConst.TABLE_FIELD_AMOUNTEXERCISE)));

                            startActivity(it);

                        } else if (which == 1) {// 删除

                            SQLiteDatabase db = mDBHelper
                                    .getWritableDatabase();
                            String whereClause = SysConst.TABLE_FIELD_DATE + " = ?";

                            long rowId = db
                                    .delete(SysConst.TABLE_NAME, whereClause, new String[]{selectId});
                            // 重新绑定查询
                            findAll();
                        }
                    }
                }).show();

    }
}
