package com.gilortal.partyup.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gilortal.partyup.Consts;
import com.gilortal.partyup.MoveToFrag;
import com.gilortal.partyup.R;


public class LoginFragment extends Fragment {

    TextView emailTV, passwordTV;
    Button signInButton, signUpButton;
    public MoveToFrag moveToFrag;



   // private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        emailTV = view.findViewById(R.id.email_login_fragment);
        passwordTV = view.findViewById(R.id.email_login_fragment);
        signInButton = view.findViewById(R.id.signin_button_login_fragment);
        signUpButton = view.findViewById(R.id.signup_button_login_fragment);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToFrag.goToSignUpForm(Consts.SIGNUP_FORM_FRAG);
            }
        });

        return view;

    }









}
