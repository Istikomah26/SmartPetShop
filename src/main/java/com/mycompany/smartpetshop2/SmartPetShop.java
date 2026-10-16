package com.mycompany.smartpetshop2;
import java.util.Scanner; // Memanggil modul Scanner bawaan Java

public class SmartPetShop {

    // ===================================================================
    // COMPILE-TIME POLYMORPHISM (Static Binding) - Method Overloading
    // ===================================================================

    // Overloading 1: Cari berdasarkan Nama (String)
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

    // Overloading 2: Cari berdasarkan Umur Maksimal (int)
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

    // Overloading 3: Cari berdasarkan Ras (String) DAN Umur Maksimal (int)
    public static void cariHewan(String ras, int umurMax, Hewan[] daftarHewan, int jumlahHewan) {
        System.out.println("\n[Hasil Pencarian Ras: " + ras + " dan Umur <= " + umurMax + " bulan]");
        boolean ditemukan = false;
        for (int i = 0; i < jumlahHewan; i++) {
            if (daftarHewan[i].getRas().equalsIgnoreCase(ras) && daftarHewan[i].getUmur() <= umurMax) {
                System.out.print("- ");
                daftarHewan[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Tidak ada hewan dengan ras dan umur tersebut.");
    }

    // ===================================================================
    // RUNTIME POLYMORPHISM & DYNAMIC BINDING (parameter bertipe Superclass)
    // ===================================================================
    public static void simulasiPerawatan(Hewan hewan) {
        System.out.println("\n>>> Memulai simulasi perawatan untuk: " + hewan.getNama());
        hewan.tampilkanInfo(); // dynamic binding
        hewan.bersuara();      // dynamic binding (abstract method)
        hewan.perawatan();     // dynamic binding (abstract method)
        System.out.println("<<< Simulasi selesai. (Wujud asli objek: " + hewan.getClass().getSimpleName() + ")");
    }

    // ===================================================================
    // MODUL 7: INTERFACE + PENGECEKAN instanceof
    // Tidak semua Hewan mengimplementasikan interface, jadi wajib dicek
    // dengan instanceof sebelum casting agar pemanggilan method aman.
    // ===================================================================
    public static void layananMandi(Hewan hewan) {
        System.out.println("\n[Layanan Mandi untuk " + hewan.getNama() + "]");
        if (hewan instanceof DapatDimandikan) {
            DapatDimandikan bisaMandi = (DapatDimandikan) hewan; // casting aman
            bisaMandi.mandikan();
        } else {
            System.out.println("-> " + hewan.getNama() + " (" + hewan.getClass().getSimpleName()
                    + ") tidak menyediakan layanan mandi.");
        }
    }

    public static void layananLatihan(Hewan hewan) {
        System.out.println("\n[Layanan Latihan untuk " + hewan.getNama() + "]");
        if (hewan instanceof DapatDilatih) {
            DapatDilatih bisaLatihan = (DapatDilatih) hewan; // casting aman
            bisaLatihan.latih();
        } else {
            System.out.println("-> " + hewan.getNama() + " (" + hewan.getClass().getSimpleName()
                    + ") tidak menyediakan layanan latihan.");
        }
    }

    // Menampilkan daftar singkat lalu meminta pengguna memilih nomor hewan
    // Mengembalikan indeks array (0-based), atau -1 jika pilihan tidak valid
    public static int pilihIndeksHewan(Scanner scanner, Hewan[] daftarHewan, int jumlahHewan) {
        for (int i = 0; i < jumlahHewan; i++) {
            System.out.printf("%d. %s (%s)%n", (i + 1), daftarHewan[i].getNama(),
                    daftarHewan[i].getClass().getSimpleName());
        }
        int nomor = bacaInt(scanner, "Pilih nomor hewan: ");
        if (nomor >= 1 && nomor <= jumlahHewan) {
            return nomor - 1;
        }
        System.out.println("Nomor tidak valid.");
        return -1;
    }

    // Membaca angka dengan aman agar program tidak crash bila input bukan angka
    public static int bacaInt(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            String masukan = scanner.nextLine().trim();
            try {
                return Integer.parseInt(masukan);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat. Coba lagi.");
            }
        }
    }

    public static void main(String[] args) {
        // Inisialisasi Scanner interaktif dan Array Kontainer Objek
        Scanner scanner = new Scanner(System.in);

        // SUPERCLASS REFERENCE: array bertipe Hewan (abstract class) menampung semua subclass
        Hewan[] daftarHewan = new Hewan[15]; // Sanggup menampung maksimal 15 hewan
        int jumlahHewan = 0;
        boolean isRunning = true;

        // Data awal (5 objek, mencakup seluruh variasi subclass) - UPCASTING
        daftarHewan[jumlahHewan++] = new Kucing("Mimi", "Persia", 12, "Sangat Aktif");
        daftarHewan[jumlahHewan++] = new Anjing("Rocky", "Golden", 24, "Besar");
        daftarHewan[jumlahHewan++] = new Kelinci("Bunny", "Holland Lop", 6, "Lebat");
        daftarHewan[jumlahHewan++] = new Kucing("Luna", "Anggora", 8, "Santai");
        daftarHewan[jumlahHewan++] = new Anjing("Bolt", "Pomeranian", 10, "Kecil");

        // Ucapan selamat datang utama program
        System.out.println("=============================================");
        System.out.println("       SELAMAT DATANG DI SMART PETSHOP       ");
        System.out.println("=============================================");
        System.out.println("(" + jumlahHewan + " data awal sudah dimuat otomatis)");

        // Struktur Kontrol Perulangan Dinamis (while loop)
        while (isRunning) {
            System.out.println("\n--- MENU UTAMA SMART PETSHOP ---");
            System.out.println("1. Tambah Data Hewan Baru");
            System.out.println("2. Tampilkan Seluruh Koleksi Hewan");
            System.out.println("3. Cari Hewan (Fitur Overloading)");
            System.out.println("4. Simulasi Perawatan Hewan (Dynamic Binding)");
            System.out.println("5. Layanan Tambahan: Mandi / Latihan (Interface + instanceof)");
            System.out.println("6. Hitung Biaya Penitipan (Abstract Method)");
            System.out.println("7. Keluar Sistem");
            int pilihan = bacaInt(scanner, "Pilih Menu (1-7): ");

            // Struktur Kontrol Percabangan switch-case
            switch (pilihan) {
                case 1:
                    if (jumlahHewan < daftarHewan.length) {
                        System.out.println("\n-- Pilih Jenis Hewan --");
                        System.out.println("1. Kucing");
                        System.out.println("2. Anjing");
                        System.out.println("3. Kelinci");
                        int jenis = bacaInt(scanner, "Pilihan (1/2/3): ");

                        if (jenis < 1 || jenis > 3) {
                            System.out.println("Pilihan tidak valid, proses dibatalkan.");
                            break;
                        }

                        System.out.print("Masukkan Nama Hewan: ");
                        String namaBaru = scanner.nextLine();
                        System.out.print("Masukkan Ras Hewan : ");
                        String rasBaru = scanner.nextLine();
                        int umurBaru = bacaInt(scanner, "Masukkan Umur (bulan): ");
                        if (umurBaru < 0) {
                            System.out.println("Umur tidak valid, proses dibatalkan.");
                            break;
                        }

                        // Upcasting: objek subclass disimpan di array bertipe Hewan
                        if (jenis == 1) {
                            System.out.print("Tingkat Keaktifan Kucing: ");
                            String aktif = scanner.nextLine();
                            daftarHewan[jumlahHewan] = new Kucing(namaBaru, rasBaru, umurBaru, aktif);
                        } else if (jenis == 2) {
                            System.out.print("Ukuran Tubuh Anjing (Besar/Kecil): ");
                            String ukuran = scanner.nextLine();
                            daftarHewan[jumlahHewan] = new Anjing(namaBaru, rasBaru, umurBaru, ukuran);
                        } else {
                            System.out.print("Jenis Bulu Kelinci      : ");
                            String bulu = scanner.nextLine();
                            daftarHewan[jumlahHewan] = new Kelinci(namaBaru, rasBaru, umurBaru, bulu);
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
                        // Perulangan array bertipe Superclass -> DYNAMIC BINDING
                        for (int i = 0; i < jumlahHewan; i++) {
                            System.out.print((i + 1) + ". ");
                            daftarHewan[i].tampilkanInfo(); // versi subclass dipilih saat runtime
                            daftarHewan[i].bersuara();      // versi subclass dipilih saat runtime

                            // Pengecekan instanceof: tampilkan layanan yang didukung tiap hewan
                            String layanan = "";
                            if (daftarHewan[i] instanceof DapatDimandikan) {
                                layanan += "[Bisa Dimandikan] ";
                            }
                            if (daftarHewan[i] instanceof DapatDilatih) {
                                layanan += "[Bisa Dilatih] ";
                            }
                            if (layanan.isEmpty()) {
                                layanan = "(tidak ada layanan tambahan)";
                            }
                            System.out.println("   Layanan tambahan: " + layanan);
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
                    System.out.println("3. Cari Berdasarkan Ras dan Batas Umur Maksimal");
                    int modeCari = bacaInt(scanner, "Pilihan (1/2/3): ");

                    if (modeCari == 1) {
                        System.out.print("Masukkan Nama Hewan yang dicari: ");
                        String kunciNama = scanner.nextLine();
                        cariHewan(kunciNama, daftarHewan, jumlahHewan); // overloading String
                    } else if (modeCari == 2) {
                        int kunciUmur = bacaInt(scanner, "Masukkan Batas Umur Maksimal (bulan): ");
                        cariHewan(kunciUmur, daftarHewan, jumlahHewan); // overloading int
                    } else if (modeCari == 3) {
                        System.out.print("Masukkan Ras Hewan yang dicari: ");
                        String kunciRas = scanner.nextLine();
                        int umurRas = bacaInt(scanner, "Masukkan Batas Umur Maksimal (bulan): ");
                        cariHewan(kunciRas, umurRas, daftarHewan, jumlahHewan); // overloading String + int
                    } else {
                        System.out.println("Pilihan mode tidak valid.");
                    }
                    System.out.print("\nTekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.println("\n--- SIMULASI PERAWATAN HEWAN ---");
                    if (jumlahHewan == 0) {
                        System.out.println("Belum ada data hewan yang terdaftar.");
                    } else {
                        for (int i = 0; i < jumlahHewan; i++) {
                            System.out.printf("%d. %s (%s)%n", (i + 1), daftarHewan[i].getNama(),
                                    daftarHewan[i].getClass().getSimpleName());
                        }
                        System.out.println("0. Simulasikan SEMUA hewan");
                        int nomor = bacaInt(scanner, "Pilih nomor hewan: ");

                        if (nomor == 0) {
                            for (int i = 0; i < jumlahHewan; i++) {
                                simulasiPerawatan(daftarHewan[i]); // parameter Superclass
                            }
                        } else if (nomor >= 1 && nomor <= jumlahHewan) {
                            simulasiPerawatan(daftarHewan[nomor - 1]); // parameter Superclass
                        } else {
                            System.out.println("Nomor tidak valid.");
                        }
                    }
                    System.out.print("\nTekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;

                case 5: {
                    System.out.println("\n--- LAYANAN TAMBAHAN (INTERFACE) ---");
                    if (jumlahHewan == 0) {
                        System.out.println("Belum ada data hewan yang terdaftar.");
                    } else {
                        int idx = pilihIndeksHewan(scanner, daftarHewan, jumlahHewan);
                        if (idx != -1) {
                            System.out.println("\n1. Mandikan Hewan");
                            System.out.println("2. Latih Hewan");
                            int layanan = bacaInt(scanner, "Pilih layanan (1/2): ");
                            if (layanan == 1) {
                                layananMandi(daftarHewan[idx]);
                            } else if (layanan == 2) {
                                layananLatihan(daftarHewan[idx]);
                            } else {
                                System.out.println("Pilihan layanan tidak valid.");
                            }
                        }
                    }
                    System.out.print("\nTekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;
                }

                case 6: {
                    System.out.println("\n--- HITUNG BIAYA PENITIPAN ---");
                    if (jumlahHewan == 0) {
                        System.out.println("Belum ada data hewan yang terdaftar.");
                    } else {
                        int idx = pilihIndeksHewan(scanner, daftarHewan, jumlahHewan);
                        if (idx != -1) {
                            int hari = bacaInt(scanner, "Lama penitipan (hari): ");
                            if (hari > 0) {
                                // Abstract method: hasilnya berbeda tergantung wujud asli objek
                                daftarHewan[idx].hitungBiayaPenitipan(hari);
                            } else {
                                System.out.println("Lama penitipan harus lebih dari 0 hari.");
                            }
                        }
                    }
                    System.out.print("\nTekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                    break;
                }

                case 7:
                    System.out.println("\nTerima kasih telah menggunakan aplikasi Smart PetShop!");
                    isRunning = false; // Menghentikan status while loop
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-7.");
                    break;
            }
        }
        scanner.close(); // Menutup penampung input keyboard untuk keamanan resource program
    }
}