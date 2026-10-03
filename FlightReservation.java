public final class FlightReservation extends Reservation {
    private Flight flight;
    private int passengerCount;
    private String contact;

    public FlightReservation(String confirmationNumber, String customerName, double totalPrice, Flight flight, int passengerCount, String contact) {
        super(confirmationNumber, customerName, totalPrice);
        this.flight = flight;
        this.passengerCount = passengerCount;
        this.contact = contact;
    }

    public FlightReservation(String confirmationNumber, String customerName, double totalPrice, Flight flight, int passengerCount) {
        this(confirmationNumber, customerName, totalPrice, flight, passengerCount, "-");
    }

    public Flight getFlight() { return flight; }
    public int getPassengerCount() { return passengerCount; }
    public String getContact() { return contact; }

    @Override
    public void displayDetails() {
        System.out.println("==================================================");
        System.out.println("               TIKET PENERBANGAN");
        System.out.println("==================================================");
        System.out.println("ID Konfirmasi  : " + getConfirmationNumber());
        System.out.println("Nama Pemesan   : " + getCustomerName());
        System.out.println("Kontak         : " + contact);
        System.out.println("--------------------------------------------------");
        System.out.println("No. Penerbangan: " + flight.getFlightNumber());
        System.out.println("Rute           : " + flight.getOrigin() + " -> " + flight.getDestination());
        System.out.println("Tanggal        : " + flight.getTravelDate());
        System.out.println("Jml Penumpang  : " + passengerCount + " orang");
        System.out.println(String.format("Harga/Penumpang: Rp%,.2f", flight.getPrice()));
        System.out.println(String.format("Total Bayar    : Rp%,.2f", getTotalPrice()));
        System.out.println("==================================================");
    }
}