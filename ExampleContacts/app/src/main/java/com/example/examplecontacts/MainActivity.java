package com.example.examplecontacts;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Loader;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "ExampleContacts";
    private ListView mListView;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.listitem,
                null,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{R.id.textview_no, R.id.textview_name},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView = findViewById(R.id.listview);
        mListView.setAdapter(simpleCursorAdapter);
        mListView.setOnItemLongClickListener(this);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        String[] colums = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(uri, colums, null, null, null);
        if (cursor.moveToFirst()) {
            final String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            final String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.i(TAG, contactId + " | " + contactName);

            new AlertDialog.Builder(this).setTitle("选择操作")
                    .setItems(R.array.select_dialog_items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG, "联系人:" + contactName);
                            if (which == 0) {
                                findPhones(contactId);
                            }
                            if (which == 1) {
                                findEmail(contactId);
                            }
                        }
                    }).show();
        }
        cursor.close();
        return false;
    }

    private void findPhones(String contactId) {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                null, null);
        while (cursor.moveToNext()) {
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.i(TAG, "Phone:" + phoneNumber);
        }
        cursor.close();
    }

    private void findEmail(String contactId) {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                null, null);
        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            Log.i(TAG, "Email:" + email);
        }
        cursor.close();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        simpleCursorAdapter.swapCursor(null);
    }
}
