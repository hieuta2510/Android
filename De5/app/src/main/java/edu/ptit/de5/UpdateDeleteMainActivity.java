package edu.ptit.de5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.ptit.de5.database.SQLiteHelper;
import edu.ptit.de5.model.Item;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import edu.ptit.de5.adapter.ViewPagerAdapter;
import edu.ptit.de5.database.SQLiteHelper;
import edu.ptit.de5.model.Item;

public class UpdateDeleteMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eHoTen, eDienThoai, eNamSinh;
    private CheckBox cbAndroid, cbIos, cbWeb;
    private Button btUpdate, btRemove, btBack;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_main);
        initView();
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btBack.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        eHoTen.setText(item.getHoTen());
        eNamSinh.setText(item.getNamSinh());
        eDienThoai.setText(item.getDienThoai());
        String kiNang = item.getKiNang();
        String[] str = kiNang.trim().split(" ");
        for(String i : str)
        {
            if(i.equals("android"))
            {
                cbAndroid.setChecked(true);
            }
            if(i.equals("ios"))
            {
                cbIos.setChecked(true);
            }
            if(i.equals("web")) {
                cbWeb.setChecked(true);
            }
        }
        int p = 0;
        int it = item.getGioiTinh();
        for(int i = 0; i < sp.getCount();i++)
        {
            if(i == it)
            {
                p = i;
                break;
            }
        }
        sp.setSelection(p);
    }

    private void initView() {
        sp = findViewById(R.id.spGioiTinh);
        eHoTen = findViewById(R.id.tvhoTen);
        eDienThoai = findViewById(R.id.tvDienThoai);
        eNamSinh = findViewById(R.id.tvNamSinh);
        cbAndroid = findViewById(R.id.cbAndroid);
        cbIos= findViewById(R.id.cbIos);
        cbWeb = findViewById(R.id.cbWeb);
        btUpdate = findViewById(R.id.btUpdate);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.gioiTinh)));
    }


    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(v == btUpdate)
        {
            int id = item.getId();
            String hoTen = eHoTen.getText().toString();
            String namSinh = eNamSinh.getText().toString();
            String gioiTinh = sp.getSelectedItem().toString();
            String sdt= eDienThoai.getText().toString();
            String kiNang = "";
            if(cbAndroid.isChecked()) kiNang += " android";
            if(cbIos.isChecked()) kiNang += " ios";
            if(cbWeb.isChecked()) kiNang += " web";
            int gt = 0;
            if (gioiTinh.equals("Nu")) gt = 1;
            else if(gioiTinh.equals("Manh")) gt = 2;
            if(Integer.parseInt(namSinh) >=  1980 && Integer.parseInt((namSinh)) <= 1995)
            {
                Item i = new Item(id, hoTen, sdt, namSinh, kiNang, gt);
                db.update(i);
                finish();
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Loi");
                builder.setMessage("Nam sinh khong hop le(1980 - 1995)");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
        //btRemove
        else if(v == btRemove)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban muon xoa " +item.getHoTen()+" khong?");
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