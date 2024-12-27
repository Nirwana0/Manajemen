package com.example.ptc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ptc.pref.AppPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity {

    private ImageView iconAbsensi, iconSetting, iconNotifikasi, iconCuti, iconRekapitulasi;
    private TextView userName, dateTime, dateSeconds, textAbsensi, textPenggajian, textNotifikasi, textCuti, textRekapitulasi;
    private CircleImageView profilePicture;

    private Handler handler = new Handler(); // Untuk pembaruan waktu otomatis
    private Runnable timeUpdater = new Runnable() {
        @Override
        public void run() {
            updateDateTime(); // Memperbarui waktu
            handler.postDelayed(this, 1000); // Pembaruan setiap detik
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Inisialisasi elemen-elemen dari layout
        profilePicture = findViewById(R.id.profile_picture);
        userName = findViewById(R.id.user_name);
        dateTime = findViewById(R.id.date_time);  // TextView untuk hari
        dateSeconds = findViewById(R.id.date_seconds); // TextView untuk waktu (jam, menit, detik)
        iconAbsensi = findViewById(R.id.icon_absensi);
        textAbsensi = findViewById(R.id.text_absensi);
        iconSetting = findViewById(R.id.icon_setting);
        textPenggajian = findViewById(R.id.text_setting);
        iconNotifikasi = findViewById(R.id.icon_notifikasi);
        textNotifikasi = findViewById(R.id.text_notifikasi);
        iconCuti = findViewById(R.id.icon_cuti);
        textCuti = findViewById(R.id.text_cuti);
        iconRekapitulasi = findViewById(R.id.icon_rekaptulasi);
        textRekapitulasi = findViewById(R.id.text_rekaptulasi);

        // Contoh: Ubah nama pengguna
        userName.setText("Nirwana");

        // Tampilkan waktu saat ini
        updateDateTime();

        // Listener untuk ikon Absensi
        iconAbsensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MapsActivity.class));
            }
        });

        // Listener untuk ikon Setting
        iconSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, profilActivity.class));
            }
        });

        // Listener untuk ikon Notifikasi
        iconNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, notifikasiActivity.class));
            }
        });

        // Listener untuk ikon Cuti
        iconCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, cutiActivity.class));
            }
        });

        // Listener untuk ikon Rekapitulasi
        iconRekapitulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, rekaptulasiActivity.class));
            }
        });

        // Listener untuk klik foto profil
        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, datadiriActivity.class));
            }
        });
    }

    // Fungsi untuk menampilkan waktu saat ini
    private void updateDateTime() {
        // Format untuk hari
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
        // Format untuk waktu (jam, menit, detik)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        String currentDay = dayFormat.format(new Date());
        String currentTime = timeFormat.format(new Date());

        dateTime.setText(currentDay); // Menampilkan hari
        dateSeconds.setText(currentTime); // Menampilkan waktu lengkap (jam, menit, detik)
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(timeUpdater); // Mulai pembaruan waktu
        loadImage();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(timeUpdater); // Hentikan pembaruan saat aplikasi dijeda
    }

    private void loadImage() {
        AppPreferences appPreferences = new AppPreferences(this);
        String image = appPreferences.getImage();

        if (image != null) {
            Bitmap decodedBitmap = decodeBase64ToBitmap(image);
            profilePicture.setImageBitmap(decodedBitmap);
        }
    }

    private Bitmap decodeBase64ToBitmap(String base64String) {
        byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
