package edu.ptit.de5.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.ptit.de5.model.Item;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "de5.db";
    private static int DATABASE_VERSION = 3;

    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT, dienThoai TEXT, namSinh TEXT, kiNang TEXT, gioiTinh INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "ALTER TABLE items RENAME COLUMN gioTinh TO gioiTinh;\n";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //getAll order by date desc
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "namSinh DESC";
        Cursor rs = sqLiteDatabase.query("items",
                null, null, null,
                null, null, order);

        while (rs != null && rs.moveToNext()) {
            int id= rs.getInt(0);
            String hoTen = rs.getString(1);
            String dienThoai = rs.getString(2);
            String namSinh = rs.getString(3);
            String kiNang = rs.getString(4);
            int gioiTinh = rs.getInt(5);
            list.add(new Item(id,hoTen,dienThoai,namSinh,kiNang, gioiTinh));
        }
        return list;
    }

    public long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("hoTen", i.getHoTen());
        values.put("dienThoai", i.getDienThoai());
        values.put("namSinh", i.getNamSinh());
        values.put("kiNang", i.getKiNang());
        values.put("gioiTinh", i.getGioiTinh());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items",null, values);
    }


    public int update(Item i)
    {
        ContentValues values = new ContentValues();
        values.put("hoTen", i.getHoTen());
        values.put("dienThoai", i.getDienThoai());
        values.put("namSinh", i.getNamSinh());
        values.put("kiNang", i.getKiNang());
        values.put("gioiTinh", i.getGioiTinh());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = " id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("items", values, whereClause, whereArgs);
    }

    public int delete(Item i)
    {
        String whereClause = " id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items", whereClause, whereArgs);
    }

    public List<Item> searchByTen(String key) {
        List<Item> list = new ArrayList<>();
        String whereClause = "hoTen like ?";
        String[] whereArgs = {"%" +key + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",
                null, whereClause, whereArgs,
                null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id= rs.getInt(0);
            String hoTen = rs.getString(1);
            String dienThoai = rs.getString(2);
            String namSinh = rs.getString(3);
            String kiNang = rs.getString(4);
            int gioiTinh = rs.getInt(5);
            list.add(new Item(id,hoTen,dienThoai,namSinh,kiNang, gioiTinh));
        }
        return list;
    }
    public List<Item> searchByTinhTrang(String key) {
        List<Item> list = new ArrayList<>();
        String whereClause = "gioiTinh like ?";
        String[] whereArgs = {key};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",
                null, whereClause, whereArgs,
                null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id= rs.getInt(0);
            String hoTen = rs.getString(1);
            String dienThoai = rs.getString(2);
            String namSinh = rs.getString(3);
            String kiNang = rs.getString(4);
            int gioiTinh = rs.getInt(5);
            list.add(new Item(id,hoTen,dienThoai,namSinh,kiNang, gioiTinh));
        }
        return list;
    }


    public List<Item> seachByKiNang(String key) {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String[] str = key.trim().split(" ");
        if (str.length == 1)
        {
            String whereClause = " kiNang LIKE ? ";
            String[] whereArgs = {"%" + str[0] + "%"};
            Cursor rs = st.query("items",
                    null, whereClause, whereArgs,
                    null, null, null);
            while (rs != null && rs.moveToNext()) {
                int id = rs.getInt(0);
                String hoTen = rs.getString(1);
                String dienThoai = rs.getString(2);
                String namSinh = rs.getString(3);
                String kiNang = rs.getString(4);
                int gioiTinh = rs.getInt(5);
                list.add(new Item(id, hoTen, dienThoai, namSinh, kiNang, gioiTinh));
            }

        }
        else if(str.length == 2)
        {
            String whereClause = " kiNang LIKE ? AND kiNang LIKE ? ";
            String[] whereArgs = {"%" + str[0] + "%","%" + str[1] + "%"};
            Cursor rs = st.query("items",
                    null, whereClause, whereArgs,
                    null, null, null);
            while (rs != null && rs.moveToNext()) {
                int id = rs.getInt(0);
                String hoTen = rs.getString(1);
                String dienThoai = rs.getString(2);
                String namSinh = rs.getString(3);
                String kiNang = rs.getString(4);
                int gioiTinh = rs.getInt(5);
                list.add(new Item(id, hoTen, dienThoai, namSinh, kiNang, gioiTinh));
            }
        }
        else if(str.length == 3) {
            String  whereClause = " kiNang LIKE ? AND kiNang LIKE ? AND kiNang LIKE ? ";
            String[] whereArgs = {"%" + str[0] + "%", "%" + str[1] + "%" , "%" + str[2] + "%"};
            Cursor rs = st.query("items",
                    null, whereClause, whereArgs,
                    null, null, null);
            while (rs != null && rs.moveToNext()) {
                int id = rs.getInt(0);
                String hoTen = rs.getString(1);
                String dienThoai = rs.getString(2);
                String namSinh = rs.getString(3);
                String kiNang = rs.getString(4);
                int gioiTinh = rs.getInt(5);
                list.add(new Item(id, hoTen, dienThoai, namSinh, kiNang, gioiTinh));
            }
        }
        return list;
    }
}