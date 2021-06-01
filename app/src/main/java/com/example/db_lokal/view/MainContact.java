package com.example.db_lokal.view;
import android.view.View;

import com.example.db_lokal.entity.AppDatabase;
import com.example.db_lokal.entity.DataKampus;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataKampus> list);
        void editData(DataKampus item);
        void deleteData(DataKampus item);
    }

    interface presenter{
        void insertData(String tanggal, int pemasukankotor, int pengeluaran, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String tanggal, int pemasukankotor, int pengeluaran, AppDatabase database);
        void deleteData(DataKampus dataKampus, AppDatabase database);
    }

}