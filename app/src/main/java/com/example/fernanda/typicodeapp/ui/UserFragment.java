package com.example.fernanda.typicodeapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fernanda.typicodeapp.R;
import com.example.fernanda.typicodeapp.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Fernanda on 15/11/2016.
 */

public class UserFragment extends Fragment {

    private User user;

    @BindView(R.id.name_title)
    TextView nameTitle;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.website)
    TextView website;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.phone)
    TextView phone;


    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameTitle.setText(user.getName());
        username.setText(user.getUsername());
        website.setText(user.getWebsite());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
    }

    public void setUser(User user) {
        this.user = user;
    }
}
