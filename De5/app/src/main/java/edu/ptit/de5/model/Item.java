package edu.ptit.de5.model;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {
    private int id;
    private String hoTen, dienThoai, namSinh;
    private String kiNang;
    private int gioiTinh;

    public Item() {
    }

    public Item(int id, String hoTen, String dienThoai, String namSinh, String kiNang, int gioiTinh) {
        this.id = id;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.namSinh = namSinh;
        this.kiNang = kiNang;
        this.gioiTinh = gioiTinh;
    }

    public Item(String hoTen, String dienThoai, String namSinh, String kiNang, int gioiTinh) {
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.namSinh = namSinh;
        this.kiNang = kiNang;
        this.gioiTinh = gioiTinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getKiNang() {
        return kiNang;
    }

    public void setKiNang(String kiNang) {
        this.kiNang = kiNang;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}