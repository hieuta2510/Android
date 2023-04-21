package edu.ptit.de5.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import edu.ptit.de5.R;
import edu.ptit.de5.UpdateDeleteMainActivity;
import edu.ptit.de5.adapter.RecycleViewAdapter;
//import edu.ptit.de5.adapter.RecycleViewAdapterTKe;
import edu.ptit.de5.database.SQLiteHelper;
import edu.ptit.de5.model.Item;
//import edu.ptit.de5.model.ThongKe;

public class FragmentSearch extends Fragment implements View.OnClickListener, RecycleViewAdapter.ItemListener {
    private RecyclerView recyclerView, recyclerViewTinhTrang;
    private Button btSearch;
    private SearchView searchView;
    private Spinner spTinhTrang;
    private RecycleViewAdapter adapter;
    private CheckBox cbAnd, cbWeb, cbIos;
//    private RecycleViewAdapterTKe adapterTKe;
    private SQLiteHelper db;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        adapter = new RecycleViewAdapter();
//        adapterTKe = new RecycleViewAdapterTKe();
        db = new SQLiteHelper(getContext());
        List<Item> list = db.getAll();
//        List<ThongKe> list2 = getListTke(list);
        adapter.setList(list);
//        adapterTKe.setList(list2);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerViewTinhTrang.setLayoutManager(manager2);
        recyclerView.setAdapter(adapter);
//        recyclerViewTinhTrang.setAdapter(adapterTKe);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> list = db.searchByTen(s);
//                List<ThongKe> list2 = getListTke(list);
                adapter.setList(list);
//                adapterTKe.setList(list2);
                return true;
            }
        });

        btSearch.setOnClickListener(this);
        spTinhTrang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cate = spTinhTrang.getItemAtPosition(position).toString();
                List<Item> list;
                if(!cate.equalsIgnoreCase("all")){
                    list = db.searchByTinhTrang(cate);
                }
                else
                {
                    list = db.getAll();
                }
//                List<ThongKe> list2 = getListTke(list);
                adapter.setList(list);
//                adapterTKe.setList(list2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

//    private List<ThongKe> getListTke(List<Item> list)
//    {
//        List<ThongKe> list2 = new ArrayList<>();
//        ArrayList<Integer> A = tong(list);
//        ThongKe i1 = new ThongKe("Chua thuc hien", A.get(0));
//        ThongKe i2 = new ThongKe("Dang thuc hien", A.get(1));
//        ThongKe i3 = new ThongKe("Da thuc hien", A.get(2));
//        if(i1.getSoLuong() > 0) list2.add(i1);
//        if(i2.getSoLuong() > 0) list2.add(i2);
//        if(i3.getSoLuong() > 0) list2.add(i3);
//        Collections.sort(list2);
//        return list2;
//    }

//    private ArrayList<Integer> tong(List<Item> list)
//    {
//        ArrayList<Integer> A = new ArrayList<>();
//        int sum1 = 0, sum2 = 0, sum3 = 0;
//        for (Item i : list)
//        {
//            if(i.getTinhTrang().equals("Chua thuc hien")) sum1++;
//            else if(i.getTinhTrang().equals("Dang thuc hien")) sum2++;
//            else sum3++;
//        }
//        A.add(sum1);
//        A.add(sum2);
//        A.add(sum3);
//        return A;
//    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerViewTinhTrang = view.findViewById(R.id.recycleViewTinhTrang);
        btSearch = view.findViewById(R.id.btSearch);
        searchView = view.findViewById(R.id.search);
        spTinhTrang = view.findViewById(R.id.spCategory);
        cbAnd = view.findViewById(R.id.cbAndroids);
        cbIos = view.findViewById(R.id.cbIoss);
        cbWeb = view.findViewById(R.id.cbWebs);
        String[] arr = getResources().getStringArray(R.array.gioiTinh);
        String[] arr1 = new String[arr.length+1];
        arr1[0] = "All";
        for(int i = 0; i < arr.length;i++)
        {
            arr1[i+1] = arr[i];
        }
        spTinhTrang.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onClick(View v) {
//        if(v == eFrom)
//        {
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int y, int m, int d) {
//                    String date = "";
//                    if(m > 8)
//                    {
//                        date = d+"/"+(m+1)+"/"+y;
//                    } else{
//                        date = d+"/0"+(m+1)+"/"+y;
//                    }
//
//                    if(d < 10) date = "0"+date;
//                    eFrom.setText(date);
//                }
//            },year, month, day);
//            dialog.show();
//        }
//        if(v == eTo)
//        {
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int y, int m, int d) {
//                    String date = "";
//                    if(m > 8)
//                    {
//                        date = d+"/"+(m+1)+"/"+y;
//                    } else{
//                        date = d+"/0"+(m+1)+"/"+y;
//                    }
//
//                    if(d < 10) date = "0"+date;
//                    eTo.setText(date);
//                }
//            },year, month, day);
//            dialog.show();
//        }
        if(v == btSearch)
        {
            String s = "";
            if(cbAnd.isChecked()) s += " android";
            if(cbIos.isChecked()) s += " ios";
            if(cbWeb.isChecked()) s += " web";
            if(!s.isEmpty())
            {
                List<Item> list = db.seachByKiNang(s);
//                List<ThongKe> list2 = getListTke(list);
                adapter.setList(list);
//                adapterTKe.setList(list2);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> list = db.getAll();
        adapter.setList(list);
//        List<ThongKe> list2 = getListTke(list);
//        adapterTKe.setList(list2);
    }

    @Override
    public void onItemClick(View view, int position) {
            Item item = adapter.getItem(position);
            Intent t = new Intent(getActivity(), UpdateDeleteMainActivity.class);
            t.putExtra("item", item);
            startActivity(t);
    }
}