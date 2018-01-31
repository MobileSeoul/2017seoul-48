package com.example.acer.login.Profile_Tab.Alarm_Related;


public class AlarmItem {
    private int alarm_no;
    private String content;
    private String date;
    private String trigger_email;
    private int writing_no;
    private String email;

    public AlarmItem(int alarm_no, String content, String date, String trigger_email, int writing_no, String email) {
        this.alarm_no = alarm_no;
        this.content = content;
        this.date = date;
        this.trigger_email = trigger_email;
        this.writing_no = writing_no;
        this.email = email;
    }

    public int getAlarm_no() {
        return alarm_no;
    }

    public void setAlarm_no(int alarm_no) {
        this.alarm_no = alarm_no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrigger_email() {
        return trigger_email;
    }

    public void setTrigger_email(String trigger_email) {
        this.trigger_email = trigger_email;
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
