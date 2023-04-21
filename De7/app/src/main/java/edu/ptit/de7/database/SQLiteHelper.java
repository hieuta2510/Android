package edu.ptit.de1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.ptit.de1.model.Item;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "de1.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT, noiDung TEXT, ngay TEXT, tinhTrang TEXT, hopTac INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //getAll order by date desc
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "ngay DESC";
        Cursor rs = sqLiteDatabase.query("items",
                null, null, null,
                null, null, order);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String ten = rs.getString(1);
            String noiDung = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhTrang = rs.getString(4);
            int hopTac = rs.getInt(5);
            boolean hopTac2 = false;
            if(hopTac == 1)  hopTac2 = true;
            list.add(new Item(id,ten,noiDung,ngay,tinhTrang, hopTac2));
        }
        return list;
    }

    public long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("ten", i.getTen());
        values.put("noiDung", i.getNoiDung());
        values.put("ngay", i.getNgay());
        values.put("tinhTrang", i.getTinhTrang());
        boolean hopTac = i.isHopTac();
        int hopTac2 = 0;
        if(hopTac == true) hopTac2 = 1;
        values.put("hopTac", hopTac2);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items",null, values);
    }

//    public List<Item> getByDate(String date) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date like ?";
//        String[] whereArgs = {date};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while (rs != null && rs.moveToNext()) {
//            int id= rs.getInt(0);
//            String title = rs.getString(1);
//            String c = rs.getString(2);
//            String p = rs.getString(3);
//            list.add(new Item(id,title,c,p,date));
//        }
//        return list;
//    }

    public int update(Item i)
    {
        ContentValues values = new ContentValues();
        values.put("ten", i.getTen());
        values.put("noiDung", i.getNoiDung());
        values.put("ngay", i.getNgay());
        values.put("tinhTrang", i.getTinhTrang());
        boolean hopTac = i.isHopTac();
        int hopTac2 = 0;
        if(hopTac == true) hopTac2 = 1;
        values.put("hopTac", hopTac2);
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
        String whereClause = "ten like ?";
        String[] whereArgs = {"%" +key + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",
                null, whereClause, whereArgs,
                null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id= rs.getInt(0);
            String ten = rs.getString(1);
            String noiDung = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhTrang = rs.getString(4);
            int hopTac = rs.getInt(5);
            boolean hopTac2 = false;
            if(hopTac == 1) hopTac2 = true;
            list.add(new Item(id,ten,noiDung,ngay,tinhTrang, hopTac2));
        }
        return list;
    }
    public List<Item> searchByTinhTrang(String key) {
        List<Item> list = new ArrayList<>();
        String whereClause = "tinhTrang like ? ";
        String[] whereArgs = {key};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",
                null, whereClause, whereArgs,
                null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id= rs.getInt(0);
            String ten = rs.getString(1);
            String noiDung = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhTrang = rs.getString(4);
            int hopTac = rs.getInt(5);
            boolean hopTac2 = false;
            if(hopTac == 1) hopTac2 = true;
            list.add(new Item(id,ten,noiDung,ngay,tinhTrang, hopTac2));
        }
        return list;
    }

    public List<Item> searchByDateFromTo(String from, String to) {
        List<Item> list = new ArrayList<>();
        String whereClause = "ngay BETWEEN ? AND ?";
        String[] whereArgs = {from.trim(), to.trim()};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items",
                null, whereClause, whereArgs,
                null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noiDung = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhTrang = rs.getString(4);
            int hopTac = rs.getInt(5);
            boolean hopTac2 = false;
            if (hopTac == 1) hopTac2 = true;
            list.add(new Item(id, ten, noiDung, ngay, tinhTrang, hopTac2));
        }
            return list;
    }
}