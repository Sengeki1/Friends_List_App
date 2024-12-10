package com.example.friends_list_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
        return new FriendViewHolder(mItemView, mInflater.getContext());
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

    public static class FriendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private final TextView friendNameView;
        private final TextView friendEmailView;
        private final TextView friendBirthdayView;
        private final Context context;

        public FriendViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.friendNameView = itemView.findViewById(R.id.friend_name);
            this.friendEmailView = itemView.findViewById(R.id.friend_email);
            this.friendBirthdayView = itemView.findViewById(R.id.friend_birthday);
            this.context = context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            showPopupMenu(view);
        }

        private void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view); // Pop Menu object
            popupMenu.inflate(R.menu.menu_context);
            popupMenu.setOnMenuItemClickListener(this); // wait for an event on the popup menu
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) { // logic when clicked on the pop up items
            if (menuItem.getItemId() == R.id.context_Email) {
                Toast.makeText(this.context, "Email Sent",Toast.LENGTH_LONG).show();
            } else if (menuItem.getItemId() == R.id.context_Delete) {
                Toast.makeText(this.context, "Deleted Friend",Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }
}
