package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText date;

        date = (EditText)findViewById(R.id.date);
        date.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);
                        if(mon<1) mon=1;
                        cal.set(Calendar.MONTH, mon+1);
                        if(day>31) day=31;
                        cal.set(Calendar.DAY_OF_MONTH, day-1);
                        if(day<1) day=1;
                        cal.set(Calendar.DAY_OF_MONTH, day-1);
                        if

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    date.setText(current);
                    date.setSelection(sel < current.length() ? sel : current.length());

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
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

        EditText d = (EditText) findViewById(R.id.date);
        if(TextUtils.isDigitsOnly(d.getText().toString())){
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