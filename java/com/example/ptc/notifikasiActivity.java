package com.example.ptc;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class notifikasiActivity extends AppCompatActivity {

    // Deklarasi TextView untuk notifikasi item 1
    private TextView tvDateRange1, tvStatus1, tvApprovalStatus1, tvRequestDate1;

    // Deklarasi TextView untuk notifikasi item 2
    private TextView tvDateRange2, tvStatus2, tvApprovalStatus2, tvRequestDate2;

    // Deklarasi ImageView untuk back button
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);

        // Inisialisasi TextView notifikasi item 1
        tvDateRange1 = findViewById(R.id.tvDateRange1);
        tvStatus1 = findViewById(R.id.tvStatus1);
        tvApprovalStatus1 = findViewById(R.id.tvApprovalStatus1);
        tvRequestDate1 = findViewById(R.id.tvRequestDate1);

        // Inisialisasi TextView notifikasi item 2
        tvDateRange2 = findViewById(R.id.tvDateRange2);
        tvStatus2 = findViewById(R.id.tvStatus2);
        tvApprovalStatus2 = findViewById(R.id.tvApprovalStatus2);
        tvRequestDate2 = findViewById(R.id.tvRequestDate2);

        // Inisialisasi ImageView untuk back button
        ivBack = findViewById(R.id.ivBack);

        // Set nilai text pada TextView notifikasi item 1
        tvDateRange1.setText("Selasa, 12 Sep 2023 - Rabu, 13 Sep 2023");
        tvStatus1.setText("Izin");
        tvApprovalStatus1.setText("Belum disetujui");
        tvRequestDate1.setText("Tanggal pengajuan: Senin, 11 Sep 2023");

        // Set nilai text pada TextView notifikasi item 2
        tvDateRange2.setText("Rabu, 4 Sep 2023");
        tvStatus2.setText("Sakit");
        tvApprovalStatus2.setText("Disetujui");
        tvRequestDate2.setText("Tanggal pengajuan: Senin, 11 Sep 2023");

        // Set OnClickListener untuk back button
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous activity
                onBackPressed(); // This will navigate back to the previous screen
            }
        });
    }
}
