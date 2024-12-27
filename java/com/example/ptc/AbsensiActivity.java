package com.example.ptc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AbsensiActivity extends AppCompatActivity {

    private ImageView selfieImageView, ivBack;
    private Button buttonCamera, kirimAbsensi;
    private Switch switchMasuk, switchPulang;
    private TextView lokasiLabel, jamLabel;
    private ActivityResultLauncher<Intent> cameraActivityLauncher;

    // Location Manager
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        // Inisialisasi komponen UI
        selfieImageView = findViewById(R.id.selfie_image_view);
        ivBack = findViewById(R.id.ivBack); // Tombol kembali
        buttonCamera = findViewById(R.id.button_camera);
        kirimAbsensi = findViewById(R.id.kirim_absensi);
        switchMasuk = findViewById(R.id.switch_masuk);
        switchPulang = findViewById(R.id.switch_pulang);
        lokasiLabel = findViewById(R.id.lokasi_label);
        jamLabel = findViewById(R.id.jam_label);

        // Setup Camera Launcher
        cameraActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                selfieImageView.setImageBitmap(photo);
                Toast.makeText(this, "Foto berhasil diambil!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal mengambil foto.", Toast.LENGTH_SHORT).show();
            }
        });

        // Inisialisasi Location Manager
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Menampilkan waktu saat ini
        showCurrentTime();

        // Menampilkan lokasi saat ini
        showCurrentLocation();

        // Atur logika untuk switch Masuk dan Pulang agar hanya satu yang aktif
        switchMasuk.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchPulang.setChecked(false); // Nonaktifkan switch Pulang
            }
        });

        switchPulang.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchMasuk.setChecked(false); // Nonaktifkan switch Masuk
            }
        });

        // Tombol kamera
        buttonCamera.setOnClickListener(view -> openCamera());

        // Tombol kirim absensi
        kirimAbsensi.setOnClickListener(view -> {
            if (isFormValid()) {
                String status = switchMasuk.isChecked() ? "Masuk" : "Pulang";
                Toast.makeText(this, "Absensi berhasil dikirim! Status: " + status, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Harap lengkapi absensi.", Toast.LENGTH_SHORT).show();
            }
        });

        // Set aksi untuk tombol kembali
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke halaman sebelumnya
                onBackPressed();
            }
        });
    }

    // Membuka kamera untuk mengambil foto selfie
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraActivityLauncher.launch(intent);
    }

    // Menampilkan waktu saat ini
    private void showCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String currentTime = sdf.format(Calendar.getInstance().getTime());
        jamLabel.setText(currentTime);
    }

    // Menampilkan lokasi saat ini
    private void showCurrentLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);

                        // Ambil komponen alamat
                        String jalan = address.getThoroughfare();
                        String kecamatan = address.getSubLocality();
                        String kota = address.getLocality();
                        String negara = address.getCountryName();

                        // Gabungkan semua informasi dalam satu string
                        String lokasiLengkap = String.format(
                                "Lat: %.6f, Lng: %.6f, %s, %s, %s, %s",
                                latitude,
                                longitude,
                                jalan != null ? jalan : "Tidak Ditemukan",
                                kecamatan != null ? kecamatan : "Tidak Ditemukan",
                                kota != null ? kota : "Tidak Ditemukan",
                                negara != null ? negara : "Tidak Ditemukan"
                        );

                        // Tampilkan lokasi di TextView
                        lokasiLabel.setText(lokasiLengkap);
                    } else {
                        lokasiLabel.setText("Alamat tidak ditemukan.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    lokasiLabel.setText("Gagal mendapatkan alamat.");
                }
            } else {
                lokasiLabel.setText("Lokasi tidak ditemukan.");
            }
        } else {
            Toast.makeText(this, "Izin lokasi diperlukan", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    // Validasi form
    private boolean isFormValid() {
        if (selfieImageView.getDrawable() == null) {
            Toast.makeText(this, "Foto selfie belum diambil!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!switchMasuk.isChecked() && !switchPulang.isChecked()) {
            Toast.makeText(this, "Pilih status Masuk atau Pulang.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }
}
