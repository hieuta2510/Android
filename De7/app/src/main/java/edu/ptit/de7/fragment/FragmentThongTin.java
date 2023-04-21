package edu.ptit.de1.fragment;


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
import java.util.Date;
import java.util.List;

import edu.ptit.de1.R;
import edu.ptit.de1.UpdateDeleteMainActivity;
import edu.ptit.de1.adapter.RecycleViewAdapter;
import edu.ptit.de1.database.SQLiteHelper;
import edu.ptit.de1.model.Item;

public class FragmentThongTin extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thong_tin, container, false);
    }

}