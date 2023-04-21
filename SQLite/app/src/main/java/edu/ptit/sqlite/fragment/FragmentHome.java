package edu.ptit.sqlite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

import edu.ptit.sqlite.R;
import edu.ptit.sqlite.UpdateDeleteMainActivity;
import edu.ptit.sqlite.adapter.RecycleViewAdapter;
import edu.ptit.sqlite.dal.SQLiteHelper;
import edu.ptit.sqlite.model.Item;

public class FragmentHome extends Fragment implements RecycleViewAdapter.ItemListener {
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    private SQLiteHelper db;
    private TextView tvTong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleViewHome);
        tvTong = view.findViewById(R.id.tvTongHome);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String s = f.format(d);
        List<Item> list = db.getByDate(s);
        adapter.setList(list);
        adapter.setItemListener(this);
        tvTong.setText("Tong tien" + tong(list));
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private int tong(List<Item> list)
    {
        int sum = 0;
        for (Item i : list)
        {
            sum += Integer.parseInt(i.getPrice());
        }
        return sum;
    }
    private void res(List<Item> list)
    {
        for (Item i : list)
        {
            System.out.println("du ma sai gon");
            System.out.println(i.getPrice());
            System.out.println("du ma sai gon");
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItem(position);
        Intent t = new Intent(getActivity(), UpdateDeleteMainActivity.class);
        t.putExtra("item", item);
        startActivity(t);
    }

    @Override
    public void onResume() {
        super.onResume();
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        List<Item> list = db.getByDate(f.format(d));
        adapter.setList(list);
        tvTong.setText("Tong tien" + tong(list));
    }
}