package com.mycompany.smartpetshop2;
import java.util.Locale;

// Kelinci: extends Hewan + implements 1 interface (DapatDilatih)
public class Kelinci extends Hewan implements DapatDilatih {
    private String jenisBulu;

    public Kelinci(String nama, String ras, int umur, String jenisBulu) {
        super(nama, ras, umur);
        this.jenisBulu = jenisBulu;
    }

    public String getJenisBulu() {
        return this.jenisBulu;
    }

    public void setJenisBulu(String jenisBulu) {
        this.jenisBulu = jenisBulu;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf(" | Jenis Bulu    : %s%n", this.jenisBulu);
    }

    // --- Override abstract method dari Hewan ---
    @Override
    public void bersuara() {
        System.out.println("-> Suara: Cit... Cit... (mendengus pelan)");
    }

    @Override
    public void perawatan() {
        System.out.println("-> Perawatan: Memberi wortel dan rumput segar, lalu membersihkan kandang.");
    }

    @Override
    public double hitungBiayaPenitipan(int hari) {
        double total = 15000 * hari;
        System.out.printf(Locale.forLanguageTag("id-ID"), "-> Biaya penitipan kelinci %s selama %d hari (Rp15.000/hari): Rp%,.0f%n",
                getNama(), hari, total);
        return total;
    }

    // --- Override method dari interface DapatDilatih ---
    @Override
    public void latih() {
        System.out.println("-> Latihan: " + getNama() + " dilatih melewati rintangan kecil dan datang saat dipanggil selama "
                + DURASI_LATIHAN_MENIT + " menit.");
    }
}