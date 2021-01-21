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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laurents.database.UserAppDatabase;
import com.example.laurents.entity.User;
import com.rey.material.widget.CheckBox;

public class Login extends AppCompatActivity {

    private EditText editTextPassword,editTextPhone;
    private Button buttonLogin2;
    private ProgressDialog pdialog;
    private UserAppDatabase user;
    private SharedPreferences pref;
    private CheckBox checkBox;
    private SharedPreferences.Editor editor;
    private final static String SHARED_PREFS = "Rememberme";
    private TextView textViewAdmin;
    private String statusUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
        buttonLogin2 = (Button)findViewById(R.id.buttonlogin2);
        checkBox =(CheckBox)findViewById(R.id.checkbox);
        textViewAdmin =(TextView)findViewById(R.id.admin);
        statusUser = "User";

        pdialog = new ProgressDialog(Login.this);
        pref = getApplicationContext().getSharedPreferences("SHARED_PREFS", 0);
        editor=pref.edit();

        buttonLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone= editTextPhone.getText().toString();
                String password =editTextPassword.getText().toString();

                if(TextUtils.isEmpty(phone))
                {
                    Toast.makeText(getApplicationContext(),"Veuillez entrer un numéro de téléphone!",Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(),"Veuillez entrer un mot de passe!",Toast.LENGTH_LONG).show();
                }else
                {

                    ValidateIds(phone,password,statusUser);

                }
            }
        });
        textViewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogin2.setText("Se Connecter come admin");
                statusUser ="Admin";
            }
        });

    }

    private void ValidateIds(String phone, String password,String statusUser) {
        user = Room.databaseBuilder(getApplicationContext(),UserAppDatabase.class,"Laurents").allowMainThreadQueries().build();

        User userfile = user.getUserDao().getUser(phone,password);
if(userfile==null)
{
    Toast.makeText(getApplicationContext(),"goodbyeee",Toast.LENGTH_LONG).show();
    return;
}else
    Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        if(checkBox.isChecked()){
            editor.putString("phone",phone);
            editor.putString("password",password);
            editor.apply();
        }
        if(statusUser == "Admin")
        {
            Intent intent =new Intent(Login.this,AdminCategory.class);
            startActivity(intent);
        }else
        {
            Intent intent =new Intent(Login.this,Home.class);
            startActivity(intent);

        }


    }

}