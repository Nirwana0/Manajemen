package com.example.ptc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ptc.network.ApiService;
import com.example.ptc.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button btnmasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind Views
        editTextEmail = findViewById(R.id.username); // ID sesuai layout
        editTextPassword = findViewById(R.id.password);
        btnmasuk = findViewById(R.id.btn_login);

        // Set OnClickListener untuk tombol login
        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(loginActivity.this, "Email atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            }
        });
    }

    // Metode untuk memanggil API login
    private void loginUser(String email, String password) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<User>> call = apiService.getUsers(); // Mengambil semua data user

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Cek apakah data user ada
                    List<User> users = response.body();
                    boolean userFound = false;
                    for (User user : users) {
                        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                            userFound = true;
                            Toast.makeText(loginActivity.this, "Welcome, " + user.getName(), Toast.LENGTH_SHORT).show();

                            // Pindah ke Dashboard
                            Intent intent = new Intent(loginActivity.this, DashboardActivity.class);
                            intent.putExtra("USER_NAME", user.getName());
                            startActivity(intent);
                            finish();
                            break;
                        }
                    }

                    if (!userFound) {
                        Toast.makeText(loginActivity.this, "Login gagal. Email atau password salah.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(loginActivity.this, "Tidak ada respon dari server.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(loginActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loginActivity", "Error: " + t.getMessage());
            }
        });
    }
}
