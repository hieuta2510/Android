package edu.ptit.de1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.ptit.de1.R;
import edu.ptit.de1.model.ThongKe;

public class RecycleViewAdapterTKe extends RecyclerView.Adapter<RecycleViewAdapterTKe.HomeViewHolder>{
    List<ThongKe> list;
    public RecycleViewAdapterTKe() {
        list = new ArrayList<>();
    }
    public void setList(List<ThongKe> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tke, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        ThongKe item = list.get(position);
        holder.tinhTrang.setText(item.getTinhTrangTK() + ": " + item.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private TextView tinhTrang;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            tinhTrang = view.findViewById(R.id.tvTinhTrangTK);
        }
    }
}