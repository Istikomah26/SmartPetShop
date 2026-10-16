package com.mycompany.smartpetshop2;
import java.util.Locale;

// Anjing: extends Hewan + MULTIPLE IMPLEMENTS (DapatDimandikan dan DapatDilatih)
public class Anjing extends Hewan implements DapatDimandikan, DapatDilatih {
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

    // --- Override abstract method dari Hewan ---
    @Override
    public void bersuara() {
        System.out.println("-> Suara: Guk... Guk...!");
    }

    @Override
    public void perawatan() {
        System.out.println("-> Perawatan: Mengajak anjing berjalan-jalan dan memandikannya.");
    }

    @Override
    public double hitungBiayaPenitipan(int hari) {
        // Anjing berukuran besar butuh kandang lebih luas, jadi tarifnya lebih mahal
        double tarif = this.ukuranTubuh.equalsIgnoreCase("Besar") ? 50000 : 40000;
        double total = tarif * hari;
        System.out.printf(Locale.forLanguageTag("id-ID"), "-> Biaya penitipan anjing %s (%s) selama %d hari (Rp%,.0f/hari): Rp%,.0f%n",
                getNama(), this.ukuranTubuh, hari, tarif, total);
        return total;
    }

    // --- Override method dari interface DapatDimandikan ---
    @Override
    public void mandikan() {
        System.out.println("-> Mandi: " + getNama() + " dimandikan dengan sampo anjing dan disikat bulunya sampai bersih.");
    }

    // --- Override method dari interface DapatDilatih ---
    @Override
    public void latih() {
        System.out.println("-> Latihan: " + getNama() + " dilatih perintah duduk, diam, dan salam selama "
                + DURASI_LATIHAN_MENIT + " menit.");
    }
}