package com.example.laurents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laurents.database.UserAppDatabase;
import com.example.laurents.entity.User;
import java.util.HashMap;

public class Signup extends AppCompatActivity {

    private EditText Name_, Phone_,PW_;
    private Button createButton;
    private ProgressDialog pdialog;
    private UserAppDatabase user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name_ = (EditText)findViewById(R.id.registereditTextName);
        Phone_ = (EditText)findViewById(R.id.registereditTextPhone);
        PW_ = (EditText)findViewById(R.id.registereditTextPassword);
        createButton=(Button)findViewById(R.id.registerbutton);
        pdialog = new ProgressDialog(Signup.this);
        user = Room.databaseBuilder(getApplicationContext(),UserAppDatabase.class,"Laurents").allowMainThreadQueries().build();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    public void CreateAccount()
    {

        String name = Name_.getText().toString();
        String phone= Phone_.getText().toString();
        String password =PW_.getText().toString();

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(getApplicationContext(),"Veuillez entrer un nom d'utilisateur!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(getApplicationContext(),"Veuillez entrer un numéro de téléphone!",Toast.LENGTH_LONG).show();
        } else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(),"Veuillez entrer un mot de passe!",Toast.LENGTH_LONG).show();
        }else
        {
            pdialog.setTitle("Créer Compte");
           pdialog.setMessage("Verification des identifiants en cours...");

            pdialog.show();

            ValidationIdentifiants(name, phone, password);

        }

    }

    private void ValidationIdentifiants(String name, String phone, String password) {
        long id = user.getUserDao().addUser(new User(0,name,phone,password));
        Toast.makeText(getApplicationContext(),"User created",Toast.LENGTH_LONG).show();
        pdialog.dismiss();
    }
}