package com.example.local.note.paper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
            viewHolder.edit = convertView.findViewById(R.id.myEdit);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.week.setText(data.get(position).getWeek());
        viewHolder.day.setText(data.get(position).getDay());
        viewHolder.myText.setText(data.get(position).getMyText());
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder{
        TextView week;
        TextView day;
        TextView myText;
        ImageView edit;
    }
}