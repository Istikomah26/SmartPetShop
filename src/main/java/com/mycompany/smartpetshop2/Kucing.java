package com.mycompany.smartpetshop2;
import java.util.Locale;

// Kucing: extends Hewan + implements 1 interface (DapatDimandikan)
public class Kucing extends Hewan implements DapatDimandikan {
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

    // --- Override abstract method dari Hewan ---
    @Override
    public void bersuara() {
        System.out.println("-> Suara: Meong... Meong...!");
    }

    @Override
    public void perawatan() {
        System.out.println("-> Perawatan: Menyisir bulu dan membersihkan litter box kucing.");
    }

    @Override
    public double hitungBiayaPenitipan(int hari) {
        double total = 25000 * hari;
        System.out.printf(Locale.forLanguageTag("id-ID"), "-> Biaya penitipan kucing %s selama %d hari (Rp25.000/hari): Rp%,.0f%n",
                getNama(), hari, total);
        return total;
    }

    // --- Override method dari interface DapatDimandikan ---
    @Override
    public void mandikan() {
        System.out.println("-> Mandi: " + getNama() + " dimandikan dengan sampo khusus kucing, lalu dikeringkan dengan handuk lembut.");
    }
}