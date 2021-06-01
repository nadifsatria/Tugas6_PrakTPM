package com.example.db_lokal.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "kampus_db")
public class DataKampus {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "tanggal")
    private String tanggal;
    @ColumnInfo(name = "pemasukankotor")
    private int pemasukankotor;
    @ColumnInfo(name = "pengeluaran")
    private int pengeluaran;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getPemasukankotor() {
        return pemasukankotor;
    }

    public void setPemasukankotor(int pemasukankotor) {
        this.pemasukankotor = pemasukankotor;
    }

    public int getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(int pengeluaran) {
        this.pengeluaran = pengeluaran;
    }
}
