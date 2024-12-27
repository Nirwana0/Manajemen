package com.example.ptc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class edit_profilActivity extends AppCompatActivity {

    private ImageView ivBack;
    private Button btnSimpan;
    private EditText statusPernikahanInput, nikInput, noTelpInput, alamatInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        // Inisialisasi elemen UI
        ivBack = findViewById(R.id.ivBack);
        btnSimpan = findViewById(R.id.buttonSimpan);
        statusPernikahanInput = findViewById(R.id.statusPernikahanInput);
        nikInput = findViewById(R.id.nikInput);
        noTelpInput = findViewById(R.id.noTelpInput);
        alamatInput = findViewById(R.id.alamatInput);

        // Aksi saat tombol kembali ditekan
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();  // Kembali ke activity sebelumnya
            }
        });

        // Aksi saat tombol simpan ditekan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data dari EditText
                String statusPernikahan = statusPernikahanInput.getText().toString();
                String nik = nikInput.getText().toString();
                String noTelp = noTelpInput.getText().toString();
                String alamat = alamatInput.getText().toString();

                // Lakukan validasi atau simpan data
                if (statusPernikahan.isEmpty() || nik.isEmpty() || noTelp.isEmpty() || alamat.isEmpty()) {
                    // Tampilkan pesan error jika ada data yang kosong
                    Toast.makeText(edit_profilActivity.this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    // Lakukan penyimpanan data (contoh: simpan ke database atau kirim ke server)
                    // Untuk demonstrasi, kita tampilkan pesan sukses
                    Toast.makeText(edit_profilActivity.this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();

                    // Kembali ke activity sebelumnya setelah data disimpan
                    finish();
                }
            }
        });
    }
}
