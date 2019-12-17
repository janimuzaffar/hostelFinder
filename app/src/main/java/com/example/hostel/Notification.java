package com.example.hostel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Notification extends Fragment  {
//    EditText event,timetx,datetx;
//    Button Date,time,submit;
//    Calendar c;
//    DatePickerDialog dp;
//
//    SharedPreferences preferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View myview = inflater.inflate(R.layout.notification_frag,container,false);
//        Date=myview.findViewById(R.id.button5);
//        time=myview.findViewById(R.id.button4);
//        submit=myview.findViewById(R.id.button3);
//        event=myview.findViewById(R.id.editText);
//        timetx=myview.findViewById(R.id.editText2);
//        datetx=myview.findViewById(R.id.editText3);
//
//        preferences = myview.getContext().getSharedPreferences("shareprefkey", Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = preferences.edit();
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editor.putString("data1", event.getText().toString());
//                editor.putString("data2", timetx.getText().toString());
//
//                editor.commit();
//
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
//            }
//        });
//
//        Date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                c=Calendar.getInstance();
//                int day=c.get(Calendar.DAY_OF_MONTH);
//                int month=c.get(Calendar.MONTH);
//                int year=c.get(Calendar.YEAR);
//                dp= new DatePickerDialog(myview.getContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                    datetx.setText(i+"/"+i1+"/"+i2);
//                    }
//                },day,month,year);
//                dp.show();
//
//                //DatePickerDialog datePickerDialog = new DatePickerDialog(myview.getContext(), (DatePickerDialog.OnDateSetListener)myview.getContext(), Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//
//            }
//        });
//
//        time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                c=Calendar.getInstance();
//                int hour=c.get(Calendar.HOUR);
//                int minuit=c.get(Calendar.MINUTE);
//                TimePickerDialog tp = new TimePickerDialog(myview.getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                        timetx.setText(i+":"+i1);
//                    }
//                },hour,minuit,true);
//                tp.show();
//
//            }
//        });

        return myview;
    }


}
