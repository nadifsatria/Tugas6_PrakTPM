package com.example.db_lokal.view;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.db_lokal.R;
import com.example.db_lokal.entity.AppDatabase;
import com.example.db_lokal.entity.DataKampus;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnOk;
    private RecyclerView recyclerView;
    private EditText etTanggal, etPemasukankotor, etPengeluaran;

    private int id = 0 ;
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOk = findViewById(R.id.btn_submit);
        etTanggal = findViewById(R.id.et_tanggal);
        etPemasukankotor = findViewById(R.id.et_pemasukankotor);
        etPengeluaran = findViewById(R.id.et_pengeluaran);
        recyclerView = findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);

    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil Memasukkan Data", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil Menghapus Data", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etTanggal.setText("");
        etPengeluaran.setText("");
        etPemasukankotor.setText("");
        btnOk.setText("Submit");
    }

    @Override
    public void getData(List<DataKampus> list) {
        mainAdapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataKampus item) {
        etTanggal.setText(item.getTanggal());
        etPemasukankotor.setText(item.getPemasukankotor());
        etPengeluaran.setText(item.getPengeluaran());
        id = item.getId();
        edit = true;
        btnOk.setText("Edit Data");
    }

    @Override
    public void deleteData(DataKampus item) {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnOk){
            if(etTanggal.getText().toString().equals("")||etPemasukankotor.getText().toString().equals("")||etPengeluaran.getText().toString().equals("")){
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {
                if(!edit) {
                    mainPresenter.insertData(etTanggal.getText().toString(), Integer.parseInt(etPemasukankotor.getText().toString()), Integer.parseInt(etPengeluaran.getText().toString()),appDatabase);
                }else{
                    mainPresenter.editData(etTanggal.getText().toString(), Integer.parseInt(etPemasukankotor.getText().toString()), Integer.parseInt(etPengeluaran.getText().toString()),id,appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}