package edu.ptit.de4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.ptit.de4.R;
import edu.ptit.de4.model.Item;

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
        holder.tenSach.setText(item.getTenSach());
        holder.tacGia.setText(item.getTacGia());
        holder.phamVi.setText(item.getPhamVi());
        holder.doiTuong.setText(item.getDoiTuong());
        holder.danhGia.setText(Float.toString(item.getDanhGia()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tenSach, tacGia, phamVi, doiTuong, danhGia;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            tenSach = view.findViewById(R.id.tvTenSachit);
            tacGia = view.findViewById(R.id.tvTacGiait);
            phamVi = view.findViewById(R.id.tvPhamViit);
            doiTuong = view.findViewById(R.id.tvDoiTuongit);
            danhGia = view.findViewById(R.id.tvDanhGiait);
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