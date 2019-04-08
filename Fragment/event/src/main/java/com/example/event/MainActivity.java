package com.example.event;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements EventsMasterFragment.onTitleSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            EventsMasterFragment firstFragment = new EventsMasterFragment();
            firstFragment.setArguments(getIntent().getExtras());
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, firstFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onEventsSelected(int position) {
        EventsDetailFragment detailFragment = (EventsDetailFragment) getFragmentManager().findFragmentById(R.id.fragment_detail);

        if (detailFragment != null) {
            detailFragment.updateDetailView(position);
        } else {
            EventsDetailFragment newFragment = new EventsDetailFragment();
            Bundle args = new Bundle();
            args.putInt(EventsDetailFragment.EVENTS_POSITION, position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
