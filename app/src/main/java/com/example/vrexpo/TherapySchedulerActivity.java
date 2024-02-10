package com.example.vrexpo;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TherapySchedulerActivity extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;

    TextView timer;
    int v1hour, v2minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy_scheduler);
        calendarView = findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();

        setDate(1, 1, 2024);

        getDate();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(TherapySchedulerActivity.this, dayOfMonth + "/" + month + 1 + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });

        timer = findViewById(R.id.timer);

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        TherapySchedulerActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                v1hour = hourOfDay;
                                v2minute = minute;
                                String time = v1hour + ":" + v2minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");

                                try {
                                    Date date = f24Hours.parse(time);

                                    //Initialize 12 hour time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");

                                    //Set selected time on text view
                                    timer.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, 12, 0, false);

                //Set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Display previously selected time
                timePickerDialog.updateTime(v1hour, v2minute);
                //Show dialog
                timePickerDialog.show();
            }
        });
    }

    public void getDate() {
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
    }
    public void setDate(int dayOfMonth, int month, int year) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        long milli = calendar.getTimeInMillis(); // setting the time
        calendarView.setDate(milli);
    }

}
