package com.example.ptc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class about_usActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us); // Pastikan file XML Anda bernama activity_main.xml

        // Inisialisasi tombol kembali
        ImageView ivBack = findViewById(R.id.ivBack);

        // Tambahkan listener untuk tombol kembali
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aksi ketika tombol kembali ditekan
                finish(); // Menutup aktivitas saat ini dan kembali ke aktivitas sebelumnya
            }
        });
    }
}
