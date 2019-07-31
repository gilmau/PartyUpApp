package com.gilortal.partyup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.gilortal.partyup.Fragments.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MoveToFrag {

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener authStateListener;

    Bundle savedInstanceState;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        this.savedInstanceState = savedInstanceState;


        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Toast.makeText(MainActivity.this, "new user sign up - listening", Toast.LENGTH_SHORT).show();
                View headerView = navigationView.getHeaderView(0); //title of drawer
//                TextView userNameDrawerTV = headerView.findViewById(R.id.);
//                TextView userTypeDrawerTV = headerView.findViewById(R.id.);
                Log.d("STATE LISTENER", "new user sign up - listening");
                final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null) { //user is logged in
                   // sharedPref.setSignedInStatus(true);
                    Log.d("STATE LISTENER", "Signed in");
                    //     userNameDrawerTV.setText(currentUser.getDisplayName());
                  //  sharedPref.setMyUserId(currentUser.getUid());
//                    userType                   DrawerTV.setText("");
                    db.collection(Consts.DB_DJS).document(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d("onAuthStateChanged", "success");
                                if (task.getResult().exists()) {
                                   // sharedPref.setIsDj(true);
                                    Log.d("onAuthStateChanged", "user is a dj");
                                //   gotToFrag(Consts.DJ_PROFILE_FRAG, currentUser.getUid(), Consts.DB_DJS);
                                } else {
                                    Log.d("onAuthStateChanged", "user NOT a dj");
                                 //   sharedPref.setIsDj(false);
                                //    gotToFrag(Consts.USER_PROFILE_FRAG, currentUser.getUid(), Consts.DB_USERS);
                                }
                            } else {
                                Log.d("onAuthStateChanged", "failed connecting to DB");

                            }
                        }
                    });


//                    navigationView.getMenu().findItem(R.id.sign_in).setVisible(false);
//                    navigationView.getMenu().findItem(R.id.sign_up).setVisible(false);
//                    navigationView.getMenu().findItem(R.id.reset_password).setVisible(false);
//                    navigationView.getMenu().findItem(R.id.sign_out).setVisible(true);

                } else { //signed out
                    changeFragmentDisplay(Consts.LOGIN_SCREEN_FRAG);
                //    sharedPref.setSignedInStatus(false);
                 //   sharedPref.setMyUserId("");


//                    userNameDrawerTV.setText(getResources().getString(R.string.login_please));
//                    userTypeDrawerTV.setText(getResources().getString(R.string.wait_for_you));
//                    navigationView.getMenu().findItem(R.id.sign_in).setVisible(true);
//                    navigationView.getMenu().findItem(R.id.sign_up).setVisible(true);
//                    navigationView.getMenu().findItem(R.id.reset_password).setVisible(true);
//                    navigationView.getMenu().findItem(R.id.sign_out).setVisible(false);
                }

            }
        };
        changeFragmentDisplay(Consts.LOGIN_SCREEN_FRAG);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void goToSignUpForm(int signUpFragment) {
        changeFragmentDisplay(signUpFragment);
    }

    private void changeFragmentDisplay(int displayFragment) {

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (displayFragment) {
                case Consts.LOGIN_SCREEN_FRAG:
                    Log.i("POSITION", "inside change display to login screen");
                    LoginFragment loginFragment = new LoginFragment();
                    fragmentTransaction.replace(R.id.fragment_container, loginFragment);
                    fragmentTransaction.addToBackStack(null);
                    break;
//                case Consts.SIGNUP_FORM_FRAG:
//                    SignUpFormFragment signUpFormFragment = new SignUpFormFragment();
//                    fragmentTransaction.replace(R.id.fragment_container, signUpFormFragment);
//                    fragmentTransaction.addToBackStack(null);
//                    break;
//                case Consts.DJ_PROFILE_FRAG:
//                    fragmentTransaction.replace(R.id.fragment_container, new DjProfileFragment());
//                    fragmentTransaction.addToBackStack(null);
//                    break;
//                case Consts.USER_PROFILE_FRAG:
//                    fragmentTransaction.replace(R.id.fragment_container, new UserProfileFragment());
//                    fragmentTransaction.addToBackStack(null);
//                    break;
//                case Consts.EVENT_FRAG:
//                    fragmentTransaction.replace(R.id.fragment_container, new EventFragment());
//                    fragmentTransaction.addToBackStack(null);
//                    break;
            }

            fragmentTransaction.commit();
            Log.i("POSITION", "done commit");
        }
    }
}
