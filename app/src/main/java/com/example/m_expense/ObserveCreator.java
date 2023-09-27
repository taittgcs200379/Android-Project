package com.example.m_expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Locale;

public class ObserveCreator extends AppCompatActivity {

    private static final String[] Observation={"Sightings of animals", "Types of vegetation", "Weather conditions","Conditions of the trails"};
    AutoCompleteTextView ObservationDate, ObservationTime;
    Button dateButton,timeButton;
    int hour, minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observe_creator);

        dateButton=findViewById(R.id.observeDate);
        timeButton=findViewById(R.id.time_button);
        ObservationDate=findViewById(R.id.observation_date);
        ObservationTime=findViewById(R.id.time);

        AutoCompleteTextView pObservation= (AutoCompleteTextView)findViewById(R.id.observation_creator);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Observation);
        dataAdapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        pObservation.setAdapter(dataAdapter);
        pObservation.setThreshold(256);
        findViewById(R.id.observation_creator).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pObservation.showDropDown();
            }
        });
        dateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        timeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem list = menu.findItem(R.id.AccessList);
        MenuItem creator =menu.findItem(R.id.HikingCreator);
        list.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent t = new Intent(getApplicationContext(),TripsDetails.class);
                startActivity(t);

                return false;
            }
        });
        creator.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent c = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(c);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
    private void showDatePickerDialog(){
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                ObservationDate.setText("" + materialDatePicker.getHeaderText());

            }
        });
        materialDatePicker.show(getSupportFragmentManager(), "TAG");
    }

    private void showTimePickerDialog(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener= new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectHour, int selectMinute) {
                hour = selectHour;
                minute=selectMinute;
                ObservationTime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));

            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, onTimeSetListener,hour,minute,true);

        timePickerDialog.show();
    }


}



