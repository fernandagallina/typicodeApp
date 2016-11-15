package com.example.fernanda.typicodeapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fernanda.typicodeapp.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fernanda on 14/11/2016.
 */

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserHolders> {

    private List<User> users = new ArrayList<>();
    private Callback callback;

    public UserRecyclerViewAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public UserHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        final UserHolders userHolders = new UserHolders(view);
        userHolders.contentLayout.setOnClickListener(new View.OnClickListener() {
            private List<User> users;

            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(userHolders.user);
                }
            }
        });

        return userHolders;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(UserHolders holder, int position) {
        holder.user = users.get(position);
        holder.textView.setText(users.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static class UserHolders extends RecyclerView.ViewHolder {

        @BindView(R.id.content_layout)
        public View contentLayout;

        public User user;

        @BindView(R.id.usertitle)
        public TextView textView;

        public UserHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            // For some reason, BindView is not working
//            contentLayout = itemView.findViewById(R.id.content_layout);
//            textView = (TextView) itemView.findViewById(R.id.usertitle);
//        itemView.setOnClickListener(this);
        }




//    @Override
//    public void onClick(View v) {
//        Toast.makeText(v.getContext(),
//                textView.getText(), Toast.LENGTH_SHORT)
//                .show();
//    }
    }


    public interface Callback {
        void onItemClick(User user);
    }
}

