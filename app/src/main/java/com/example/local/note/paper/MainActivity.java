package com.example.local.note.paper;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MySqlite mysql = new MySqlite(this);
    private List<ShowData> data;
    private MyAdapter myAdapter;
    private int year=2020;
    private int month=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = findViewById(R.id.all);
        getNowTime();
        data = mysql.getShowDataByTime(year,month);
        myAdapter = new MyAdapter(this,data);
        listView.setAdapter(myAdapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });

        EditText editText = findViewById(R.id.editSearchKey);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String show = s.toString();
                if ("".equals(show)){
                    data = mysql.getShowDataByTime(year,month);
                    myAdapter.setData(data);
                    myAdapter.notifyDataSetChanged();
                }else {
                    data = mysql.getShowDataBySearch(show);
                    myAdapter.setData(data);
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onRestart(){
        super.onRestart();
        data = mysql.getShowDataByTime(year,month);
        myAdapter.setData(data);
        myAdapter.notifyDataSetChanged();
    }

    public void select(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("选择时间")
                .setIcon(android.R.drawable.ic_menu_recent_history)
                .setView(R.layout.activity_time_select).create();
        alertDialog.show();

        alertDialog.findViewById(R.id.selectSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner yearSpinner = alertDialog.findViewById(R.id.yearSelect);
                Spinner monthSpinner = alertDialog.findViewById(R.id.monthSelect);
                if (yearSpinner == null || monthSpinner == null){
                    Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
                    alertDialog.dismiss();
                    return;
                }
                String yearStr = yearSpinner.getSelectedItem().toString();
                long monthId = monthSpinner.getSelectedItemId();
                year = Integer.parseInt(yearStr);
                month = (int) monthId+1;
                data = mysql.getShowDataByTime(year,month);
                myAdapter.setData(data);
                myAdapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });

        alertDialog.findViewById(R.id.selectCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    private void getNowTime(){
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date(System.currentTimeMillis()));
        year = ca.get(Calendar.YEAR);
        month = ca.get(Calendar.MONTH)+1;
    }
}