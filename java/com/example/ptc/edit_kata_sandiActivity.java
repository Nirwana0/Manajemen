package com.example.ptc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class edit_kata_sandiActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;
    private EditText confirmPasswordEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kata_sandi);

        // Inisialisasi elemen-elemen UI
        usernameEditText = findViewById(R.id.username);
        oldPasswordEditText = findViewById(R.id.old_password);
        newPasswordEditText = findViewById(R.id.new_password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        saveButton = findViewById(R.id.buttonSimpan);

        // Set aksi untuk tombol "Simpan"
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePassword();
            }
        });
    }

    private void savePassword() {
        String username = usernameEditText.getText().toString().trim();
        String oldPassword = oldPasswordEditText.getText().toString().trim();
        String newPassword = newPasswordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validasi input
        if (username.isEmpty()) {
            usernameEditText.setError("Username tidak boleh kosong");
            return;
        }
        if (oldPassword.isEmpty()) {
            oldPasswordEditText.setError("Password lama tidak boleh kosong");
            return;
        }
        if (newPassword.isEmpty()) {
            newPasswordEditText.setError("Password baru tidak boleh kosong");
            return;
        }
        if (confirmPassword.isEmpty()) {
            confirmPasswordEditText.setError("Konfirmasi password tidak boleh kosong");
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Password tidak cocok");
            return;
        }

        // Proses menyimpan kata sandi baru (simulasi, disesuaikan dengan kebutuhan)
        // Contoh: simpan ke database atau panggil API
        Toast.makeText(this, "Kata sandi berhasil diubah", Toast.LENGTH_SHORT).show();

        // Tambahkan kode logika lainnya sesuai kebutuhan Anda di sini
    }
}
