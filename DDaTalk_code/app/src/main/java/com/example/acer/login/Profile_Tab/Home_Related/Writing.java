package com.example.acer.login.Profile_Tab.Home_Related;




public class Writing {
    private String content;
    private int reply_cnt;
    private int with_cnt;
    private String date;
    private int writing_no;
    private String email;
    private String rental_spot;

    public Writing(){}

    public Writing(String content, int reply_cnt, int with_cnt, String date, int writing_no, String email,String rental_spot) {
        this.content = content;
        this.reply_cnt = reply_cnt;
        this.with_cnt = with_cnt;
        this.date = date;
        this.writing_no = writing_no;
        this.email = email;
        this.rental_spot = rental_spot;
    }

    public String getRental_spot() {
        return rental_spot;
    }

    public void setRental_spot(String rental_spot) {
        this.rental_spot = rental_spot;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReply_cnt() {
        return reply_cnt;
    }

    public void setReply_cnt(int reply_cnt) {
        this.reply_cnt = reply_cnt;
    }

    public int getWith_cnt() {
        return with_cnt;
    }

    public void setWith_cnt(int with_cnt) {
        this.with_cnt = with_cnt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWriting_no() {
        return writing_no;
    }

    public void setWriting_no(int writing_no) {
        this.writing_no = writing_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
