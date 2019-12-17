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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostel.adapters.NotificationAdapter;
import com.example.hostel.helpers.TableNames;
import com.example.hostel.models.Notifications;
import com.example.hostel.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Notification extends Fragment  {
//    EditText event,timetx,datetx;
//    Button Date,time,submit;
//    Calendar c;
//    DatePickerDialog dp;
//
//    SharedPreferences preferences;

    FirebaseFirestore firestore;
    NotificationAdapter notificationAdapter;

    TextView notifyDefaultTV;
    ProgressBar notifyProgressBar;
    RecyclerView notifyRecyclerView;

    HashMap<String, Users> allUserData;

    List<Notifications> notifyList;


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

        notifyDefaultTV = myview.findViewById(R.id.notifyDefaultTV);
        notifyProgressBar = myview.findViewById(R.id.notifyProgressBar);
        notifyRecyclerView = myview.findViewById(R.id.notifyRecyclerView);
        notifyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notifyRecyclerView.setHasFixedSize(true);



        return myview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firestore = FirebaseFirestore.getInstance();

        firestore.collection(TableNames.USERS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                allUserData = new HashMap<>();

                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Users user = doc.toObject(Users.class);

                    allUserData.put(doc.getId(), user);
                }

                firestore.collection(TableNames.NOTIFICATIONS).whereEqualTo(Users.USER_ID, FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                notifyList = new ArrayList<>();

                                if (task.isSuccessful()) {
                                    if (task.getResult().size() > 0) {
                                        for (QueryDocumentSnapshot doc : task.getResult()) {
                                            Notifications notify = new Notifications();

                                            notify._setNotifyId(doc.getId());
                                            notify._setNotifyUserAvatar(allUserData.get(notify.getNotifyUserId()).getUserAvatar());
                                            notify.setNotifyMsg(doc.getString(Notifications.NOTIFY_MSG));
                                        }

                                        notificationAdapter = new NotificationAdapter(getContext(), notifyList);
                                        notifyRecyclerView.setAdapter(notificationAdapter);

                                        notifyProgressBar.setVisibility(View.GONE);
                                        notifyRecyclerView.setVisibility(View.VISIBLE);
                                    } else {
                                        notifyProgressBar.setVisibility(View.GONE);
                                        notifyDefaultTV.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        });
            }
        });
    }
}
