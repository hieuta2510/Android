package edu.ptit.de1;

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

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner sp;
    private EditText eTen, eNoiDung, eNgay;
    private CheckBox cbHopTac;
    private Button btAdd, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btAdd.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        eNgay.setOnClickListener(this);
    }

    private void initView() {
        sp = findViewById(R.id.spCategory);
        eTen = findViewById(R.id.tvTen);
        eNoiDung = findViewById(R.id.tvNoiDung);
        eNgay = findViewById(R.id.tvNgay);
        cbHopTac = findViewById(R.id.cbHopTac);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.tinhTrang)));
    }


    @Override
    public void onClick(View v) {
        if(v == eNgay){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        else if(v == btAdd){
            String ten = eTen.getText().toString();
            String noiDung = eNoiDung.getText().toString();
            String tinhTrang = sp.getSelectedItem().toString();
            String ngay= eNgay.getText().toString();
            boolean hopTac = false;
            if (cbHopTac.isChecked()) hopTac = true;
            if(!ten.isEmpty() && noiDung.matches("\\d+"));
            Item i = new Item(ten, noiDung, ngay, tinhTrang, hopTac);
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