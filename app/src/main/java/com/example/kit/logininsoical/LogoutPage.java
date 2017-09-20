package com.example.kit.logininsoical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LogoutPage extends Fragment {

    public LogoutPage() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logout_page, container, false);

        Button logout_btn = (Button) view.findViewById(R.id.logOut_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getIntent().removeExtra("user_id");
                ((MainActivity)getActivity()).showAutorisationPage();
            }
        });

        ((TextView) view.findViewById(R.id.logout_userId)).setText(getActivity().getIntent().getStringExtra("user_id"));
        ((TextView) view.findViewById(R.id.logout_first)).setText(getActivity().getIntent().getStringExtra("first_name"));
        ((TextView) view.findViewById(R.id.logout_last)).setText(getActivity().getIntent().getStringExtra("last_name"));
        ((TextView) view.findViewById(R.id.logout_photo)).setText(getActivity().getIntent().getStringExtra("photo"));
        ((TextView) view.findViewById(R.id.logOut_from)).setText(getActivity().getIntent().getStringExtra("from"));
        return view;
    }
}