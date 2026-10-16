package com.mycompany.smartpetshop2;

// INTERFACE 2 (Modul 7): kontrak perilaku "bisa dilatih"
public interface DapatDilatih {
    // Konstanta interface: otomatis public static final (nilai tidak bisa diubah)
    int DURASI_LATIHAN_MENIT = 30;

    // Otomatis public abstract (tanpa isi)
    void latih();
}