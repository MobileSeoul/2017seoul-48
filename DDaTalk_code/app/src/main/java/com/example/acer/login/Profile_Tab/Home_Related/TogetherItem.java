package com.example.acer.login.Profile_Tab.Home_Related;


import android.graphics.Bitmap;

public class TogetherItem {
    private String email;
    private String content;
    private String date;
    private String imgPath;
    private int together, comment;
    private int writing_no;
    private String rental_spot;
    private Bitmap bm;


    public TogetherItem(int writing_no,String email, String content,String date, Bitmap bm, int together, int comment,String rental_spot) {
        this.writing_no = writing_no;
        this.email = email;
        this.content = content;
        this.date = date;
        this.bm = bm;
        this.together = together;
        this.comment = comment;
        this.rental_spot = rental_spot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRental_spot() {
        return rental_spot;
    }

    public void setRental_spot(String rental_spot) {
        this.rental_spot = rental_spot;
    }

    public void setWriting_no(int writing_no){
        this.writing_no = writing_no;
    }
    public int getWriting_no(){
        return this.writing_no;
    }

    public int getTogether() {
        return together;
    }

    public void setTogether(int together) {
        this.together = together;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getimgPath() {
        return imgPath;
    }

    public void setimgPath(String imgPath) {this.imgPath = imgPath;}

    public Bitmap getBM(){return this.bm;}

}
