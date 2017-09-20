package com.example.kit.logininsoical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginPage extends Fragment {

    public LoginPage() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_page, container, false);
        //final Intent intent = new Intent(getActivity(), VKLoginActivity.class);
        ((Button)view.findViewById(R.id.vk_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VKLoginActivity.class));
            }
        });
        ((Button)view.findViewById(R.id.google_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GPLoginActivity.class));
            }
        });
        return view;
    }
}