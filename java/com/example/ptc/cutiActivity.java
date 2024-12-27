package com.example.ptc;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class cutiActivity extends AppCompatActivity {

    private EditText namaLengkap, jabatan, nip, mulaiCuti, selesaiCuti, alamatCuti;
    private Spinner spinnerJenisPengajuan;
    private Button buttonPengajuanCuti;
    private ImageView ivBack;  // Back button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuti);

        // Inisialisasi elemen-elemen dari layout
        namaLengkap = findViewById(R.id.nama_lengkap);
        jabatan = findViewById(R.id.jabatan);
        nip = findViewById(R.id.nip);
        mulaiCuti = findViewById(R.id.mulai_cuti);
        selesaiCuti = findViewById(R.id.selesai_cuti);
        alamatCuti = findViewById(R.id.alamat_cuti);
        spinnerJenisPengajuan = findViewById(R.id.spinner_jenis_pengajuan);
        buttonPengajuanCuti = findViewById(R.id.button_pengajuan_cuti);
        ivBack = findViewById(R.id.ivBack); // Initialize the back button

        // Mengatur adapter untuk Spinner (mengambil nilai dari string-array)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jenis_pengajuan_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisPengajuan.setAdapter(adapter);

        // Set onClickListener untuk mulai cuti dan selesai cuti
        mulaiCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(mulaiCuti);
            }
        });

        selesaiCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(selesaiCuti);
            }
        });

        // Set aksi untuk tombol pengajuan cuti
        buttonPengajuanCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajukanCuti();
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

    // Method untuk menangani pengajuan cuti
    private void ajukanCuti() {
        String nama = namaLengkap.getText().toString().trim();
        String jabatanText = jabatan.getText().toString().trim();
        String nipText = nip.getText().toString().trim();
        String jenisPengajuan = spinnerJenisPengajuan.getSelectedItem().toString();
        String mulai = mulaiCuti.getText().toString().trim();
        String selesai = selesaiCuti.getText().toString().trim();
        String alamat = alamatCuti.getText().toString().trim();

        // Validasi input sederhana (anda bisa menambahkan validasi lebih lanjut)
        if (nama.isEmpty() || jabatanText.isEmpty() || nipText.isEmpty() || mulai.isEmpty() || selesai.isEmpty() || alamat.isEmpty()) {
            Toast.makeText(cutiActivity.this, "Harap lengkapi semua data.", Toast.LENGTH_SHORT).show();
        } else {
            // Tampilkan pesan sukses (Anda bisa mengganti dengan pengiriman data ke server)
            Toast.makeText(cutiActivity.this, "Pengajuan cuti berhasil diajukan.", Toast.LENGTH_SHORT).show();

            // Anda bisa menambahkan logika lain seperti menyimpan data ke database atau mengirim data ke server
        }
    }

    // Method untuk menampilkan DatePickerDialog dan mengatur tanggal di EditText
    private void showDatePickerDialog(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                cutiActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Format tanggal yang dipilih (misalnya: 01/11/2024)
                        String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        editText.setText(selectedDate);
                    }
                }, year, month, day);

        // Tampilkan DatePickerDialog
        datePickerDialog.show();
    }
}
