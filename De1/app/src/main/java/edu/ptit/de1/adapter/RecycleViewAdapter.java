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
import edu.ptit.de1.model.Item;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    List<Item> list;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Item getItem(int position)
    {
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = list.get(position);
        holder.ten.setText(item.getTen());
        holder.noiDung.setText(item.getNoiDung());
        holder.ngay.setText(item.getNgay());
        holder.tinhTrang.setText(item.getTinhTrang());
        holder.hopTac.setChecked(item.isHopTac());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView ten, noiDung, ngay, tinhTrang;
        private CheckBox hopTac;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            ten = view.findViewById(R.id.tvTenit);
            noiDung = view.findViewById(R.id.tvNoiDungit);
            ngay = view.findViewById(R.id.tvNgayit);
            tinhTrang = view.findViewById(R.id.tvTinhTrangit);
            hopTac = view.findViewById(R.id.cbHopTacit);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemListener != null)
            {
                itemListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view, int position);
    }
}