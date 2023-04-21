
package edu.ptit.de5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner sp;
    private EditText eHoTen, eDienThoai, eNamSinh;
    private CheckBox cbAndroid, cbIos, cbWeb;
    private Button btAdd, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btAdd.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void initView() {
        sp = findViewById(R.id.spGioiTinh);
        eHoTen = findViewById(R.id.tvhoTen);
        eDienThoai = findViewById(R.id.tvDienThoai);
        eNamSinh = findViewById(R.id.tvNamSinh);
        cbAndroid = findViewById(R.id.cbAndroid);
        cbIos= findViewById(R.id.cbIos);
        cbWeb = findViewById(R.id.cbWeb);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.gioiTinh)));
    }


    @Override
    public void onClick(View v) {
        if(v == btAdd){
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
                Item i = new Item(hoTen, sdt, namSinh, kiNang, gt);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
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
        // btCancel
        else
        {
            finish();
        }

    }
}