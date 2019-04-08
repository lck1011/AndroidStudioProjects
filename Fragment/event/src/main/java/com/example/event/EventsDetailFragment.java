package com.example.event;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class EventsDetailFragment extends Fragment {
    //选中列表项位置
    int mCurrentPosition = -1;
    //保存选中列表项位置到Bundle，所需要的键
    final static String EVENTS_POSITION = "position";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            //恢复之前保存的列表项位置
            mCurrentPosition = savedInstanceState.getInt(EVENTS_POSITION);
        }
        //从布局文件中创建视图
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        //获取碎片中的参数
        Bundle args = getArguments();
        if (args != null) {
            //通过选中列表项位置，设置详细视图
            updateDetailView(args.getInt(EVENTS_POSITION));
        } else if (mCurrentPosition != -1) {
            //在onCreate调用期间，设置详细视图
            updateDetailView(mCurrentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EVENTS_POSITION, mCurrentPosition);
    }

    //设置详细视图
    public void updateDetailView(int position) {
        //获取选中的比赛项目
        Events events = EventsDAO.list.get(position);
        //获取当前碎片所在的活动
        Activity activity = this.getActivity();

        //获取碎片中的imageView对象
        ImageView imageView = activity.findViewById(R.id.imageView_detail);
        imageView.setImageResource(getLogoResId(events.getLogo()));
        //获取碎片中的TextView对象
        TextView textView = activity.findViewById(R.id.textView_detail);
        textView.setText(events.getDescription());
    }

    private int getLogoResId(String logo) {
        String packageName = this.getActivity().getPackageName();
        int pos = logo.indexOf(".");
        String logoFile = logo.substring(0, pos);

        int resId = this.getActivity().getResources().getIdentifier(logoFile, "drawable", packageName);
        return resId;
    }
}
