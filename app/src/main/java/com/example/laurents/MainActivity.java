package com.example.laurents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laurents.database.UserAppDatabase;
import com.example.laurents.entity.User;

public class MainActivity extends AppCompatActivity {

    private Button buttonlogin, buttonjoin;
    private UserAppDatabase user;
    private SharedPreferences pref;
    private String mdp,tel;
    private ProgressDialog pdialog;
    private final static String SHARED_PREFS = "Rememberme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonjoin = (Button)findViewById(R.id.buttonjoin);
        buttonlogin = (Button)findViewById(R.id.buttonlogin);
        user = Room.databaseBuilder(getApplicationContext(),UserAppDatabase.class,"Laurents").allowMainThreadQueries().build();
        pref = getApplicationContext().getSharedPreferences(SHARED_PREFS, 0);
        pdialog  = new ProgressDialog(this);

        mdp = pref.getString("password","");
        tel =pref.getString("phone","");


        if(mdp != "" && tel != "")
            AccessValide(mdp,tel);
        buttonjoin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,Signup.class);
        startActivity(intent);
    }
});
buttonlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }
});
    }

    private void AccessValide(String mdp, String tel) {

            pdialog.setTitle("Login Compte");
            pdialog.setMessage("Verification des identifiants en cours...");
            pdialog.show();

            User user_ = user.getUserDao().getUser(tel,mdp);
            if(user_.getPhone().equals(tel) && user_.getPassword().equals(mdp))
            {
                Toast.makeText(MainActivity.this,"helloooo",Toast.LENGTH_LONG).show();
                pdialog.dismiss();
            }else
            {
                Toast.makeText(MainActivity.this,"goodbyeee",Toast.LENGTH_LONG).show();
                pdialog.dismiss();
            }


    }

}