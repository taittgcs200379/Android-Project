package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String[] Length={"1000m", "2000m", "3000m"};
    private static final String[] Level={"beginner", "professional"};

    AutoCompleteTextView date;
    Button btDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView pLength= (AutoCompleteTextView)findViewById(R.id.length);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Length);
        dataAdapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
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
        else {
            displayAlert();
        }
    }
    public void displayAlert(){
        EditText mName = (EditText)findViewById(R.id.TripName);
        String name= mName.getText().toString();
        EditText mDestination = (EditText)findViewById(R.id.editTextText);
        String destination= mDestination.getText().toString();
        EditText eDate = (EditText)findViewById(R.id.date);
        String date= eDate.getText().toString();
        EditText eLength = (EditText)findViewById(R.id.length);
        String length= eLength.getText().toString();
        EditText eLevel = (EditText)findViewById(R.id.level);
        String levels= eLevel.getText().toString();
        RadioGroup eGroup = (RadioGroup) findViewById(R.id.radioGroup2);
        RadioButton eChoice= (RadioButton)findViewById(eGroup.getCheckedRadioButtonId());
        String choice= eChoice.getText().toString();
        EditText eDescription = (EditText)findViewById(R.id.editTextTextMultiLine);
        String description= eDescription.getText().toString();

        new AlertDialog.Builder(this)
                .setTitle("Details of order")
                .setMessage("Details enter:\n" +
                        name+"\n"+
                        destination+"\n"+
                        length+"\n"+
                        levels+"\n"+
                        choice+"\n"+
                        description+"\n"
                ).setNeutralButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();


    }


}