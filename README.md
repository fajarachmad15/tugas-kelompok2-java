# Sistem Pemesanan Perjalanan (Console App)
Tugas Kelompok 2 - Pemrograman Berorientasi Objek Java  
Target: Java 17+

Aplikasi konsol sederhana untuk simulasi pencarian, pemesanan, dan pembatalan tiket pesawat serta hotel (mirip alur Traveloka/Tiket.com). 

Dibuat dari nol untuk memenuhi rubrik tugas: OOP (Sealed/Final class, inheritance, polymorphism), Java 17 pattern matching, Stream API, Lambda, dan custom exception handling.

---

## Anggota Kelompok & Pembagian Tugas

| Nama | Tugas & Modul | File | Branch |
| :--- | :--- | :--- | :--- |
| **Eryka Octa** | Modul Penerbangan (Stream API & filter tiket) | `Flight.java`, `FlightReservation.java`, `FlightService.java` | `feature-flight` |
| **Ananda Afriezta** | Modul Hotel (Filter lokasi & booking kamar) | `Hotel.java`, `HotelReservation.java`, `HotelService.java` | `feature-hotel` |
| **Fadhil Fakhruddin** | Core OOP, Sealed Class, & Polimorfisme list pemesanan | `Reservation.java`, `CodeGenerator.java`, `ReservationNotFoundException.java`, `ReservationService.java` | `feature-core-oop` |
| **Ridho** | Menu CLI utama & pembatalan via Pattern Matching | `Main.java` | `feature-app-driver` |
| **Achmad Fajar** | Lead QA, testing konsol, diagram UML, & dokumen laporan | `README.md`, laporan final (.pdf) | `qa-and-docs` |

---

## Fitur Utama

1. **Cari Penerbangan:** Filter asal, tujuan, dan tanggal pakai Java Stream & Lambda.
2. **Pesan Penerbangan:** Pilih tiket, potong kuota kursi, dan generate nomor konfirmasi 6 digit.
3. **Cari Hotel:** Filter hotel berdasarkan kota dan ketersediaan kamar.
4. **Pesan Hotel:** Hitung total tarif dan potong sisa kamar.
5. **Batalkan Reservasi:** Cek nomor konfirmasi. Jika ada, kuota kursi/kamar otomatis dikembalikan via *pattern matching* (`instanceof`). Jika salah, melempar `ReservationNotFoundException`.
6. **Lihat Semua Pemesanan:** Menampilkan semua tiket dan kamar yang sudah dibooking memanfaatkan *polymorphism*.
7. **Validasi Input:** Proteksi `try-catch` agar aplikasi tidak langsung keluar jika salah ketik huruf di input angka.

---

## Struktur File

```text
├── CodeGenerator.java                  # Helper pembuat kode acak (Final Class)
├── Flight.java                         # Model data pesawat
├── FlightReservation.java              # Subclass reservasi pesawat
├── FlightService.java                  # Logika pencarian & booking tiket
├── Hotel.java                          # Model data hotel
├── HotelReservation.java               # Subclass reservasi hotel
├── HotelService.java                   # Logika pencarian & booking hotel
├── Main.java                           # Menu console & alur pembatalan
├── Reservation.java                    # Sealed abstract class
├── ReservationNotFoundException.java   # Custom error pembatalan
├── ReservationService.java             # Fungsi cetak daftar reservasi
└── README.md
