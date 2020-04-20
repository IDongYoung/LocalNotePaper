package com.example.local.note.paper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<ShowData> data;
    private Context context;
    public MyAdapter(Context context, List<ShowData> data){
        this.context = context;
        this.data = data;
    }
    public void setData(List<ShowData> data){
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.content_main,null);
            viewHolder.week = convertView.findViewById(R.id.week);
            viewHolder.day = convertView.findViewById(R.id.day);
            viewHolder.myText = convertView.findViewById(R.id.myText);
            convertView.setTag(viewHolder);
        }else {
            convertView.setBackgroundColor(Color.WHITE);
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.week.setText(Html.fromHtml(data.get(position).getWeek()));
        viewHolder.day.setText(data.get(position).getCalendar());
        viewHolder.myText.setText(Html.fromHtml(data.get(position).getHtmlMyText()));
        viewHolder.myText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
                return true;
            }
        });
        if (isToday(data.get(position).getYear(),data.get(position).getMonth(),data.get(position).getDay())){
            convertView.setBackgroundColor(0xFFFAF0E6);
        }
        return convertView;
    }

    private boolean isToday(int year , int month , int day){
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date(System.currentTimeMillis()));
        if (ca.get(Calendar.YEAR) != year){
            return false;
        }
        if (ca.get(Calendar.MONTH)+1 != month){
            return false;
        }
        return ca.get(Calendar.DATE) == day;
    }

    static class ViewHolder{
        TextView week;
        TextView day;
        TextView myText;
    }
}