import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
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

    // Pencarian hotel berdasarkan kota/lokasi dan ketersediaan kamar
    public void searchHotels(Scanner scanner) {
        System.out.println("\n=== CARI HOTEL ===");
        System.out.print("Masukkan kota/lokasi: ");

        String city = scanner.nextLine().trim();

        if (city.isEmpty()) {
            System.out.println("Kota/lokasi tidak boleh kosong.");
            return;
        }
        List<Hotel> results = hotels.stream().filter(h -> h.getCity().equalsIgnoreCase(city)).filter(h -> h.getAvailableRooms() > 0).toList();
        if (results.isEmpty()) {
            System.out.println("Tidak ditemukan hotel tersedia di kota/lokasi: " + city);
            return;
        }
        System.out.println("\nHotel yang tersedia di " + city + ":");
        results.forEach(hotel -> System.out.println(hotel));
    }

    // Pemesanan kamar hotel
    public HotelReservation bookHotel(Scanner scanner) {
        System.out.println("\n=== PESAN KAMAR HOTEL ===");

        // Tampilkan hotel yang tersedia
        System.out.println("Daftar hotel:");
        hotels.stream().filter(h -> h.getAvailableRooms() > 0).forEach(hotel -> System.out.println(hotel));

        // Jika tidak ada hotel tersedia
        boolean hasAvailableHotel = hotels.stream()
                .anyMatch(h -> h.getAvailableRooms() > 0);

        if (!hasAvailableHotel) {
            System.out.println("Tidak ada hotel dengan kamar yang tersedia.");
            return null;
        }

        // Pilih hotel
        System.out.print("\nMasukkan ID hotel: ");
        String hotelId = scanner.nextLine().trim();

        Hotel selectedHotel = hotels.stream()
                .filter(h -> h.getHotelId().equalsIgnoreCase(hotelId))
                .filter(h -> h.getAvailableRooms() > 0)
                .findFirst()
                .orElse(null);

        if (selectedHotel == null) {
            System.out.println(
                    "Hotel tidak ditemukan atau kamar hotel sudah habis."
            );
            return null;
        }

        // Input nama customer
        System.out.print("Masukkan nama customer: ");
        String customerName = scanner.nextLine().trim();

        if (customerName.isEmpty()) {
            System.out.println("Nama customer tidak boleh kosong.");
            return null;
        }

        // Input tanggal check-in
        System.out.print("Tanggal check-in (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine().trim();

        // Input tanggal check-out
        System.out.print("Tanggal check-out (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine().trim();

        LocalDate checkIn;
        LocalDate checkOut;

        try {
            checkIn = LocalDate.parse(checkInDate);
            checkOut = LocalDate.parse(checkOutDate);
        } catch (DateTimeParseException e) {
            System.out.println(
                    "Format tanggal tidak valid. Gunakan format YYYY-MM-DD."
            );
            return null;
        }

        // Check-out harus setelah check-in
        if (!checkOut.isAfter(checkIn)) {
            System.out.println(
                    "Tanggal check-out harus setelah tanggal check-in."
            );
            return null;
        }

        // Hitung jumlah malam
        long numberOfNights = ChronoUnit.DAYS.between(checkIn, checkOut);

        // Input jumlah kamar
        System.out.print("Jumlah kamar: ");

        int roomCount;

        try {
            roomCount = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Jumlah kamar harus berupa angka.");
            return null;
        }

        if (roomCount <= 0) {
            System.out.println("Jumlah kamar harus lebih dari 0.");
            return null;
        }

        // Validasi ketersediaan kamar
        if (roomCount > selectedHotel.getAvailableRooms()) {
            System.out.println("Kamar tidak mencukupi. " + "Tersedia: " + selectedHotel.getAvailableRooms() + " kamar.");
            return null;
        }

        // Hitung total harga
        double totalPrice =
                numberOfNights
                * selectedHotel.getPricePerNight()
                * roomCount;

        // Kurangi jumlah kamar setelah semua validasi berhasil
        selectedHotel.setAvailableRooms(
                selectedHotel.getAvailableRooms() - roomCount
        );

        // Generate nomor konfirmasi
        String confirmationNumber =
                CodeGenerator.generateConfirmationCode();

        // Buat reservasi hotel
        HotelReservation reservation = new HotelReservation(
                confirmationNumber,
                customerName,
                totalPrice,
                selectedHotel,
                checkInDate,
                checkOutDate,
                roomCount
        );

        // Tampilkan ringkasan booking
        System.out.println("\n=== PEMESANAN BERHASIL ===");
        System.out.println("Nomor Konfirmasi : " + confirmationNumber);
        System.out.println("Hotel             : " + selectedHotel.getName());
        System.out.println("Check-in          : " + checkInDate);
        System.out.println("Check-out         : " + checkOutDate);
        System.out.println("Jumlah malam      : " + numberOfNights);
        System.out.println("Jumlah kamar      : " + roomCount);
        System.out.printf("Total harga       : Rp%,.2f%n", totalPrice);
        System.out.println(
                "Sisa kamar        : "
                + selectedHotel.getAvailableRooms()
        );

        return reservation;
    }
}