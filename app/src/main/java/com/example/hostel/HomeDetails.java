package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostel.helpers.TableNames;
import com.example.hostel.models.PropertyOwner;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class HomeDetails extends AppCompatActivity {

    static final int PICK_IMAGE_REQUEST= 1;

    FirebaseFirestore firestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    ImageView hom;
    TextView location,propres;
    TextView owner,owneres;
    TextView society,societyres;
    TextView bedroom,bedroomres;
    TextView vacent,vacentres;
    TextView name,nameres;
    TextView phone,phoneres;
    TextView price,priceres;

    RadioGroup cato_rg,farni_rg;
    RadioButton radioButton,radioButton1;

    ProgressBar propSubmitProgressBar;
    Uri imgUri;

    Button addFilterBtn, propertySubmitBtn, choosePropImgBtn;
    ImageView propImageView;

    SharedPreferences pref;

//    String s;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);

        setTitle("Home Details");

        firestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("propertyImage");

        propSubmitProgressBar = findViewById(R.id.propSubmitProgressBar);
        addFilterBtn = findViewById(R.id.addFilterBtn);
        propertySubmitBtn = findViewById(R.id.propertySubmitBtn);
        choosePropImgBtn = findViewById(R.id.choosePropImgBtn);
        propImageView = findViewById(R.id.propImageView);

        owner=findViewById(R.id.ownership);
        owneres=findViewById(R.id.ownres);

        society=findViewById(R.id.society);
        societyres=findViewById(R.id.societyres);

        bedroom=findViewById(R.id.bedrooms);
        bedroomres=findViewById(R.id.bedres);

        vacent=findViewById(R.id.vacent);
        vacentres=findViewById(R.id.vacres);

        name=findViewById(R.id.nameown);
        nameres=findViewById(R.id.nameres);

        phone=findViewById(R.id.phoneno);
        phoneres=findViewById(R.id.phoneres);

        price=findViewById(R.id.price);
        priceres=findViewById(R.id.priceres);
        location=findViewById(R.id.textView19);
        hom=findViewById(R.id.home00);
        propres=findViewById(R.id.propres);

        pref = getSharedPreferences("shareprefkey", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = pref.edit();


        choosePropImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });


        hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeDetails.this,MainActivity.class));
            }
        });



        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIntent(location.getText().toString());
            }
        });
        propres.setText(pref.getString("prop", ""));





        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIntent(owner.getText().toString());
            }
        });
        owneres.setText(pref.getString("own", ""));





        society.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIntent(society.getText().toString());
            }
        });

        societyres.setText(pref.getString("soc", ""));




        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIntent(bedroom.getText().toString());
            }
        });
        bedroomres.setText(pref.getString("bed", ""));





        vacent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIntent(vacent.getText().toString());
            }
        });
       vacentres.setText(pref.getString("vac", ""));


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIntent(name.getText().toString());

            }
        });
        nameres.setText(pref.getString("nam", ""));





        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIntent(phone.getText().toString());
            }
        });
        phoneres.setText(pref.getString("pho", ""));




        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIntent(price.getText().toString());
            }
        });
        priceres.setText(Integer.toString(pref.getInt("pri", 0)));


        addFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeDetails.this);

                View v = LayoutInflater.from(HomeDetails.this).inflate(R.layout.filter_popup_layout, null);

                ImageButton closePopup = v.findViewById(R.id.closeFilterPopupBtn);

                //finish();


                builder.setView(v);

                final AlertDialog dialog = builder.create();
                dialog.show();

                cato_rg=v.findViewById(R.id.cato_rg);
                int rodioid = cato_rg.getCheckedRadioButtonId();
                radioButton= v.findViewById(rodioid);
                editor.putString("cato", radioButton.getText().toString());
                Toast.makeText(HomeDetails.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                editor.commit();

                farni_rg=v.findViewById(R.id.farni_rg);
                int rodioid1 = farni_rg.getCheckedRadioButtonId();
                radioButton1= v.findViewById(rodioid1);
                Log.d("HOME_DETAILS", radioButton1.getText().toString());
                editor.putString("farn", radioButton1.getText().toString());
                editor.commit();



                closePopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });


        propertySubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propertySubmitBtn.setVisibility(View.GONE);
                propSubmitProgressBar.setVisibility(View.VISIBLE);

                storePostImageToFirestorage();
            }
        });

    }

    public void goToIntent(String formType) {
        startActivity(new Intent(HomeDetails.this, PopAct.class).putExtra("formType", formType));
    }



    public String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST &&
                resultCode == RESULT_OK &&
                data != null &&
                data.getData() != null) {
            imgUri = data.getData();

            Picasso.get().load(imgUri).into(propImageView);
        }
    }

    public void storePostImageToFirestorage() {

        // returns all users credentials
//        final String LOGGED_IN_USER_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        String propName = pref.getString("prop", "");
        String ownership = pref.getString("own", "");
        String socityName = pref.getString("soc", "");
        String bedrooms = pref.getString("bed", "");
        String vacant = pref.getString("vac", "");
        String ownerName = pref.getString("nam", "");
        String phone = pref.getString("pho", "");
        String furnishing = pref.getString("farn", "");
        String propertyLocation = "Indiranagar";
        int price = pref.getInt("pri", 0);


        final PropertyOwner propOwner = new PropertyOwner(
                propName,
                price,
                ownership,
                socityName,
                bedrooms,
                vacant,
                ownerName,
                phone,
                null,
                furnishing,
                propertyLocation,
                null,
                FirebaseAuth.getInstance().getCurrentUser().getUid()
        );

        if (imgUri != null) {
            final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imgUri));

            fileRef.putFile(imgUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) throw task.getException();
                    return fileRef.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();

                                propOwner.setPropertyImage(downloadUri.toString());

                                insertPostToDatabase(propOwner);
                            }
                        }
                    });
        }
        else {
            insertPostToDatabase(propOwner);
        }
    }


    public void insertPostToDatabase(PropertyOwner po) {
        firestore.collection(TableNames.PROPERTY_OWNERS).add(po)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            propertySubmitBtn.setVisibility(View.VISIBLE);
                            propSubmitProgressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}
