package com.example.praktikumdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.praktikumdatabase.database.AppDataBase;
import com.example.praktikumdatabase.database.DataDiri;

public class UpdateActivity extends AppCompatActivity {
    private AppDataBase appDataBase;
    private EditText et_nama, et_alamat , et_gender;
    private Button btn_update;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        appDataBase = AppDataBase.iniDb(this);
        et_nama = findViewById(R.id.activityUpdateUpdate_name);
        et_alamat = findViewById(R.id.activityUpdateUpdate_alamat);
        et_gender = findViewById(R.id.activityUpdateedit_gender);
        btn_update = findViewById(R.id.activityUpdate_btn_update);

        final int id = getIntent().getIntExtra("id",0);
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("alamat");
        char gender = getIntent().getCharExtra("gender",'N');

        et_nama.setText(name);
        et_alamat.setText(address);
        et_gender.setText(new char[]{gender},0,1);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(id);
            }
        });
    }

    private void updateData(int id) {
        String name = et_nama.getText().toString();
        String address = et_alamat.getText().toString();
        char gender = et_gender.getText().toString().charAt(0);

        DataDiri dataDiri = new DataDiri();
        dataDiri.setId(id);
        dataDiri.setNama(name);
        dataDiri.setAlamat(address);
        dataDiri.setGender(gender);
        appDataBase.dao().updateData(dataDiri);
        Toast.makeText(getApplicationContext(),"Berhasil mengupdate data ! ",Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UpdateActivity.this.finish();
                new ReadActivity().refresh();
            }
        }, 1000);
    }
}
