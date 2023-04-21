package edu.ptit.de4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.app.AlertDialog;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import edu.ptit.de4.database.SQLiteHelper;
import edu.ptit.de4.model.Item;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner spTacGia;
    private EditText eTenSach, eTacGia;
    private RadioButton radTra, radHoc;
    private CheckBox cbCN, cbVT, cbDT;
    private Button btAdd, btCancel;
    private RatingBar rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btAdd.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void initView() {
        spTacGia = findViewById(R.id.spTacGia);
        eTenSach = findViewById(R.id.tvTenSach);
        radTra = findViewById(R.id.radTra);
        radHoc = findViewById(R.id.radHoc);
        cbCN = findViewById(R.id.cbCN);
        cbVT= findViewById(R.id.cbVT);
        cbDT = findViewById(R.id.cbDT);
        rate = findViewById(R.id.rateDanhGia);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        spTacGia.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.DSTacGia)));
    }


    @Override
    public void onClick(View v) {
        if(v == btAdd){
            String tenSach = eTenSach.getText().toString();
            String tacGia = spTacGia.getSelectedItem().toString();
            String nganh = "";
            if(cbCN.isChecked()) nganh += " CNTT";
            if(cbVT.isChecked()) nganh += " VT";
            if(cbDT.isChecked()) nganh += " DT";
            String phamVi = "";
            if(radTra.isChecked()) {
                phamVi = "Tra cuu";
            }
            else {
                phamVi = "Hoc";
            }
            Float rating = rate.getRating();
            Item i = new Item(tenSach, tacGia, phamVi, nganh, rating);
            SQLiteHelper db = new SQLiteHelper(this);
            db.addItem(i);
            finish();
        }
        // btCancel
        else
        {
            finish();
        }

    }
}