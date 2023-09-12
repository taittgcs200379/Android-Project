package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String[] trips={"Conference", "Client meeting","Picnic"};

    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner sp=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, trips);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);

        datePicker=(DatePicker) findViewById(R.id.datePicker);
        Calendar c= Calendar.getInstance();
        int y =c.get(Calendar.YEAR);
        int m =c.get(Calendar.MONTH);
        int d =c.get(Calendar.DAY_OF_MONTH);
        datePicker.init(y, m, d, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int y, int m, int d) {
                int day =datePicker.getDayOfMonth();
                int month= datePicker.getMonth();
                int year = datePicker.getYear();
                Toast.makeText(getApplicationContext(), day+"/"+(month+1)+"/"+year, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void handleButtonClick(View v){
        Spinner spinner =(Spinner)findViewById(R.id.spinner);

        DatePicker dp = (DatePicker)findViewById(R.id.datePicker);

        EditText editText=(EditText)findViewById(R.id.editTextText);
        RadioGroup rg=(RadioGroup) findViewById(R.id.radioGroup2);
        if(!spinner.performClick()){
            Toast t= Toast.makeText(this, "you must choose name of trip", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        if(!dp.isScrollContainer()){
            Toast t= Toast.makeText(this, "you must choose date", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        if(editText.getFreezesText()){
            Toast t= Toast.makeText(this, "you must fill location", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        if(!rg.callOnClick()){
            Toast t= Toast.makeText(this, "you must choose your option", Toast.LENGTH_SHORT);
            t.show();
            return;
        }


    }

}