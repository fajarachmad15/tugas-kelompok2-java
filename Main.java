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

    private static void cancelReservation(Scanner scanner) {
    System.out.print("Masukkan ID Konfirmasi: ");
    String code = scanner.nextLine().trim();

    try {
        // Cari reservasi, throw ReservationNotFoundException kalau tidak ada
        Reservation res = findReservation(code);

        // Pattern matching: kembalikan kuota sesuai jenis reservasi
        if (res instanceof FlightReservation fr) {
            fr.getFlight().restoreSeats(fr.getPassengerCount());
            System.out.println("Kursi penerbangan " + fr.getFlight().getFlightNumber()
                    + " dikembalikan (" + fr.getPassengerCount() + " kursi).");
        } else if (res instanceof HotelReservation hr) {
            Hotel hotel = hr.getHotel();
            hotel.setAvailableRooms(hotel.getAvailableRooms() + hr.getRoomCount());
            System.out.println("Kamar " + hotel.getName()
                    + " dikembalikan (" + hr.getRoomCount() + " kamar).");
        }

        // Hapus dari list
        reservations.remove(res);
        System.out.println("Reservasi " + code + " berhasil dibatalkan.");

    } catch (ReservationNotFoundException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    private static Reservation findReservation(String code) throws ReservationNotFoundException {
        return reservations.stream()
                .filter(r -> r.getConfirmationNumber().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new ReservationNotFoundException(
                        "Reservasi dengan ID " + code + " tidak ditemukan."));
    }
}