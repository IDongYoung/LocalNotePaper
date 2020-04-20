package com.example.local.note.paper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MySqlite extends SQLiteOpenHelper {

    public MySqlite(Context context){
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table notepaper(id INTEGER primary key autoincrement," +
                "                  year INTEGER," +
                "                  month INTEGER," +
                "                  day INTEGER," +
                "                  week INTEGER," +
                "                  note text);";
        db.execSQL(sql);

        int year = 2020;
        int month = 4;
        int day = 18;
        for (int i=0;i<1000;i++){
            if (!dayIsFull(year,month,day)){
                day++;
            }else {
                if (month<12){
                    month++;
                }else{
                    month=1;
                    year++;
                }
                day=1;
            }
            sql = "insert into notepaper (year,month,day,week,note) values("+year+","+month+","+day+","+(((i+6)%7)+1)+",''); ";
            db.execSQL(sql);
        }
    }

    private boolean dayIsFull(int year,int month,int day){
        int monthNum = 0;
        switch (month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: monthNum=31;break;
            case 4: case 6: case 9: case 11: monthNum=30;break;
            default:monthNum=28;break;
        }
        if (month != 2){
            return day >= monthNum;
        }
        if (year%100==0&&year%400==0 || year%100!=0&&year%4==0){
            monthNum=29;
        }
        return day >= monthNum;
    }

    public List<ShowData> getShowDataByTime(int year, int month){
        return getShowDataByTime(year,month,0,year,month,31);
    }

    public List<ShowData> getShowDataByTime(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
        String sql = "select * from notepaper where year>= "+startYear+" and year<="+endYear
                +" and month>="+startMonth+" and month<="+endMonth
                +" and day>="+startDay+ " and day<="+endDay;
        return getShowDataSql(sql);
    }

    public List<ShowData> getShowDataBySearch(String key){
        String sql = "select * from notepaper where note like '%"+key+"%'; ";
        List<ShowData> showDataList = getShowDataSql(sql);
        for (ShowData showData : showDataList){
            showData.setHtmlMyText(showData.getHtmlMyText().replaceAll(key,"<font color=\"#ff0000\"><strong>"+key+"</strong></font>"));
        }
        return showDataList;
    }
    public ShowData getShowDataById(int id){
        String sql = "select * from notepaper where id = "+id+";";
        return getShowDataSql(sql).get(0);
    }

    public boolean updateDataById(int id,String note){
        String sql = "update notepaper set note = '"+note+"' where id = "+id+";";
        try {
            this.getWritableDatabase().execSQL(sql);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private List<ShowData> getShowDataSql(String sql){
        Cursor c = this.getWritableDatabase().rawQuery(sql,null);
        List<ShowData> result = new ArrayList<>();
        while (c.moveToNext()){
            result.add(new ShowData(c.getInt(0),getWeek(c.getInt(4)),getDay(c.getInt(1),c.getInt(2),c.getInt(3)),c.getString(5)));
        }
        c.close();
        return result;
    }

    private String getDay (int year,int month,int day){
        StringBuilder stringBuilder = new StringBuilder().append(year).append(".").append(month).append(".");
        if (day < 10){
            return stringBuilder.append("0").append(day).toString();
        }
        return stringBuilder.append(day).toString();
    }
    private String getWeek (int week){
        switch (week){
            case 1:return "周一";
            case 2:return "周二";
            case 3:return "周三";
            case 4:return "周四";
            case 5:return "周五";
            case 6:return "<font color=\"#ff00ff\">周六</font>";
            case 7:return "<font color=\"#ff00ff\">周日</font>";
            default:return "未知";
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
