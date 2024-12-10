package com.example.friends_list_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {

    private final List<Friend> friends;
    private final LayoutInflater mInflater;

    public FriendAdapter(Context context, List<Friend> friends) {
        mInflater = LayoutInflater.from(context);
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recycler, parent, false);
        return new FriendViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.friendNameView.setText(String.format("Name: %s\n", friend.getName()));
        holder.friendEmailView.setText(String.format("Email: %s\n", friend.getEmail()));
        holder.friendBirthdayView.setText(String.format("Birthday: %s\n", friend.getBirthday()));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView friendNameView;
        private final TextView friendEmailView;
        private final TextView friendBirthdayView;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            this.friendNameView = itemView.findViewById(R.id.friend_name);
            this.friendEmailView = itemView.findViewById(R.id.friend_email);
            this.friendBirthdayView = itemView.findViewById(R.id.friend_birthday);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
