package com.example.local.note.paper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    private MySqlite mysql = new MySqlite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final int id = getIntent().getIntExtra("id",-1);
        ShowData showData = mysql.getShowDataById(id);
        final EditText editText = findViewById(R.id.noteText);
        editText.setText(showData.getMyText());

        findViewById(R.id.editSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = mysql.updateDataById(id,editText.getText().toString());
                if (success){
                    finish();
                }else {
                    Toast.makeText(EditActivity.this,"保存失败",Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.editCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onPause(){
        super.onPause();
    }



}