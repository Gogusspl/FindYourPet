package com.example.myapplication.myApp.app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.myApp.app.listeners.UserListener;
import com.example.myapplication.myApp.app.models.Users;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private final List<Users> users;
    private final UserListener userListener;

    public UsersAdapter(List<Users> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = users.get(position);
        holder.setUserData(user); // Ustaw dane użytkownika w widoku
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView;

        UserViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.userName);
            emailTextView = view.findViewById(R.id.userEmail);
        }

        void setUserData(Users user) {
            nameTextView.setText(user.name);
            emailTextView.setText(user.email);

            itemView.setOnClickListener(v -> userListener.onUserClicked(user));
        }
    }
}
