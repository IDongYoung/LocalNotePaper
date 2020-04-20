package com.example.local.note.paper;

public class ShowData {
    private int id;
    private int year;
    private int month;
    private int day;
    private String week;
    private String calendar;
    private String myText;
    private String htmlMyText;
    public ShowData(int id,String week,String calendar,String myText){
        this.id = id;
        this.week = week;
        this.calendar = calendar;
        this.myText = myText;
        this.htmlMyText = myText.replaceAll("(\r\n|\n)","<br/>");
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getMyText() {
        return myText;
    }

    public void setMyText(String myText) {
        this.myText = myText;
    }

    public String getHtmlMyText() {
        return htmlMyText;
    }

    public void setHtmlMyText(String htmlMyText) {
        this.htmlMyText = htmlMyText;
    }
}