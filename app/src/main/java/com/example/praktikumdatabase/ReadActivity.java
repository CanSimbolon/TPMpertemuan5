package com.example.praktikumdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.praktikumdatabase.database.AppDataBase;
import com.example.praktikumdatabase.database.DataDiri;

public class ReadActivity extends AppCompatActivity {

    private AppDataBase appDataBase;
    private RecyclerView rc;
    private DataDiri[] dataDiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        // init database
        appDataBase = AppDataBase.iniDb(this);

        // findViewByid
        rc = findViewById(R.id.rvMain);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        dataDiris = appDataBase.dao().getData();
        read();

    }
    private void read(){


        // tampilkan ke recyclerview
        DataDiriAdapter adapter = new DataDiriAdapter(getApplicationContext(),dataDiris, new DataDiriListener() {
            @Override
            public void onButtonDelete(DataDiri item) {
                appDataBase.dao().deleteData(item);
                Toast.makeText(getApplicationContext(),"Berhasil menghapus data!", Toast.LENGTH_SHORT).show();
                read();
            }
        });
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);
    }
    public void refresh(){ read();}
}
