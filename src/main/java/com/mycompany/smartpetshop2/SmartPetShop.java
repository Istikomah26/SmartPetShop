package com.mycompany.smartpetshop2;
import java.util.Scanner; // Memanggil modul Scanner bawaan Java

public class SmartPetShop {

    // Penerapan Method Overloading 1: Cari berdasarkan Nama (String)
    public static void cariHewan(String nama, Hewan[] daftarHewan, int jumlahHewan) {
        System.out.println("\n[Hasil Pencarian Nama: " + nama + "]");
        boolean ditemukan = false;
        for (int i = 0; i < jumlahHewan; i++) {
            if (daftarHewan[i].getNama().equalsIgnoreCase(nama)) {
                System.out.print("- ");
                daftarHewan[i].tampilkanInfo();
                daftarHewan[i].bersuara();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Hewan dengan nama '" + nama + "' tidak ditemukan.");
    }

    // Penerapan Method Overloading 2: Cari berdasarkan Umur Maksimal (int)
    public static void cariHewan(int umurMax, Hewan[] daftarHewan, int jumlahHewan) {
        System.out.println("\n[Hasil Pencarian Hewan Berumur <= " + umurMax + " bulan]");
        boolean ditemukan = false;
        for (int i = 0; i < jumlahHewan; i++) {
            if (daftarHewan[i].getUmur() <= umurMax) {
                System.out.print("- ");
                daftarHewan[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Tidak ada hewan dengan kriteria umur tersebut.");
    }

    public static void main(String[] args) {
        // Inisialisasi Scanner interaktif dan Array Kontainer Objek
        Scanner scanner = new Scanner(System.in);
        Hewan[] daftarHewan = new Hewan[15]; // Sanggup menampung maksimal 15 hewan
        int jumlahHewan = 0;
        boolean isRunning = true;

        // Ucapan selamat datang utama program
        System.out.println("=============================================");
        System.out.println("       SELAMAT DATANG DI SMART PETSHOP       ");
        System.out.println("=============================================");

        // Struktur Kontrol Perulangan Dinamis (while loop)
        while (isRunning) {
            System.out.println("\n--- MENU UTAMA SMART PETSHOP ---");
            System.out.println("1. Tambah Data Hewan Baru");
            System.out.println("2. Tampilkan Seluruh Koleksi Hewan");
            System.out.println("3. Cari Hewan (Fitur Overloading)");
            System.out.println("4. Keluar Sistem");
            System.out.print("Pilih Menu (1-4): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan sisa buffer enter pada keyboard

            // Struktur Kontrol Percabangan switch-case
            switch (pilihan) {
                case 1:
                    if (jumlahHewan < daftarHewan.length) {
                        System.out.println("\n-- Pilih Jenis Hewan --");
                        System.out.println("1. Kucing");
                        System.out.println("2. Anjing");
                        System.out.print("Pilihan (1/2): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan Nama Hewan: ");
                        String namaBaru = scanner.nextLine();
                        System.out.print("Masukkan Ras Hewan : ");
                        String rasBaru = scanner.nextLine();
                        System.out.print("Masukkan Umur (bulan): ");
                        int umurBaru = scanner.nextInt();
                        scanner.nextLine();

                        // Percabangan if-else untuk menentukan instansiasi objek subclass
                        if (jenis == 1) {
                            System.out.print("Tingkat Keaktifan Kucing: ");
                            String aktif = scanner.nextLine();
                            daftarHewan[jumlahHewan] = new Kucing(namaBaru, rasBaru, umurBaru, aktif);
                        } else if (jenis == 2) {
                            System.out.print("Ukuran Tubuh Anjing     : ");
                            String ukuran = scanner.nextLine();
                            daftarHewan[jumlahHewan] = new Anjing(namaBaru, rasBaru, umurBaru, ukuran);
                        } else {
                            System.out.println("Pilihan tidak valid, proses dibatalkan.");
                            break;
                        }

                        jumlahHewan++;
                        System.out.println("Sukses! Data hewan peliharaan berhasil disimpan ke sistem.");
                    } else {
                        System.out.println("Maaf, kapasitas penitipan/rak PetShop sudah penuh!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- DAFTAR HEWAN DI PETSHOP ---");
                    if (jumlahHewan == 0) {
                        System.out.println("Belum ada data hewan yang terdaftar.");
                    } else {
                        // Perulangan for untuk pengaksesan tiap elemen array objek
                        for (int i = 0; i < jumlahHewan; i++) {
                            System.out.print((i + 1) + ". ");
                            daftarHewan[i].tampilkanInfo(); // Memanggil method hasil overriding
                            daftarHewan[i].bersuara();      // Memanggil method hasil overriding
                        }
                        // Pemanggilan variabel static tingkat Class
                        System.out.println("\n* Total Akumulasi Hewan Sukses Terdaftar: " + Hewan.totalHewanTerdaftar);
                    }
                    System.out.print("\nTekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println("\n-- Fitur Cari Hewan (Overloading) --");
                    System.out.println("1. Cari Berdasarkan Nama");
                    System.out.println("2. Cari Berdasarkan Batas Umur Maksimal");
                    System.out.print("Pilihan (1/2): ");
                    int modeCari = scanner.nextInt();
                    scanner.nextLine();

                    if (modeCari == 1) {
                        System.out.print("Masukkan Nama Hewan yang dicari: ");
                        String kunciNama = scanner.nextLine();
                        cariHewan(kunciNama, daftarHewan, jumlahHewan); // Memanggil overloading String
                    } else if (modeCari == 2) {
                        System.out.print("Masukkan Batas Umur Maksimal (bulan): ");
                        int kunciUmur = scanner.nextInt();
                        scanner.nextLine();
                        cariHewan(kunciUmur, daftarHewan, jumlahHewan); // Memanggil overloading int
                    } else {
                        System.out.println("Pilihan mode tidak valid.");
                    }
                    System.out.print("\nTekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("\nTerima kasih telah menggunakan aplikasi Smart PetShop!");
                    isRunning = false; // Menghentikan status while loop
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
                    break;
            }
        }
        scanner.close(); // Menutup penampung input keyboard untuk keamanan resource program
    }
}
