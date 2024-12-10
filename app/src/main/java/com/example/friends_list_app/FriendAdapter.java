package com.example.friends_list_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    private final Intent intent;
    private final OnFriendActionListener actionListener;

    public FriendAdapter(Context context, List<Friend> friends, Intent intent, OnFriendActionListener actionListener) {
        mInflater = LayoutInflater.from(context);
        this.friends = friends;
        this.intent = intent;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recycler, parent, false);
        return new FriendViewHolder(mItemView, mInflater.getContext(), friends, intent, actionListener);
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
        private final List<Friend> friends;
        private final Intent intent;
        private final OnFriendActionListener actionListener;

        public FriendViewHolder(@NonNull View itemView, Context context, List<Friend> friends, Intent intent, OnFriendActionListener actionListener) {
            super(itemView);
            this.friendNameView = itemView.findViewById(R.id.friend_name);
            this.friendEmailView = itemView.findViewById(R.id.friend_email);
            this.friendBirthdayView = itemView.findViewById(R.id.friend_birthday);
            this.context = context;
            this.friends = friends;
            this.intent = intent;
            this.actionListener = actionListener;

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
                intent.setData(Uri.parse("email"));
                String[] email = {friends.get(getAdapterPosition()).getEmail(), "xyz@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "This is a subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi, this is the email body");
                intent.setType("message/rfc822");

                Intent chooser = Intent.createChooser(intent, "Launch Email");
                context.startActivity(chooser);
                return true;
            } else if (menuItem.getItemId() == R.id.context_Delete) {
                actionListener.onDeleteFriend(getAdapterPosition());
                Toast.makeText(this.context, "Deleted Friend",Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        }
    }
}
