package edu.ptit.de5.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.ptit.de5.R;
import edu.ptit.de5.model.Item;

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
        holder.hoTen.setText(item.getHoTen());
        holder.dienThoai.setText(item.getDienThoai());
        holder.namSinh.setText(item.getNamSinh());
        holder.kiNang.setText(item.getKiNang());
        int gt = item.getGioiTinh();
        String gt2 = "Nam";
        if (gt == 1) gt2 = "Nu";
        if (gt == 2) gt2 = "Manh";
        holder.gioiTinh.setText(gt2);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView hoTen, dienThoai, namSinh, kiNang, gioiTinh;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            hoTen = view.findViewById(R.id.tvHoTenit);
            dienThoai = view.findViewById(R.id.tvSoDienThoaiit);
            namSinh = view.findViewById(R.id.tvNamSinhit);
            kiNang = view.findViewById(R.id.tvKiNangit);
            gioiTinh = view.findViewById(R.id.tvGioiTinhit);
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