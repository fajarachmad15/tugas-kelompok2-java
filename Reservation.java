// Kelas induk sealed: hanya mengizinkan FlightReservation dan HotelReservation
public abstract sealed class Reservation permits FlightReservation, HotelReservation {
    private String confirmationNumber;
    private String customerName;
    private double totalPrice;

    public Reservation(String confirmationNumber, String customerName, double totalPrice) {
        this.confirmationNumber = confirmationNumber;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String getConfirmationNumber() { return confirmationNumber; }
    public String getCustomerName() { return customerName; }
    public double getTotalPrice() { return totalPrice; }

    // Wajib dioverride oleh subclass
    public abstract void displayDetails();
}