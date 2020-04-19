package com.example.local.note.paper;

public class ShowData {
    private int id;
    private String week;
    private String day;
    private String myText;
    public ShowData(int id,String week,String day,String myText){
        this.id = id;
        this.week = week;
        this.day = day;
        this.myText = myText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMyText() {
        return myText;
    }

    public void setMyText(String myText) {
        this.myText = myText;
    }
}
