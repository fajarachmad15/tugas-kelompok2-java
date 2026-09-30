import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightService {
    private List<Flight> flights = new ArrayList<>();

    public FlightService() {
        // Inisialisasi beberapa data dummy awal
        flights.add(new Flight("GA101", "Jakarta", "Bali", "2026-10-10", 1200000, 5));
        flights.add(new Flight("SJ202", "Jakarta", "Surabaya", "2026-10-12", 800000, 10));
    }

    public List<Flight> getFlights() { return flights; }

    // TODO (Eryka): Implementasikan pencarian rute dengan Java Stream API & Lambda
    public void searchFlights(Scanner scanner) {
        System.out.println("\n[TODO Eryka] Fitur Cari Penerbangan dengan Stream/Lambda");
    }

    // TODO (Eryka): Implementasikan form pemesanan tiket, validasi kursi, potong kuota, dan return FlightReservation
    public FlightReservation bookFlight(Scanner scanner) {
        System.out.println("\n[TODO Eryka] Fitur Pesan Tiket Penerbangan");
        return null;
    }
}