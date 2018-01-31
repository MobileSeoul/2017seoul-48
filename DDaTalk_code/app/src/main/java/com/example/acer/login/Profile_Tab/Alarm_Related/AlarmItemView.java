package com.example.acer.login.Profile_Tab.Alarm_Related;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.login.R;


public class AlarmItemView extends LinearLayout {

    TextView textViewAlarmEmail,getTextViewAlarmContent;
    public AlarmItemView(Context context) {
        super(context);
        init(context);
    }

    public AlarmItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.alarm_item,this,true);

        textViewAlarmEmail = (TextView)findViewById(R.id.textViewAlarmEmail);
        getTextViewAlarmContent = (TextView)findViewById(R.id.textViewAlarmContent);

    }

    public void setAlarmEmail(String email){
        textViewAlarmEmail.setText(email);
    }
    public void setAlarmContent(String content){
        getTextViewAlarmContent.setText(content);
    }
}
