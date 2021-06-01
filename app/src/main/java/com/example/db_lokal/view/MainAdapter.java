package com.example.db_lokal.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db_lokal.R;
import com.example.db_lokal.entity.DataKampus;

import java.util.List;

public class  MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataKampus> list;
    MainContact.view mView;

    public MainAdapter(Context context, List<DataKampus> list, MainContact.view mView) {
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sekolah,parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataKampus item = list.get(position);
        holder.tvTanggal.setText(item.getTanggal());
        holder.tvPemasukankotor.setText(item.getPemasukankotor());
        holder.tvPengeluaran.setText(item.getPengeluaran());
        holder.id.setText(item.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mView.deleteData(item);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvPemasukankotor,tvPengeluaran,id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_item_Tanggal);
            tvPemasukankotor = itemView.findViewById(R.id.tv_item_Pemasukankotor);
            tvPengeluaran = itemView.findViewById(R.id.tv_item_Pengeluaran);
            id = itemView.findViewById(R.id.tv_item_id);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
