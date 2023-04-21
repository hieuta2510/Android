package edu.ptit.de1.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String ten, noiDung, ngay, tinhTrang;
    private boolean hopTac;

    public Item() {
    }

    public Item(int id, String ten, String noiDung, String ngay, String tinhTrang, boolean hopTac) {
        this.id = id;
        this.ten = ten;
        this.noiDung = noiDung;
        this.ngay = ngay;
        this.tinhTrang = tinhTrang;
        this.hopTac = hopTac;
    }

    public Item(String ten, String noiDung, String ngay, String tinhTrang, boolean hopTac) {
        this.ten = ten;
        this.noiDung = noiDung;
        this.ngay = ngay;
        this.tinhTrang = tinhTrang;
        this.hopTac = hopTac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public boolean isHopTac() {
        return hopTac;
    }

    public void setHopTac(boolean hopTac) {
        this.hopTac = hopTac;
    }
}