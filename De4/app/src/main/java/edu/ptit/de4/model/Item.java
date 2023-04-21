package edu.ptit.de4.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String tenSach, tacGia, phamVi, doiTuong;
    private Float danhGia;

    public Item() {
    }

    public Item(String tenSach, String tacGia, String phamVi, String doiTuong, Float danhGia) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.phamVi = phamVi;
        this.doiTuong = doiTuong;
        this.danhGia = danhGia;
    }

    public Item(int id, String tenSach, String tacGia, String phamVi, String doiTuong, Float danhGia) {
        this.id = id;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.phamVi = phamVi;
        this.doiTuong = doiTuong;
        this.danhGia = danhGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getPhamVi() {
        return phamVi;
    }

    public void setPhamVi(String phamVi) {
        this.phamVi = phamVi;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public Float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(Float danhGia) {
        this.danhGia = danhGia;
    }
}