package com.example.ayushi.listicle;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateTask extends AppCompatActivity {

    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        mydb = new DatabaseHelper(this);
        Button fab = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);

        if(getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            String taskValue = extras.getString("task");
            if (taskValue != null) {
                editText.setText(taskValue);
            }
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String task = editText.getText().toString();
            if(getIntent().getExtras() != null) {
                Bundle extras = getIntent().getExtras();
                String taskId = extras.getString("id");
                mydb.updateData(taskId ,task);
            }
            else {
                mydb.insertData(task);
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(setIntent);
    }
}
