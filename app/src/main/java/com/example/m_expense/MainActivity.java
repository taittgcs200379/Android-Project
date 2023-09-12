package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
                //Toast.makeText(getApplicationContext(), day+"/"+(month+1)+"/"+year, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void handleButtonClick(View v){
        EditText editText2= (EditText)findViewById(R.id.TripName);

        if(TextUtils.isEmpty(editText2.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose name of trip", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        EditText editText=(EditText)findViewById(R.id.editTextText);
        if(TextUtils.isEmpty(editText.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose destination", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        DatePicker dp = (DatePicker)findViewById(R.id.datePicker);
        if(!dp.isScrollContainer()){
            Toast t= Toast.makeText(this, "you must choose date", Toast.LENGTH_SHORT);
            t.show();
            return;
        }


        RadioGroup rg=(RadioGroup) findViewById(R.id.radioGroup2);
        if(rg.getCheckedRadioButtonId()==-1){
            Toast t= Toast.makeText(this, "you must choose your option", Toast.LENGTH_SHORT);
            t.show();
            return;
        }


    }

}