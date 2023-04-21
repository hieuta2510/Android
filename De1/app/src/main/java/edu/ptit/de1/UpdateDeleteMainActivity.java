package edu.ptit.de1;

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

import edu.ptit.de1.database.SQLiteHelper;
import edu.ptit.de1.model.Item;


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

import edu.ptit.de1.adapter.ViewPagerAdapter;
import edu.ptit.de1.database.SQLiteHelper;
import edu.ptit.de1.model.Item;

public class UpdateDeleteMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eTen, eNoiDung, eNgay;
    private CheckBox cbHopTac;
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
        eNgay.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        eTen.setText(item.getTen());
        eNoiDung.setText(item.getNoiDung());
        eNgay.setText(item.getNgay());
        cbHopTac.setChecked(item.isHopTac());
        int p = 0;
        String cate = item.getTinhTrang();
        for(int i = 0; i < sp.getCount();i++)
        {
            if(sp.getItemAtPosition(i).toString().equals(item.getTinhTrang()))
            {
                p = i;
            }
        }

        sp.setSelection(p);
    }

    private void initView() {
        sp = findViewById(R.id.spCategory);
        eTen = findViewById(R.id.tvTen);
        eNoiDung = findViewById(R.id.tvNoiDung);
        eNgay = findViewById(R.id.tvNgay);
        cbHopTac = findViewById(R.id.cbHopTac);
        btUpdate = findViewById(R.id.btUpdate);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.tinhTrang)));
    }


    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(v == eNgay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteMainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int y, int m, int d) {
                    String date = "";
                    if(m > 8)
                    {
                        date = d+"/"+(m+1)+"/"+y;
                    } else{
                        date = d+"/0"+(m+1)+"/"+y;
                    }

                    if(d < 10) date = "0"+date;
                    eNgay.setText(date);
                }
            },year, month, day);
            dialog.show();
        }
        else if(v == btUpdate)
        {
            int id = item.getId();
            String ten = eTen.getText().toString();
            String noiDung = eNoiDung.getText().toString();
            String tinhTrang = sp.getSelectedItem().toString();
            String ngay= eNgay.getText().toString();
            boolean hopTac = cbHopTac.isChecked();
            if(!ten.isEmpty() && noiDung.matches("\\d+"));
            Item i = new Item(id, ten, noiDung, ngay, tinhTrang, hopTac);
            db.update(i);
            finish();
        }
        //btRemove
        else if(v == btRemove)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban muon xoa " +item.getTen()+" khong?");
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