package com.example.laurents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.laurents.database.UserAppDatabase;
import com.example.laurents.entity.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AdminAddNewProduct extends AppCompatActivity {


    private String CategoryName;
    private ImageView imageProduit;
    private Button buttonValider;
    private EditText nomproduit,prixproduit,descriptionproduit;
    private final static int GALLERYPICK = 1;
    private String UriImage; //Pour enregistrer l'Uri de l'image dans la Base de données
    private UserAppDatabase user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);
        imageProduit = (ImageView)findViewById(R.id.imageProduit);
        buttonValider =(Button)findViewById(R.id.validerProduit);
        nomproduit = (EditText)findViewById(R.id.productname);
        prixproduit = (EditText)findViewById(R.id.price);
        descriptionproduit = (EditText)findViewById(R.id.description);
        CategoryName = getIntent().getExtras().get("category").toString();
        user = Room.databaseBuilder(getApplicationContext(),UserAppDatabase.class,"Laurents").allowMainThreadQueries().build();

        imageProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nomproduit.getText().toString();
                String description = descriptionproduit.getText().toString();
                String prix = prixproduit.getText().toString();


                if(nom.isEmpty()){
                    Toast.makeText(AdminAddNewProduct.this, "Vous n'avez pas renseigné le nom du produit", Toast.LENGTH_LONG).show();

                }
                else if(description.isEmpty()){
                    Toast.makeText(AdminAddNewProduct.this, "Vous n'avez pas renseigné la description", Toast.LENGTH_LONG).show();

                }
                else if(prix.isEmpty()){
                    Toast.makeText(AdminAddNewProduct.this, "Vous n'avez pas renseigné le prix", Toast.LENGTH_LONG).show();

                }
                else if(imageProduit.getDrawable() == null)
                {
                    Toast.makeText(AdminAddNewProduct.this, "Vous n'avez pas selectionné d'image", Toast.LENGTH_LONG).show();

                }else{
                SaveData(nom,description,prix);
                }
            }
        });

    }


    private void OpenGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERYPICK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                UriImage = imageUri.toString();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageProduit.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Quelque chose a fait défaut", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(AdminAddNewProduct.this, "Vous n'avez pas selectionné d'image", Toast.LENGTH_LONG).show();
        }

}

    private void SaveData(String nom, String description, String prix) {

    if(UriImage==null || UriImage == "")
    {
        Toast.makeText(getApplicationContext(), "Vous n'avez pas selectionné d'image", Toast.LENGTH_LONG).show();
        return;
    }
         long id = user.getProductDao().addProduct(new Product(0, nom, description, prix, UriImage));



    }
}