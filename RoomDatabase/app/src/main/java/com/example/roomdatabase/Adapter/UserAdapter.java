package com.example.roomdatabase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.DatabaseClass;
import com.example.roomdatabase.EntityClass.UserModel;
import com.example.roomdatabase.R;
import com.example.roomdatabase.UpdateData;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    List<UserModel> list;
    DeleteItemClicklistner deleteItemClicklistner;

    public UserAdapter(Context context, List<UserModel> list, DeleteItemClicklistner deleteItemClicklistner) {
        this.context = context;
        this.list = list;
        this.deleteItemClicklistner = deleteItemClicklistner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.phone.setText(list.get(position).getPhoneno());
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateData.class);
                intent.putExtra("id", String.valueOf(list.get(position).getKey()));
                intent.putExtra("name", String.valueOf(list.get(position).getName()));
                intent.putExtra("address", String.valueOf(list.get(position).getAddress()));
                intent.putExtra("phoneno", String.valueOf(list.get(position).getPhoneno()));
                context.startActivity(intent);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItemClicklistner.onItemDelete(position, list.get(position).getKey());

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, phone;
        Button update, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.name);
            update = itemView.findViewById(R.id.updateId);
            delete = itemView.findViewById(R.id.deleteId);
        }
    }

    public interface DeleteItemClicklistner {
        void onItemDelete(int position, int id);
    }
}
