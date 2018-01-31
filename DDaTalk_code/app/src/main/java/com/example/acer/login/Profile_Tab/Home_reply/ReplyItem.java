package com.example.acer.login.Profile_Tab.Home_reply;



public class ReplyItem {

//    private int resId;
    private String email;
    private String content;
    private int reply_no;
    private int writing_no;
    private String reply_time;

    public ReplyItem(/*int resId,*/ int reply_no,String email, String content,int writing_no,String reply_time) {
//        this.resId = resId;
        this.reply_no = reply_no;
        this.email = email;
        this.content = content;
        this.writing_no = writing_no;
        this.reply_time = reply_time;
    }
    public ReplyItem(/*int resId,*/ int reply_no,String email, String content,int writing_no){
        this.reply_no = reply_no;
        this.email = email;
        this.content = content;
        this.writing_no = writing_no;
    }

    public ReplyItem(String email, String content){
        this.email = email;
        this.content = content;
    }

   /* public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }*/

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }

    public int getWriting_no() {
        return writing_no;
    }

    public void setWriting_no(int writing_no) {
        this.writing_no = writing_no;
    }

    public int getReply_no() {
        return reply_no;
    }

    public void setReply_no(int reply_no) {
        this.reply_no = reply_no;
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
}
