package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hostel.helpers.TableNames;
import com.example.hostel.models.PropertyOwner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PropertyActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    TextView propertyNameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        setTitle("Property");

        firestore = FirebaseFirestore.getInstance();

        propertyNameTV = findViewById(R.id.propertyNameTV);

        firestore.collection(TableNames.PROPERTY_OWNERS).document(getIntent().getStringExtra(PropertyOwner.PROPERTY_ID))
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            PropertyOwner props = task.getResult().toObject(PropertyOwner.class);
                            propertyNameTV.setText(props.getPropertyName());
                        }
                    }
                });
    }
}
