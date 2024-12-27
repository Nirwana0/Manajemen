package com.example.ptc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class profilActivity extends AppCompatActivity {

    private TextView tvAppBarTitle, tvPasswordSecurity, tvLogout, tvPrivacyPolicy, tvAboutUs;
    private ImageView ivBackButton, ivPasswordIcon, ivLogoutIcon, ivAboutUsIcon;
    private ConstraintLayout clPasswordSecurity, clLogout, clAboutUs;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil); // Pastikan nama layout sesuai

        // Inisialisasi tombol kembali
        ivBackButton = findViewById(R.id.ivBackButton);

        // Inisialisasi komponen UI
        clPasswordSecurity = findViewById(R.id.clPasswordSecurity);
        ivPasswordIcon = findViewById(R.id.ivPasswordIcon);
        tvPasswordSecurity = findViewById(R.id.tvPasswordSecurity);

        clLogout = findViewById(R.id.clLogout);
        ivLogoutIcon = findViewById(R.id.ivLogoutIcon);
        tvLogout = findViewById(R.id.tvLogout);

        tvPrivacyPolicy = findViewById(R.id.tvPrivacyPolicy);

        // Inisialisasi untuk Tentang Kami
        clAboutUs = findViewById(R.id.clAboutUs);
        ivAboutUsIcon = findViewById(R.id.ivAboutUsIcon);
        tvAboutUs = findViewById(R.id.tvAboutUs);

        // Set listener untuk tombol kembali
        ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Menggunakan fungsi bawaan untuk kembali ke halaman sebelumnya
            }
        });

        // Set listener untuk tombol "Keamanan dan Kata Sandi"
        clPasswordSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profilActivity.this, edit_kata_sandiActivity.class);
                startActivity(intent);
            }
        });

        // Set listener untuk tombol "Keluar"
        clLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        // Set listener untuk tombol "Kebijakan Privasi"
        tvPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profilActivity.this, privacy_policyActivity.class);
                startActivity(intent);
            }
        });

        // Set listener untuk tombol "Tentang Kami"
        clAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profilActivity.this, about_usActivity.class);
                startActivity(intent);
            }
        });
    }

    // Fungsi logout
    private void logout() {
        Intent intent = new Intent(profilActivity.this, loginActivity.class);
        startActivity(intent);
        finish(); // Menutup ProfilActivity setelah logout
    }
}
