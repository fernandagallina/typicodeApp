package com.example.fernanda.typicodeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernanda.typicodeapp.model.User;
import com.example.fernanda.typicodeapp.model.component.DaggerMainComponent;

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

//    @BindView(R.id.list_users)
//    ListView listView;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    private Unbinder unbinder;

    @BindView(R.id.list_users)
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        DaggerMainComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        list = new ArrayList<>();
        presenter.loadUsers();
    }


    @Override
    public void showUsers(List<User> users) {
//        for(int i = 0; i < users.size(); i++) {
//            list.add(users.get(i).getName());
//        }

        textView.setText(users.get(0).getName());

//        adapter = new ArrayAdapter<String>(getContext(), R.layout.fragment_main, list);
//        listView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
