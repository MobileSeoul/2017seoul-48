package com.example.acer.login.Profile_Tab.Home_Related;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.login.R;



public class TogetherItemView extends LinearLayout {

    TextView spot_View, textView,textView2,together_tv,comment_tv,wrting_date;
    ImageView imageView;


    public TogetherItemView(Context context) {
        super(context);
        init(context);
    }

    public TogetherItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.together_item,this,true);

        textView = (TextView)findViewById(R.id.textView);
        spot_View = (TextView)findViewById(R.id.spot_View);
        textView2 = (TextView)findViewById(R.id.textView2);
        imageView = (ImageView)findViewById(R.id.user_profile);
        together_tv = (TextView)findViewById(R.id.together_tv);
        comment_tv = (TextView)findViewById(R.id.comment_tv);
        wrting_date = (TextView)findViewById(R.id.writing_date);



    }

    public void setWrting_date(String date){wrting_date.setText(date);}

    public void setEmail(String email){
        textView.setText(email);
    }

    public void setRental_spot(String rental_spot) { spot_View.setText(rental_spot);}

    public void setContent(String content){
        textView2.setText(content);
    }

    public void setTogether_tv(int together){
        together_tv.setText(String.valueOf(together));
    }

    public void setComment_Tv(int comment){
        comment_tv.setText(String.valueOf(comment));
    }

    public void setImageView(Bitmap bm){imageView.setImageBitmap(bm);}


}
