package com.example.acer.login.Profile_Tab.Home_reply;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.login.R;

public class ReplyItemView extends LinearLayout {

    ImageView imageView;
    TextView textViewEmail, textViewContent, textViewTime;
    public ReplyItemView(Context context) {
        super(context);
        init(context);
    }

    public ReplyItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.reply_item_nest,this,true);

        textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        textViewContent = (TextView)findViewById(R.id.textViewContent);
        textViewTime = (TextView)findViewById(R.id.textViewTime);
        imageView = (ImageView)findViewById(R.id.user_profile);


    }
    public void setName(String email){
        textViewEmail.setText(email);
    }

    public void setContent(String content){
        textViewContent.setText(content);
    }

    public void setImageView(int resId){
        imageView.setImageResource(resId);
    }

    public void setTime(String time){textViewTime.setText(time);}
}
