package com.example.acer.login.Profile_Tab.MyPage_Related;

import android.graphics.drawable.Drawable;

public class List_writing {
    private Drawable mWithIcon;
    private String mContent;
    private String mWith_cnt;
    private String mDate;

    public List_writing(Drawable withicon, String content, String with_cnt, String date) {
        mWithIcon = withicon;
        mContent = content;
        mWith_cnt = with_cnt;
        mDate = date;
    }

    public Drawable getwithicon(){
        return mWithIcon;
    }

    public String getcontent() {
        return mContent;
    }

    public String  getwith_cnt() {
        return mWith_cnt;
    }

    public String  getdate() {
        return mDate;
    }
}