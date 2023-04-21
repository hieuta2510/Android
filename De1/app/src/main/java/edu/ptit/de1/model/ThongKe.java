package edu.ptit.de1.model;

public class ThongKe implements Comparable <ThongKe> {
    String tinhTrangTK;
    int soLuong;

    public ThongKe() {
    }

    public ThongKe(String tinhTrang, int soLuong) {
        this.tinhTrangTK = tinhTrang;
        this.soLuong = soLuong;
    }

    @Override
    public int compareTo(ThongKe tk2)
    {
        if(this.soLuong < tk2.soLuong) return 1;
        else if(this.soLuong > tk2.soLuong) return -1;
        return 0;
    }

    public String getTinhTrangTK() {
        return tinhTrangTK;
    }

    public void setTinhTrangTK(String tinhTrangTK) {
        this.tinhTrangTK = tinhTrangTK;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}