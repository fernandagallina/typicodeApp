package com.example.fernanda.typicodeapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fernanda.typicodeapp.App;
import com.example.fernanda.typicodeapp.model.module.MainModule;
import com.example.fernanda.typicodeapp.R;
import com.example.fernanda.typicodeapp.UserRecyclerViewAdapter;
import com.example.fernanda.typicodeapp.model.User;
import com.example.fernanda.typicodeapp.model.component.DaggerMainComponent;
import com.example.fernanda.typicodeapp.presenter.MainPresenterImpl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Fernanda on 12/11/2016.
 */

public class MainFragment extends Fragment implements MainView {

    @Inject
    MainPresenterImpl presenter;

    UserRecyclerViewAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<User> userList;
    private Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        DaggerMainComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);

        userList = new ArrayList<>();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.loadUsers();
    }


    @Override
    public void showUsers(List<User> users) {
        for(int i = 0; i < users.size(); i++) {
            userList.add(users.get(i));
        }

        adapter = new UserRecyclerViewAdapter(userList);
        adapter.setUsers(users);
        adapter.setCallback(new UserRecyclerViewAdapter.Callback() {

            @Override
            public void onItemClick(User user) {

                ((MainActivity)getActivity()).replaceFragments(user);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
