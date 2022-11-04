package com.example.quanlisinhvien;

public class SinhVien {
    private  String tenSV,lopSV;
    private int id;

    public SinhVien(String tenSV, String lopSV) {
        this.tenSV = tenSV;
        this.lopSV = lopSV;
    }

    public SinhVien(String tenSV, String lopSV, int id) {
        this.tenSV = tenSV;
        this.lopSV = lopSV;
        this.id = id;
    }

    public SinhVien(String lopSV) {
        this.lopSV = lopSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getLopSV() {
        return lopSV;
    }

    public void setLopSV(String lopSV) {
        this.lopSV = lopSV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SinhVien(int id,String lopSV) {
        this.lopSV = lopSV;
        this.id = id;
    }
}
