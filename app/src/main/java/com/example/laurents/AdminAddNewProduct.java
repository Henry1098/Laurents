package com.example.laurents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
    private Uri ImageUri;

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

        imageProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
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
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageProduit.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }

}
}