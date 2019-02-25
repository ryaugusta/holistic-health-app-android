package com.example.ryanaugusta.hoistichealthapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

//import android.icu.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    // create a static reference to calendar object
    // it is not created and destroyed each time the DatePickerFragment is created.
    private static Calendar calendar;

    // declare static variables for month, day and year
    // used to initialize the datePicker
    private static int year;
    private static int month;
    private static int day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the current date as the default date in the picker
        // if calendar has no value
        if(calendar == null)
        {
            // get current date
            calendar = Calendar.getInstance();

            // assign current month, day and year to static variables
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        // copy the user supplied month, day and year to the static instance variables
        this.year = year;
        this.day = dayOfMonth;
        this.month = month;

        // update the calendar object with the user supplied date
        calendar.set(year, month, dayOfMonth);

        // get the MainActivity and call the public setDate method
        MainActivity ma = (MainActivity)getActivity();
        MyStackFragment myStackFragment = (MyStackFragment)getTargetFragment();
        myStackFragment.setDate(calendar);
    }
}
