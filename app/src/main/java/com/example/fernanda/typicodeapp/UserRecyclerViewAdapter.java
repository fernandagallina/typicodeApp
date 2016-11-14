package com.example.fernanda.typicodeapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fernanda.typicodeapp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernanda on 14/11/2016.
 */

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserHolders> {

    private List<User> users = new ArrayList<>();

    public UserRecyclerViewAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public UserHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, null);
        return new UserHolders(view);
    }

    @Override
    public void onBindViewHolder(UserHolders holder, int position) {
        holder.textView.setText(users.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
