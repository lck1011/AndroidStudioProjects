package com.example.event;

import android.content.Context;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EventsMasterFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] titles = new String[EventsDAO.list.size()];
        for (int i = 0; i < titles.length; i++) {
            Events e = EventsDAO.list.get(i);
            titles[i]=e.getName();
        }
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,titles));
    }

    public interface onTitleSelectedListener {
        public void onEventsSelected(int position);
    }

    onTitleSelectedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (onTitleSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "必须实现OnTitleSelectedListener接口");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onEventsSelected(position);
    }
}
