package com.example.hostel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PopAct extends Activity {

    Button popupFormSubmitBtn;
    EditText propertyNameET,socity,pric,phon,nam;
    TextView popupActivityTagTV ;
    LinearLayout propertyNameFormWrapperLL, ownershipFormWrapperLL,socityll,bedll,vacentll,phonell,namell,pricell;
    SharedPreferences preferences;

    RadioGroup radioGroup,radiobed,radiovec;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        String FORM_TYPE = getIntent().getStringExtra("formType");
        radioGroup=findViewById(R.id.ownradiogrup);
        radiobed=findViewById(R.id.bed_radiog);
        radiovec=findViewById(R.id.vacnrad);
        propertyNameFormWrapperLL = findViewById(R.id.propertyNameFormWrapperLL);
        ownershipFormWrapperLL = findViewById(R.id.ownershipFormWrapperLL);
        bedll=findViewById(R.id.bed_ll);
        socityll=findViewById(R.id.socity_ll);
        vacentll=findViewById(R.id.vacent_ll);
        phonell=findViewById(R.id.phone_ll);
        namell=findViewById(R.id.name_ll);
        pricell=findViewById(R.id.price_ll);

        pric=findViewById(R.id.prictx);
        phon=findViewById(R.id.phontx);
        nam=findViewById(R.id.nametx);

        socity=findViewById(R.id.socityet);
        popupFormSubmitBtn = findViewById(R.id.popupFormSubmitBtn);
        propertyNameET = findViewById(R.id.propertyNameET);
        popupActivityTagTV = findViewById(R.id.popupActivityTagTV);
        popupActivityTagTV.setText(FORM_TYPE);

        preferences = getSharedPreferences("shareprefkey", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();


        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height= dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.4));

        WindowManager.LayoutParams params =getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=20;

        getWindow().setAttributes(params);

        popupFormSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (propertyNameFormWrapperLL.getVisibility() == View.VISIBLE) {
                    //String mystr = propertyNameET.getText().toString().trim();
                    editor.putString("prop", propertyNameET.getText().toString());
                    editor.commit();
                    finish();


                    startActivity(new Intent(PopAct.this,HomeDetails.class));


                }

                if (ownershipFormWrapperLL.getVisibility() == View.VISIBLE) {
                    //String mystr = propertyNameET.getText().toString().trim();
                    editor.putString("own", radioButton.getText().toString());
                    editor.commit();
                    finish();

                    startActivity(new Intent(PopAct.this,HomeDetails.class));


                }

                if (socityll.getVisibility() == View.VISIBLE) {
                    //String mystr = propertyNameET.getText().toString().trim();
                    editor.putString("soc", socity.getText().toString());
                    editor.commit();
                    finish();

                    startActivity(new Intent(PopAct.this,HomeDetails.class));
                }

                if (bedll.getVisibility() == View.VISIBLE) {

                    int rodioid = radiobed.getCheckedRadioButtonId();
                    radioButton= findViewById(rodioid);
                    //String mystr = propertyNameET.getText().toString().trim();
                    editor.putString("bed", radioButton.getText().toString());
                    editor.commit();
                    finish();

                    startActivity(new Intent(PopAct.this,HomeDetails.class));
                }

                if (vacentll.getVisibility() == View.VISIBLE) {
                    int rodioid = radiovec.getCheckedRadioButtonId();
                    radioButton= findViewById(rodioid);
                    //String mystr = propertyNameET.getText().toString().trim();
                    editor.putString("vac", radioButton.getText().toString());
                    editor.commit();
                    finish();

                    startActivity(new Intent(PopAct.this,HomeDetails.class));
                }

                if (namell.getVisibility() == View.VISIBLE) {
                    //String mystr = propertyNameET.getText().toString().trim();
                    editor.putString("nam", nam.getText().toString());
                    editor.commit();
                    finish();

                    startActivity(new Intent(PopAct.this,HomeDetails.class));
                }

                if (phonell.getVisibility() == View.VISIBLE) {
                    //String mystr = propertyNameET.getText().toString().trim();
                    editor.putString("pho", phon.getText().toString());
                    editor.commit();
                    finish();

                    startActivity(new Intent(PopAct.this,HomeDetails.class));
                }

                if (pricell.getVisibility() == View.VISIBLE) {
                    //String mystr = propertyNameET.getText().toString().trim();
                    editor.putInt("pri", Integer.parseInt(pric.getText().toString()));
                    editor.commit();
                    finish();

                    startActivity(new Intent(PopAct.this,HomeDetails.class));
                }


            }
        });

        switch (FORM_TYPE) {
            case "Property Name": {
                propertyNameFormWrapperLL.setVisibility(View.VISIBLE);
                break;
            }
            case "Ownership": {
                ownershipFormWrapperLL.setVisibility(View.VISIBLE);
                break;
            }
            case "Society Name": {
                socityll.setVisibility(View.VISIBLE);
                break;
            }

            case "Bedrooms": {
                bedll.setVisibility(View.VISIBLE);
                break;
            }

            case "Vacant": {
                vacentll.setVisibility(View.VISIBLE);
                break;
            }

            case "Name": {
                namell.setVisibility(View.VISIBLE);
                break;
            }

            case "Phone Number": {
                phonell.setVisibility(View.VISIBLE);
                break;
            }

            case "Price": {
                pricell.setVisibility(View.VISIBLE);
                break;
            }
        }


    }
    public  void checkButton(View v){
        int rodioid = radioGroup.getCheckedRadioButtonId();
        radioButton= findViewById(rodioid);
        Toast.makeText(this, "value "+radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}

