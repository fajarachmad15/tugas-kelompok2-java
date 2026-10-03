import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlightService {
    private List<Flight> flights = new ArrayList<>();

    public FlightService() {
        // Inisialisasi beberapa data dummy awal
        flights.add(new Flight("GA101", "Jakarta", "Bali", "2026-10-10", 1200000, 5));
        flights.add(new Flight("SJ202", "Jakarta", "Surabaya", "2026-10-12", 800000, 10));
        flights.add(new Flight("QG303", "Jakarta", "Bali", "2026-10-10", 950000, 8));
        flights.add(new Flight("ID404", "Surabaya", "Bali", "2026-10-15", 700000, 3));
        flights.add(new Flight("JT505", "Jakarta", "Bali", "2026-10-11", 850000, 0));
    }

    public List<Flight> getFlights() { return flights; }

    // TODO (Eryka): Implementasikan pencarian rute dengan Java Stream API & Lambda
    public void searchFlights(Scanner scanner) {
        System.out.println("\n=== CARI PENERBANGAN ===");
        String origin = readText(scanner, "Masukkan kota asal      : ");
        String destination = readText(scanner, "Masukkan kota tujuan    : ");
        String date = readDate(scanner, "Tanggal (yyyy-MM-dd)    : ");
        int passengers = readPositiveInt(scanner, "Jumlah penumpang       : ");

         // Stream API + Lambda: filter rute, tanggal, kursi; urutkan dari harga termurah
        List<Flight> results = flights.stream()
                .filter(f -> f.getOrigin().equalsIgnoreCase(origin))
                .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                .filter(f -> f.getTravelDate().equals(date))
                .filter(f -> f.hasEnoughSeats(passengers))
                .sorted(Comparator.comparingDouble(Flight::getPrice))
                .collect(Collectors.toList());
 
        if (results.isEmpty()) {
            System.out.println("\nTidak ada penerbangan tersedia untuk kriteria tersebut.");
            return;
        }
 
        System.out.println("\nDitemukan " + results.size() + " penerbangan (diurutkan dari termurah):");
        results.forEach(f -> System.out.println(" - " + f));
        System.out.println("\nGunakan menu Pesan Penerbangan untuk memesan salah satu tiket di atas.");
    }

    // TODO (Eryka): Implementasikan form pemesanan tiket, validasi kursi, potong kuota, dan return FlightReservation
    public FlightReservation bookFlight(Scanner scanner) {
       System.out.println("\n=== PESAN TIKET PENERBANGAN ===");
 
        // Tampilkan penerbangan yang masih punya kursi sebagai referensi
        List<Flight> available = flights.stream()
                .filter(f -> f.getAvailableSeats() > 0)
                .collect(Collectors.toList());
        if (available.isEmpty()) {
            System.out.println("Maaf, semua penerbangan sudah penuh.");
            return null;
        }
        available.forEach(f -> System.out.println(" - " + f));
 
        String flightNumber = readText(scanner, "\nMasukkan nomor penerbangan: ");
 
        // Cari penerbangan berdasarkan nomor (Stream + Optional)
        Optional<Flight> found = flights.stream()
                .filter(f -> f.getFlightNumber().equalsIgnoreCase(flightNumber))
                .findFirst();
        if (found.isEmpty()) {
            System.out.println("Nomor penerbangan \"" + flightNumber + "\" tidak ditemukan.");
            return null;
        }
        Flight flight = found.get();
 
        int passengers = readPositiveInt(scanner, "Jumlah penumpang       : ");
 
        // Validasi ketersediaan kursi
        if (!flight.hasEnoughSeats(passengers)) {
            System.out.println("Kursi tidak mencukupi. Sisa kursi pada " + flight.getFlightNumber()
                    + ": " + flight.getAvailableSeats());
            return null;
        }
 
        String name = readText(scanner, "Nama pemesan           : ");
        String contact = readContact(scanner, "Kontak (no. HP)        : ");
 
        double totalPrice = flight.getPrice() * passengers;
 
        // Potong kuota kursi lalu buat reservasi
        flight.reduceSeats(passengers);
        String code = CodeGenerator.generateConfirmationCode();
        FlightReservation reservation =
                new FlightReservation(code, name, totalPrice, flight, passengers, contact);
 
        System.out.println("\nPemesanan berhasil! Nomor konfirmasi Anda: " + code);
        reservation.displayDetails();
        return reservation;
    }
 
    // ===================== HELPER INPUT (try-catch & validasi) =====================
 
    private String readText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
        }
    }
 
    private int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
                System.out.println("Angka harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
            }
        }
    }
 
    private String readDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                LocalDate.parse(input); // validasi format yyyy-MM-dd
                return input;
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal salah. Gunakan yyyy-MM-dd (contoh: 2026-10-10).");
            }
        }
    }
 
    private String readContact(Scanner scanner, String prompt) {
        while (true) {
            String input = readText(scanner, prompt);
            if (input.matches("\\+?\\d{8,15}")) {
                return input;
            }
            System.out.println("Kontak tidak valid. Masukkan 8-15 digit angka (boleh diawali +).");
        }
    }
}