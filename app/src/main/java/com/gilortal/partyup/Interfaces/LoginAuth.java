package com.gilortal.partyup.Interfaces;

import java.util.HashMap;

public interface LoginAuth {

    void signInUser(String email, String password);
    void signUpForm(HashMap userData, String collection);


}
