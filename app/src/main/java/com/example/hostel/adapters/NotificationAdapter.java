package com.example.hostel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostel.R;
import com.example.hostel.models.Notifications;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    Context context;
    List<Notifications> notifyList;

    public NotificationAdapter(Context c, List<Notifications> nlist) {
        this.context = c;
        this.notifyList = nlist;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(context).inflate(R.layout.notification_list_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.notifyContentTV.setText(notifyList.get(position).getNotifyMsg());

        if (notifyList.get(position)._getNotifyUserAvatar() != null) {
            Picasso.get().load(notifyList.get(position)._getNotifyUserAvatar()).into(holder.notifyUserAvatarIV);
        }
    }

    @Override
    public int getItemCount() {
        return notifyList.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        ImageView notifyUserAvatarIV;
        TextView notifyContentTV;

        public NotificationViewHolder(@NonNull View iv) {
            super(iv);

            notifyUserAvatarIV = iv.findViewById(R.id.notifyUserAvatarIV);
            notifyContentTV = iv.findViewById(R.id.notifyContentTV);
        }
    }
}
