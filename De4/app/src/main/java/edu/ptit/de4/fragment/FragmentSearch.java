package edu.ptit.de4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ptit.de4.R;
import edu.ptit.de4.adapter.RecycleViewAdapter;
import edu.ptit.de4.database.SQLiteHelper;
import edu.ptit.de4.model.Item;

public class FragmentSearch extends Fragment {
    private RecyclerView recyclerView;
    private SearchView searchView;
    private Spinner spTacGia;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Item> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> list = db.searchByTacGiaOrTenSach(s);
                adapter.setList(list);
                return true;
            }
        });

        spTacGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cate = spTacGia.getItemAtPosition(position).toString();
                List<Item> list;
                if(!cate.equalsIgnoreCase("all")){
                    list = db.searchByTacGia(cate);
                }
                else
                {
                    list = db.getAll();
                }
                adapter.setList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycleView);
        searchView = view.findViewById(R.id.search);
        spTacGia = view.findViewById(R.id.spCategory);
        String[] arr = getResources().getStringArray(R.array.DSTacGia);
        String[] arr1 = new String[arr.length+1];
        arr1[0] = "All";
        for(int i = 0; i < arr.length;i++)
        {
            arr1[i+1] = arr[i];
        }
        spTacGia.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> list = db.getAll();
        adapter.setList(list);
    }
}