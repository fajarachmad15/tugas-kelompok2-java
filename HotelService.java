import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelService {
    private List<Hotel> hotels = new ArrayList<>();

    public HotelService() {
        // Inisialisasi data dummy awal
        hotels.add(new Hotel("H01", "Grand Mercure", "Bali", 950000, 4));
        hotels.add(new Hotel("H02", "Santika", "Surabaya", 600000, 8));
    }

    public List<Hotel> getHotels() { return hotels; }

    // TODO (Ananda): Implementasikan pencarian hotel berdasarkan filter kota/lokasi
    public void searchHotels(Scanner scanner) {
        System.out.println("\n[TODO Ananda] Fitur Cari Hotel");
    }

    // TODO (Ananda): Implementasikan form pemesanan kamar, hitung total harga (malam x tarif), potong kamar, dan return HotelReservation
    public HotelReservation bookHotel(Scanner scanner) {
        System.out.println("\n[TODO Ananda] Fitur Pesan Kamar Hotel");
        return null;
    }
}