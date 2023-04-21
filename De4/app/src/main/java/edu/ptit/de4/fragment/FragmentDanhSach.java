package edu.ptit.de4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ptit.de4.R;
import edu.ptit.de4.UpdateDeleteActivity;
import edu.ptit.de4.adapter.RecycleViewAdapter;
import edu.ptit.de4.database.SQLiteHelper;
import edu.ptit.de4.model.Item;

public class FragmentDanhSach extends Fragment implements RecycleViewAdapter.ItemListener {
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleViewHome);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Item> list = db.getAll();
        adapter.setList(list);
        adapter.setItemListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItem(position);
        Intent t = new Intent(getActivity(), UpdateDeleteActivity.class);
        t.putExtra("item", item);
        startActivity(t);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> list = db.getAll();
        adapter.setList(list);
    }
}