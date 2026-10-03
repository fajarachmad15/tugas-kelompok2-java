public final class HotelReservation extends Reservation {
    private Hotel hotel;
    private String checkInDate;
    private String checkOutDate;
    private int roomCount;

    public HotelReservation(String confirmationNumber, String customerName, double totalPrice, Hotel hotel, String checkInDate, String checkOutDate, int roomCount) {
        super(confirmationNumber, customerName, totalPrice);
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomCount = roomCount;
    }

    public Hotel getHotel() { return hotel; }
    public int getRoomCount() { return roomCount; }

    @Override
    public void displayDetails() {
        System.out.println("\n========================================");
        System.out.println("       DETAIL RESERVASI HOTEL");
        System.out.println("========================================");
        System.out.println("ID Konfirmasi : " + getConfirmationNumber());
        System.out.println("Nama Customer : " + getCustomerName());
        System.out.println("Hotel         : " + hotel.getName());
        System.out.println("Kota          : " + hotel.getCity());
        System.out.println("Check-in      : " + checkInDate);
        System.out.println("Check-out     : " + checkOutDate);
        System.out.println("Jumlah Kamar  : " + roomCount);
        System.out.printf("Harga/Malam   : Rp%,.2f%n", hotel.getPricePerNight());
        System.out.printf("Total Harga   : Rp%,.2f%n", getTotalPrice());
        System.out.println("========================================");
    }
}