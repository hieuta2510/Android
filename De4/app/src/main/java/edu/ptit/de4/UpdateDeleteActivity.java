package edu.ptit.de4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;



import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import edu.ptit.de4.database.SQLiteHelper;


import edu.ptit.de4.model.Item;


import android.widget.RatingBar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner spTacGia;
    private EditText eTenSach, eTacGia;
    private RadioButton radTra, radHoc;
    private CheckBox cbCN, cbVT, cbDT;
    private Button btUpdate, btRemove, btBack;
    private RatingBar rate;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btBack.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        eTenSach.setText(item.getTenSach());
        if(item.getPhamVi().equals("Tra cuu")) radTra.setChecked(true);
        else radHoc.setChecked(true);
        String doiTuong = item.getDoiTuong();
        String[] str = doiTuong.trim().split(" ");
        for(String i : str)
        {
            if(i.equals("CNTT"))
            {
                cbCN.setChecked(true);
            }
            if(i.equals("VT"))
            {
                cbVT.setChecked(true);
            }
            if(i.equals("DT")) {
                cbDT.setChecked(true);
            }
        }
        int p = 0;
        String  it = item.getTacGia();
        for(int i = 0; i < spTacGia.getCount();i++)
        {
            if(it.equals(spTacGia.getItemAtPosition(i).toString()))
            {
                p = i;
                break;
            }
        }
        spTacGia.setSelection(p);
        rate.setRating(item.getDanhGia());
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
        btUpdate = findViewById(R.id.btUpdate);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        spTacGia.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.DSTacGia)));
    }


    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(v == btUpdate){
            int id = item.getId();
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
            Item i = new Item(id, tenSach, tacGia, phamVi, nganh, rating);
            db.update(i);
            finish();
        }
        // btCancel
        else if(v == btRemove)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban muon xoa " +item.getTenSach()+" khong?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.delete(item);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else
        {
            finish();
        }
    }
}