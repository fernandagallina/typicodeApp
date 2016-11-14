package com.example.fernanda.typicodeapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

/**
 * Created by Fernanda on 14/11/2016.
 */

public class UserHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.usertitle)
    public TextView textView;

    public UserHolders(View itemView) {
        super(itemView);
        // For some reason, BindView is not working
        textView = (TextView) itemView.findViewById(R.id.usertitle);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),
                textView.getText(), Toast.LENGTH_SHORT)
                .show();
    }
}
