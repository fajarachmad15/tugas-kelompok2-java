import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static FlightService flightService = new FlightService();
    private static HotelService hotelService = new HotelService();
    private static ReservationService reservationService = new ReservationService();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Navigasi menu utama CLI
        while (running) {
            System.out.println("\n=== SISTEM PEMESANAN PERJALANAN (TRAVEL APP) ===");
            System.out.println("1. Cari Penerbangan");
            System.out.println("2. Pesan Tiket Penerbangan");
            System.out.println("3. Cari Hotel");
            System.out.println("4. Pesan Kamar Hotel");
            System.out.println("5. Batalkan Reservasi");
            System.out.println("6. Lihat Semua Pemesanan");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu [1-7]: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> flightService.searchFlights(scanner);
                    case 2 -> {
                        FlightReservation fr = flightService.bookFlight(scanner);
                        if (fr != null) reservations.add(fr);
                    }
                    case 3 -> hotelService.searchHotels(scanner);
                    case 4 -> {
                        HotelReservation hr = hotelService.bookHotel(scanner);
                        if (hr != null) reservations.add(hr);
                    }
                    case 5 -> cancelReservation(scanner);
                    case 6 -> reservationService.viewAllReservations(reservations);
                    case 7 -> {
                        System.out.println("Terima kasih telah menggunakan Travel App!");
                        running = false;
                    }
                    default -> System.out.println("Pilihan tidak valid. Silakan pilih menu 1-7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Input menu harus berupa angka numerik!");
            }
        }
    }

    // TODO (Ridho): 
    // 1. Cari objek reservasi berdasarkan input nomor konfirmasi.
    // 2. Jika tidak ditemukan, throw ReservationNotFoundException.
    // 3. Jika ditemukan, gunakan Pattern Matching (instanceof):
    //    - if (res instanceof FlightReservation fr) -> kembalikan kuota kursi pesawat
    //    - else if (res instanceof HotelReservation hr) -> kembalikan kuota kamar hotel
    // 4. Hapus reservasi dari list dan tampilkan pesan sukses.
    private static void cancelReservation(Scanner scanner) {
        System.out.println("\n[TODO Ridho] Fitur Batalkan Reservasi (Pattern Matching & Exception)");
    }
}