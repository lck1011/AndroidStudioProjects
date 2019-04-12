package com.example.examplecontacts;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemLongClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "ExampleContacts";// 调试标签

    //声明游标适配器
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建SimpleCursorAdapter游标适配器对象
        simpleCursorAdapter = new SimpleCursorAdapter(
                this, R.layout.listitem,
                null,//使用CursorLoader不需要游标对象了
                new String[]{ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{R.id.textview_no, R.id.textview_name},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //声明ListView
        ListView mListView = findViewById(R.id.listview);
        mListView.setAdapter(simpleCursorAdapter);
        mListView.setOnItemLongClickListener(this);

        //从活动中获得LoaderManager对象
        LoaderManager loaderManager = getLoaderManager();
        //LoaderManager初始化
        loaderManager.initLoader(0, null, this);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);

        String[] columns = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME};

        Cursor cursor = getContentResolver().query(uri, columns, null, null, null);

        if (cursor.moveToFirst()) {
            final String contactId = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            final String contactName = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.i(TAG, contactId + " | " + contactName);

            new AlertDialog.Builder(this).setTitle("选择操作")
                    .setItems(R.array.select_dialog_items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG, "联系人 : " + contactName);
                            //选择查看“电话号码”
                            if (which == 0) {
                                findPhones(contactId);
                            }
                            //选择查看“Email”
                            if (which == 1) {
                                findEmail(contactId);
                            }
                        }
                    }).show();

        }
        cursor.close();

        return false;
    }


    //创建CursorLoader时调用
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //创建CursorLoader对象
        return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    }

    //加载数据完成时调用
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        //采用新的游标与老游标交换，老游标不关闭
        simpleCursorAdapter.swapCursor(c);
    }

    //CursorLoader对象被重置时调用
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //采用新的游标与老游标交换，老游标不关闭
        simpleCursorAdapter.swapCursor(null);
    }

    /**
     * 选择查看【电话号码】
     *
     * @param contactId 联系人id
     */
    private void findPhones(String contactId) {

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                        + " = "
                        + contactId,
                null, null);

        while (cursor.moveToNext()) {
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.i(TAG, "电话号码 : " + phoneNumber);
        }
        cursor.close();
    }

    /**
     * 选择查看“Email”
     *
     * @param contactId 联系人ID
     */
    private void findEmail(String contactId) {

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID
                        + " = "
                        + contactId,
                null, null);

        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            Log.i(TAG, "Email : " + email);
        }
        cursor.close();
    }
}