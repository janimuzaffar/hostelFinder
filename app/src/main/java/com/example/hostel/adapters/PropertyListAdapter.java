package com.example.hostel.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostel.PropertyActivity;
import com.example.hostel.R;
import com.example.hostel.models.PropertyOwner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.PropertyListViewHolder> {

    Context context;
    List<PropertyOwner> propOwnerList;

    public PropertyListAdapter(Context context, List<PropertyOwner> po) {
        this.context = context;
        this.propOwnerList = po;
    }

    @NonNull
    @Override
    public PropertyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PropertyListViewHolder(LayoutInflater.from(this.context).inflate(R.layout.post_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyListViewHolder holder, final int position) {

        holder.propertyPriceTV.setText(Integer.toString(propOwnerList.get(position).getPrice()));
        holder.propNameTV.setText(propOwnerList.get(position).getPropertyName());
        holder.propertyRoomsTV.setText(propOwnerList.get(position).getBedrooms());

        switch (propOwnerList.get(position).getFurnishing()) {
            case "FULLY": {
                holder.propertyFurnishedTV.setText("Fully Furnished");
                break;
            }
            case "SEMI": {
                holder.propertyFurnishedTV.setText("Semi Furnished");
                break;
            }
            case "NOT": {
                holder.propertyFurnishedTV.setText("Not Furnished");
                break;
            }
        }

        if (propOwnerList.get(position).getPropertyImage() != null) {
            Picasso.get().load(propOwnerList.get(position).getPropertyImage()).into(holder.propertyImageView);
        }


        holder.viewPropertyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PropertyActivity.class).putExtra(PropertyOwner.PROPERTY_ID, propOwnerList.get(position)._getPropertyId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.propOwnerList.size();
    }

    class PropertyListViewHolder extends RecyclerView.ViewHolder {

        TextView propertyPriceTV, propNameTV, propertyFurnishedTV, propertyRoomsTV;
        ImageView propertyImageView;
        Button viewPropertyBtn;

        public PropertyListViewHolder(@NonNull View iv) {
            super(iv);

            propertyPriceTV = iv.findViewById(R.id.propertyPriceTV);
            propNameTV = iv.findViewById(R.id.propNameTV);
            propertyImageView = iv.findViewById(R.id.propertyImageView);
            propertyRoomsTV = iv.findViewById(R.id.propertyRoomsTV);
            propertyFurnishedTV = iv.findViewById(R.id.propertyFurnishedTV);
            viewPropertyBtn = iv.findViewById(R.id.viewPropertyBtn);

        }
    }
}
