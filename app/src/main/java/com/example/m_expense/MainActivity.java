package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String[] Length={"1000", "2000", "3000"};
    private static final String[] Level={"beginner", "professional"};

    AutoCompleteTextView date;
    Button btDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView pLength= (AutoCompleteTextView)findViewById(R.id.length);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Length);
        //dataAdapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        pLength.setAdapter(dataAdapter);
        pLength.setThreshold(256);

        findViewById(R.id.length).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                pLength.showDropDown();
            }
        });


        AutoCompleteTextView pLevel= (AutoCompleteTextView)findViewById(R.id.level);
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Level);
        //dataAdapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        pLevel.setAdapter(levelAdapter);
        pLevel.setThreshold(256);

        findViewById(R.id.level).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                pLevel.showDropDown();
            }
        });


        date=findViewById(R.id.date);
        btDate=findViewById(R.id.date_button);

        btDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

    }

    private void showDatePickerDialog() {
        MaterialDatePicker materialDatePicker=MaterialDatePicker.Builder.datePicker().build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                date.setText(""+materialDatePicker.getHeaderText());

            }
        });
        materialDatePicker.show(getSupportFragmentManager(),"TAG");

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

        AutoCompleteTextView d = (AutoCompleteTextView) findViewById(R.id.date);
        if(TextUtils.isEmpty(d.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose date", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        AutoCompleteTextView l =(AutoCompleteTextView)findViewById((R.id.length));
        if(TextUtils.isEmpty(l.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose length", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        AutoCompleteTextView level =(AutoCompleteTextView)findViewById((R.id.level));
        if(TextUtils.isEmpty(level.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose level", Toast.LENGTH_SHORT);
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