package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hostel.helpers.TableNames;
import com.example.hostel.models.PropertyOwner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class PropertyActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    TextView propertyNameTV,ownerTV,socityTV,bedroomsTV,vacentTV,ownNameTV,phoneTV,priceTV,carogoryTV,furnishingTV;
    ImageView profilePropertyIV;

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

        firestore.collection(TableNames.PROPERTY_OWNERS).document(getIntent().getStringExtra(PropertyOwner.PROPERTY_ID))
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            PropertyOwner props = task.getResult().toObject(PropertyOwner.class);

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
    }
}
