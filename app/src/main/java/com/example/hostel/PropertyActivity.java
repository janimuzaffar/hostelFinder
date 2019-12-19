package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hostel.helpers.TableNames;
import com.example.hostel.models.Notifications;
import com.example.hostel.models.PropertyOwner;
import com.example.hostel.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PropertyActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    TextView propertyNameTV,ownerTV,socityTV,bedroomsTV,vacentTV,ownNameTV,phoneTV,priceTV,carogoryTV,furnishingTV, bookRequestStatusTV;
    ImageView profilePropertyIV;
    Button bookPropVisitBtn;
    ProgressBar bookVisitProgressBar;

    PropertyOwner props;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        setTitle("Property");

        firestore = FirebaseFirestore.getInstance();

        propertyNameTV = findViewById(R.id.propertyNameTV);
        profilePropertyIV = findViewById(R.id.profilePropertyIV);
        ownerTV=findViewById(R.id.ownership);
        socityTV=findViewById(R.id.society);
        bedroomsTV=findViewById(R.id.bedrooms);
        vacentTV=findViewById(R.id.vacant);
        ownNameTV=findViewById(R.id.ownName);
        phoneTV=findViewById(R.id.phone);
        priceTV=findViewById(R.id.price);
        carogoryTV=findViewById(R.id.carogory);
        furnishingTV=findViewById(R.id.furnishing);
        bookPropVisitBtn = findViewById(R.id.bookPropVisitBtn);
        bookRequestStatusTV = findViewById(R.id.bookRequestStatusTV);
        bookVisitProgressBar = findViewById(R.id.bookVisitProgressBar);

        firestore.collection(TableNames.USERS).document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (((List)task.getResult().get(Users.PROPERTY_REQUEST_SENT)).contains(getIntent().getStringExtra(PropertyOwner.PROPERTY_ID))) {
                                bookPropVisitBtn.setVisibility(View.GONE);
                                bookRequestStatusTV.setText("Request Sent");
                            }
                        }
                    }
                });

        firestore.collection(TableNames.PROPERTY_OWNERS).document(getIntent().getStringExtra(PropertyOwner.PROPERTY_ID))
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            props = task.getResult().toObject(PropertyOwner.class);

                            propertyNameTV.setText(props.getPropertyName());
                            ownerTV.setText(props.getOwnership());
                            socityTV.setText(props.getSocityName());
                            bedroomsTV.setText(props.getBedrooms());
                            vacentTV.setText(props.getVacant());
                            ownNameTV.setText(props.getOwnerName());
                            phoneTV.setText(props.getPhone());
                            priceTV.setText(Integer.toString(props.getPrice()));
                            carogoryTV.setText(props.getEligibility());
                            furnishingTV.setText(props.getFurnishing());

                            if (props.getPropertyImage() != null) {
                                Picasso.get().load(props.getPropertyImage()).into(profilePropertyIV);
                            } else {
                                Picasso.get().load(R.drawable.testimg).into(profilePropertyIV);
                            }
                        }
                    }
                });


        bookPropVisitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookPropVisitBtn.setVisibility(View.GONE);
                bookVisitProgressBar.setVisibility(View.VISIBLE);

                firestore.collection(TableNames.USERS).document(props.getUserId())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Notifications notification = new Notifications();

                                    notification.setNotifyMsg(task.getResult().getString(Users.USER_NAME) + " has requested to visit your property " + props.getPropertyName());
                                    notification.setNotifyUserId(task.getResult().getId());
                                    notification.setPropertyId(getIntent().getStringExtra(PropertyOwner.PROPERTY_ID));
                                    notification._setNotifyUserAvatar(task.getResult().getString(Users.USER_AVATAR));

                                    firestore.collection(TableNames.NOTIFICATIONS).add(notification)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                    if (task.isSuccessful()) {
                                                        bookVisitProgressBar.setVisibility(View.GONE);
                                                        bookRequestStatusTV.setText("Request Sent");
                                                        bookRequestStatusTV.setVisibility(View.VISIBLE);
                                                    }
                                                }
                                            });

                                    firestore.collection(TableNames.USERS).document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .update(Users.PROPERTY_REQUEST_SENT, FieldValue.arrayUnion(getIntent().getStringExtra(PropertyOwner.PROPERTY_ID)));
                                }
                            }
                        });


            }
        });
    }
}
