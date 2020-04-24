package com.example.karkas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDataBase openHelper = new MyDataBase(this);
    }

    //Обработка нажатий
    public void onClick(View v){
        EditText editTextName = findViewById(R.id.editTextNAME);
        EditText editTextNumber = findViewById(R.id.editTextNUMBER);
        EditText editTextRout = findViewById(R.id.editTextROUT);
        EditText editTextLoc = findViewById(R.id.editTextLOC);
        EditText editTextPlace = findViewById(R.id.editTextPLACE);
        EditText editTextTime = findViewById(R.id.editTextTIME);
        EditText editTextPhone = findViewById(R.id.editTextPHONE);

        String name = editTextName.getText().toString();
        int number = Integer.parseInt(editTextNumber.getText().toString());
        String rout = editTextRout.getText().toString();
        int loc = Integer.parseInt(editTextLoc.getText().toString());
        String place = editTextPlace.getText().toString();
        String time = editTextTime.getText().toString();
        int phone = Integer.parseInt(editTextPhone.getText().toString());

        MyDataBase openHelper = new MyDataBase(this);

        openHelper.insert(name, number, rout, loc, place, time, phone);

        Log.d("myTag", "excellent");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }
}
