package com.mycompany.smartpetshop2;
public class Hewan {
    // Atribut privat (Enkapsulasi)
    private String nama;
    private String ras;
    private int umur; 

    // Variabel static untuk menghitung total objek
    public static int totalHewanTerdaftar = 0;

    // Constructor utama
    public Hewan(String nama, String ras, int umur) {
        this.nama = nama;
        this.ras = ras;
        this.umur = umur;
        totalHewanTerdaftar++; 
    }

    // --- GETTER DAN SETTER ---
    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRas() {
        return this.ras;
    }

    public void setRas(String ras) {
        this.ras = ras;
    }

    public int getUmur() {
        return this.umur;
    }

    public void setUmur(int umur) {
        if (umur >= 0) { 
            this.umur = umur;
        } else {
            System.out.println("Umur tidak valid!");
        }
    }

    // --- METHOD LAYANAN ---
    public void tampilkanInfo() {
        System.out.printf("Nama: %-15s | Ras: %-12s | Umur: %d bulan", this.nama, this.ras, this.umur);
    }

    public void bersuara() {
        System.out.println("Hewan ini mengeluarkan suara khasnya.");
    }
}
