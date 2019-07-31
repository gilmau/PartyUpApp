package com.gilortal.partyup.Fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gilortal.partyup.Consts;
import com.gilortal.partyup.Interfaces.LoginAuth;
import com.gilortal.partyup.Interfaces.SendServerResponeToFrags;
import com.gilortal.partyup.MainActivity;
import com.gilortal.partyup.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SignUpFormFragment extends Fragment implements View.OnClickListener , SendServerResponeToFrags {

    public LoginAuth loginAuth;

    TextInputEditText nameTV, emailTV, passwordTV, confirmPasswordTV;
    CheckBox isDJCheckBox;
    EditText aboutBox;
    CheckBox electronicGenre, rockGenre, popGenre, reggaeGenre, hiphopGenre, israelGenre;
    boolean isDJ = false;
    ImageView profileImage;
    Button confirm;
    String name = "", email = "", password = "", confirmPassword = "";
    List<String> checkedGenres = new ArrayList<>();






    public SignUpFormFragment() {
        // Required empty public constructor
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        ((MainActivity)getActivity()).serverToFragsListener = this;
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        ((MainActivity)getActivity()).serverToFragsListener = null;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up_form, container, false);
        nameTV = view.findViewById(R.id.name_sign_up_form);
        emailTV = view.findViewById(R.id.email_sign_up_form);
        passwordTV = view.findViewById(R.id.password_sign_up_form);
        confirmPasswordTV = view.findViewById(R.id.password_confirm_sign_up_form);
        isDJCheckBox = view.findViewById(R.id.dj_or_not_button_ID);
        aboutBox = view.findViewById(R.id.about_text_id);
        electronicGenre = view.findViewById(R.id.genre_electronic);
        rockGenre = view.findViewById(R.id.genre_rock);
        popGenre = view.findViewById(R.id.genre_pop);
        reggaeGenre = view.findViewById(R.id.genre_reggae);
        hiphopGenre = view.findViewById(R.id.genre_hiphop);
        israelGenre = view.findViewById(R.id.genre_israeli);
        rockGenre.setOnClickListener(this);
        popGenre.setOnClickListener(this);
        reggaeGenre.setOnClickListener(this);
        hiphopGenre.setOnClickListener(this);
        israelGenre.setOnClickListener(this);
        electronicGenre.setOnClickListener(this);
        profileImage = view.findViewById(R.id.profile_pic);
        confirm = view.findViewById(R.id.confirmBox);



        isDJCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDJ = ((CheckBox)v).isChecked();
                if (isDJ)
                    aboutBox.setVisibility(View.VISIBLE);
                else
                    aboutBox.setVisibility(View.GONE);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    name = String.valueOf(nameTV.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    password = String.valueOf(passwordTV.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    confirmPassword = String.valueOf(confirmPasswordTV.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    email = emailTV.getText().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(getActivity().getBaseContext(), password + " " + confirmPassword, Toast.LENGTH_LONG).show();

                if (name == "" || email == "" || password == "" || confirmPassword == "") {
                    Toast.makeText(getActivity().getBaseContext(), "Please Insert All Mandatory fields", Toast.LENGTH_LONG).show();

                } else if (!(password.equals(confirmPassword))){

                    Toast.makeText(getActivity().getBaseContext(), "Please confirm again the password", Toast.LENGTH_LONG).show();
                }
                else {
                    //TODO: IMAGE PROFILE
                }


                HashMap<String, Object> userData = new HashMap();

                userData.put(Consts.COLUMN_NAME, name);
                userData.put(Consts.COLUMN_EMAIL, email);
                userData.put(Consts.COLUMN_PASSWORD, password);
                userData.put(Consts.COLUMN_GENRES, checkedGenres);
                userData.put(Consts.COLUMN_PIC_URL, "");
                if (isDJ) {
                    userData.put(Consts.COLUMN_ABOUT, aboutBox.getText().toString());
                    //followersList = new ArrayList<>();
                    //userData.put(Consts.COLUMN_FOLLOWERS_IDS, followersList);
                    loginAuth.signUpForm(userData, Consts.DB_DJS);
                } else {
                    loginAuth.signUpForm(userData, Consts.DB_USERS);
                    //followingList = new ArrayList<>();
                    //userData.put(Consts.COLUMN_FOLLOWERS_IDS, followingList);
                }
            }
        });


                return view;
    }


    @Override
    public void onClick(View v) {
        checkedGenres.add(((CheckBox)v).getText().toString());
    }
}
