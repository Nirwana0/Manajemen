package com.example.ptc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class rekaptulasiActivity extends AppCompatActivity {

    // Deklarasi elemen UI
    private ImageView backArrow;
    private TextView headerText, tvAttendanceCircle, tvHadir, tvIzin, tvSakit, tvLainLain, tvTerlambat;
    private Spinner spinnerBulan, spinnerTahun;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menghubungkan layout XML dengan activity
        setContentView(R.layout.activity_rekaptulasi); // Pastikan nama file XML benar

        try {
            // Inisialisasi elemen UI
            backArrow = findViewById(R.id.backArrow);
            headerText = findViewById(R.id.headerText);
            tvAttendanceCircle = findViewById(R.id.tvAttendanceCircle);
            tvHadir = findViewById(R.id.tvHadir);
            tvIzin = findViewById(R.id.tvIzin);
            tvSakit = findViewById(R.id.tvSakit);
//            tvLainLain = findViewById(R.id.tvLainLain);
//            tvTerlambat = findViewById(R.id.tvTerlambat);
            spinnerBulan = findViewById(R.id.spinnerBulan);
            spinnerTahun = findViewById(R.id.spinnerTahun);
            tableLayout = findViewById(R.id.tableLayout);

            // Aksi pada tombol back
            backArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); // Kembali ke aktivitas sebelumnya
                }
            });

            // Contoh mengubah teks programmatically
            headerText.setText("Rekapitulasi Kehadiran");
            tvAttendanceCircle.setText("30.91\n5/20 Hari");
            tvHadir.setText("Hadir   3 Hari");
            tvIzin.setText("Izin   3 Hari");
            tvSakit.setText("Sakit   0 Hari");
            tvLainLain.setText("Lain-Lain   1 Hari");
            tvTerlambat.setText("Terlambat   3 Hari");

        } catch (Exception e) {
            // Log error untuk membantu debugging
            e.printStackTrace();
        }
    }
}
