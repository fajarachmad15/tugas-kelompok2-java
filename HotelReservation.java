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
        // TODO (Ananda): Tampilkan rincian reservasi hotel secara lengkap dan rapi
        System.out.println("ID Konfirmasi: " + getConfirmationNumber() + " | Hotel: " + hotel.getName());
    }
}