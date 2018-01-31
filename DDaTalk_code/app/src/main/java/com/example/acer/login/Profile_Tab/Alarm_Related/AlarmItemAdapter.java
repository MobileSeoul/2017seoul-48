package com.example.acer.login.Profile_Tab.Alarm_Related;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;


public class AlarmItemAdapter extends BaseAdapter {
    private ArrayList<AlarmItem> items = new ArrayList<>();

    public void addItem(AlarmItem item){
        items.add(item);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        AlarmItemView view;
        if(convertView == null){
            view = new AlarmItemView(context);
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.together_item, parent, false);
        }else{
            view = (AlarmItemView) convertView;
        }
        final AlarmItem item = items.get(position);

        view.setAlarmEmail(item.getTrigger_email()+"님이");
        view.setAlarmContent(item.getEmail()+"님의 글에 "+item.getContent()+"\n"+item.getDate());
        return view;
    }
}
