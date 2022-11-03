package com.example.exchangelibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

    Button loginButton;
    EditText loginEmail;

    public LoginFragment() {
    }

//    @Override
//    public void onCreate(Bundle b) {
//        super.onCreate(b);
//
//        loginEmail = (EditText) getView().findViewById(R.id.et_email);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.login_activity, container, false);

        loginButton = (Button) v.findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("HIii");
                Fragment homePageFragment = new HomePageFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_activity, homePageFragment, "Home Page")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;

    }



}