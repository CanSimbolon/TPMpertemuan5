package com.example.praktikumdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikumdatabase.database.DataDiri;

public class DataDiriAdapter extends RecyclerView.Adapter<DataDiriAdapter.VH> {

    private  DataDiri[] list;
    private DataDiriListener listener;
    private  Context context;
    public DataDiri getDataDiri(int index){return list[index];}


    public DataDiriAdapter(Context context,DataDiri[] list,DataDiriListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DataDiriAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.data_diri_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataDiriAdapter.VH holder, final int position) {
        final DataDiri item = list[position];
        holder.tvNama.setText(item.getNama());
        holder.tvAlamat.setText(item.getAlamat());
        holder.tvJenisKel.setText(""+item.getGender());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update = new Intent(context, UpdateActivity.class);
                intent_update.putExtra("name", getDataDiri(position).getNama());
                intent_update.putExtra("alamat", getDataDiri(position).getAlamat());
                intent_update.putExtra("gender",getDataDiri(position).getGender());
                intent_update.putExtra("id",getDataDiri(position).getId());
                context.startActivity(intent_update);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonDelete(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tvNama,tvAlamat,tvJenisKel;
        Button btnDelete,btnUpdate;
        public VH(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvJenisKel = itemView.findViewById(R.id.tvJenisKel);
            btnDelete = itemView.findViewById(R.id.btnHapus);
            btnUpdate = itemView.findViewById(R.id.btn_update);
        }
    }
}
