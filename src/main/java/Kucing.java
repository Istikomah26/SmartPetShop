package com.mycompany.smartpetshop2;
public class Kucing extends Hewan {
    private String tingkatKeaktifan; 

    public Kucing(String nama, String ras, int umur, String tingkatKeaktifan) {
        super(nama, ras, umur); 
        this.tingkatKeaktifan = tingkatKeaktifan;
    }

    public String getTingkatKeaktifan() {
        return this.tingkatKeaktifan;
    }

    public void setTingkatKeaktifan(String tingkatKeaktifan) {
        this.tingkatKeaktifan = tingkatKeaktifan;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo(); 
        System.out.printf(" | Karakteristik: %s%n", this.tingkatKeaktifan);
    }

    @Override
    public void bersuara() {
        System.out.println("-> Suara: Meong... Meong...!");
    }
}
