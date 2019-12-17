package com.example.hostel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostel.adapters.PropertyListAdapter;
import com.example.hostel.helpers.TableNames;
import com.example.hostel.models.PropertyOwner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends Fragment {

    FirebaseFirestore firestore;
    PropertyListAdapter listAdapter;
    List<PropertyOwner> propertyOwnerList;

    RecyclerView propertyListRecyclerView;
    ImageButton filterImageBtn;
    String searchProperty;

    public Home() {}

    public Home(String searchPattern) {
        this.searchProperty = searchPattern;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("HOME_FRAG", "Home fragment onCreateView");
        View view = inflater.inflate(R.layout.home_fregment,container,false);

        filterImageBtn = view.findViewById(R.id.filterImageBtn);
        propertyListRecyclerView = view.findViewById(R.id.propertyListRecyclerView);
        propertyListRecyclerView.setHasFixedSize(true);
        propertyListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        filterImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogPopup();
            }
        });

        Log.d("HOME", searchProperty != null ? searchProperty : "no patterns");

        if (searchProperty != null) {
            getAllPropertyData(searchProperty);
        }
        else {
            getAllPropertyData(null);
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HOME_FRAG", "Home fragment onCreate");
        firestore = FirebaseFirestore.getInstance();
    }

    public void getAllPropertyData(final String sp) {
        propertyOwnerList = new ArrayList<>();

        firestore.collection(TableNames.PROPERTY_OWNERS).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
//                            propertyOwnerList = task.getResult().toObjects(PropertyOwner.class);

                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                PropertyOwner prop = new PropertyOwner();

                                prop.setPrice(Integer.parseInt(doc.get(PropertyOwner.PRICE).toString()));
                                prop.setPropertyName(doc.getString(PropertyOwner.PROPERTY_NAME));
                                prop.setSocityName(doc.getString(PropertyOwner.SOCITY_NAME));
                                prop.setBedrooms(doc.getString(PropertyOwner.BEDROOMS));
                                prop.setVacant(doc.getString(PropertyOwner.VACANT));
                                prop.setOwnerName(doc.getString(PropertyOwner.OWNER_NAME));
                                prop.setPhone(doc.getString(PropertyOwner.PHONE));
                                prop.setEligibility(doc.getString(PropertyOwner.ELIGIBILITY));
                                prop.setPropertyImage(doc.getString(PropertyOwner.PROPERTY_IMAGE));
                                prop.setPropertyLocation(doc.getString(PropertyOwner.PROPERTY_LOCATION));
                                prop.setFurnishing(doc.getString(PropertyOwner.FURNISHING));
                                prop._setPropertyId(doc.getId());

                                if (sp != null && doc.getString(PropertyOwner.PROPERTY_LOCATION).toLowerCase().contains(sp.toLowerCase().trim()))
                                    propertyOwnerList.add(prop);

                                if (sp == null)
                                    propertyOwnerList.add(prop);

                            }

                            listAdapter = new PropertyListAdapter(getContext(), propertyOwnerList);
                            propertyListRecyclerView.setAdapter(listAdapter);
                        }
                    }
                });
    }

    public void openDialogPopup() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View v = LayoutInflater.from(getContext()).inflate(R.layout.home_filter_layout, null);

        final HashMap<String, String> propListFilter = new HashMap<>();

        RadioButton gt1k, gt3k, gt6k, gt10k, BOYS, GIRLS, FAMILY, ANY, BHK1, BHK2, BHK3, BHK4, FULL, SEMI, NOT;
        Button closeFilterPopupBtn, applyFilterBtn;

        gt1k = v.findViewById(R.id.gt1k);
        gt3k = v.findViewById(R.id.gt3k);
        gt6k = v.findViewById(R.id.gt6k);
        gt10k = v.findViewById(R.id.gt10k);
        BOYS = v.findViewById(R.id.BOYS);
        GIRLS = v.findViewById(R.id.GIRLS);
        FAMILY = v.findViewById(R.id.FAMILY);
        ANY = v.findViewById(R.id.ANY);
        BHK1 = v.findViewById(R.id.BHK1);
        BHK2 = v.findViewById(R.id.BHK2);
        BHK3 = v.findViewById(R.id.BHK3);
        BHK4 = v.findViewById(R.id.BHK4);
        FULL = v.findViewById(R.id.FULL);
        SEMI = v.findViewById(R.id.SEMI);
        NOT = v.findViewById(R.id.NOT);
        closeFilterPopupBtn = v.findViewById(R.id.closeFilterPopupBtn);
        applyFilterBtn = v.findViewById(R.id.applyFilterBtn);

        builder.setView(v);

        final AlertDialog dialog = builder.create();
        dialog.show();

        closeFilterPopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        gt1k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("price", Integer.toString(1000));
            }
        });

        gt3k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("price", Integer.toString(3000));
            }
        });

        gt6k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("price", Integer.toString(6000));
            }
        });

        gt10k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("price", Integer.toString(10000));
            }
        });

        BOYS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("category", "BOYS");
            }
        });
        GIRLS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("category", "GIRLS");
            }
        });

        FAMILY.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("category", "FAMILY");
            }
        });

        ANY.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("category", "ANY");
            }
        });

        BHK1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("bedrooms", "1BHK");
            }
        });

        BHK2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("bedrooms", "2BHK");
            }
        });

        BHK3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("bedrooms", "3BHK");
            }
        });

        BHK4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("bedrooms", "4BHK");
            }
        });


        NOT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("furnishing", "NOT");
            }
        });

        SEMI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("furnishing", "SEMI");
            }
        });

        FULL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) propListFilter.put("furnishing", "FULLY");
            }
        });

        applyFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<PropertyOwner> filteredProperty = new ArrayList<>();

                for (PropertyOwner p : propertyOwnerList) {
                    if ((propListFilter.get("price") != null && p.getPrice() > Integer.parseInt(propListFilter.get("price"))) ||
                            (propListFilter.get("bedrooms") != null && p.getBedrooms().equals(propListFilter.get("bedrooms"))) ||
                            (propListFilter.get("furnishing") != null && p.getFurnishing().equals(propListFilter.get("furnishing"))) ||
                            (propListFilter.get("category") != null && p.getEligibility().equals(propListFilter.get("category")))
                    ) {
                        filteredProperty.add(p);
                    }
                }

                listAdapter = new PropertyListAdapter(getContext(), filteredProperty);
                propertyListRecyclerView.setAdapter(listAdapter);

                dialog.cancel();
            }
        });
    }
}
