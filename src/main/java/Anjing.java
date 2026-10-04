package com.mycompany.smartpetshop2;

public class Anjing extends Hewan {
    private String ukuranTubuh; 

    public Anjing(String nama, String ras, int umur, String ukuranTubuh) {
        super(nama, ras, umur); 
        this.ukuranTubuh = ukuranTubuh;
    }

    public String getUkuranTubuh() {
        return this.ukuranTubuh;
    }

    public void setUkuranTubuh(String ukuranTubuh) {
        this.ukuranTubuh = ukuranTubuh;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo(); 
        System.out.printf(" | Ukuran Tubuh  : %s%n", this.ukuranTubuh);
    }

    @Override
    public void bersuara() {
        System.out.println("-> Suara: Guk... Guk...!");
    }
}
