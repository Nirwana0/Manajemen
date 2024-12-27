package com.example.ptc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ptc.pref.AppPreferences;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class datadiriActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1; // Kode permintaan untuk galeri
    private CircleImageView profilePicture;
    private Button updatePhotoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datadiri);

        // Inisialisasi komponen
        profilePicture = findViewById(R.id.imgProfile);
        updatePhotoButton = findViewById(R.id.btnUpdatePhoto);

        // Listener untuk tombol Update Foto
        updatePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buka galeri
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        // Listener untuk tombol Edit Profil
        findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(datadiriActivity.this, edit_profilActivity.class));
            }
        });

        // Menambahkan listener untuk tombol Kembali (ivBack)
        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();  // Kembali ke activity sebelumnya
            }
        });

        // Memuat gambar profil dari penyimpanan
        loadImage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // Update foto profil dengan gambar yang dipilih
                profilePicture.setImageURI(selectedImageUri);
                AppPreferences appPreferences = new AppPreferences(this);
                try {
                    appPreferences.setImage(encodeImageToBase64(getBitmapFromUri(selectedImageUri)));
                } catch (IOException e) {
                    Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this, "Foto berhasil diperbarui", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        return BitmapFactory.decodeStream(inputStream);
    }

    private String encodeImageToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private void loadImage() {
        AppPreferences appPreferences = new AppPreferences(this);
        String image = appPreferences.getImage();

        if (image != null && !image.isEmpty()) {
            Bitmap decodedBitmap = decodeBase64ToBitmap(image);
            profilePicture.setImageBitmap(decodedBitmap);
        }
    }

    private Bitmap decodeBase64ToBitmap(String base64String) {
        byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
