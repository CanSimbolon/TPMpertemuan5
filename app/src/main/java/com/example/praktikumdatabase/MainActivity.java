package com.example.praktikumdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.praktikumdatabase.database.AppDataBase;
import com.example.praktikumdatabase.database.DataDiri;

public class MainActivity extends AppCompatActivity {

    private AppDataBase appDataBase;
    private EditText etNama, etAlamat,etGender;
    private Button btnInsert,btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inisiasi data bse
        appDataBase = AppDataBase.iniDb(this);

        etNama = findViewById(R.id.Editnama);
        etAlamat = findViewById(R.id.EditAlamat);
        etGender = findViewById(R.id.EditGender);
        btnInsert = findViewById(R.id.btn_kirim);
        btnRead = findViewById(R.id.btn_Read);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
                startActivity(intent);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });


    }

    private void insertData() {
        /// simpan inputan ke stirng
        String nama = etNama.getText().toString();
        String alamat = etAlamat.getText().toString();
        char gender = etGender.getText().toString().charAt(0);
        final DataDiri dataDiri = new DataDiri();
        dataDiri.setNama(nama);
        dataDiri.setGender(gender);
        dataDiri.setAlamat(alamat);
        appDataBase.dao().insertData(dataDiri);
        etNama.setText("");
        etAlamat.setText("");
        etGender.setText("");
    }
}
