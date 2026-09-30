public final class FlightReservation extends Reservation {
    private Flight flight;
    private int passengerCount;

    public FlightReservation(String confirmationNumber, String customerName, double totalPrice, Flight flight, int passengerCount) {
        super(confirmationNumber, customerName, totalPrice);
        this.flight = flight;
        this.passengerCount = passengerCount;
    }

    public Flight getFlight() { return flight; }
    public int getPassengerCount() { return passengerCount; }

    @Override
    public void displayDetails() {
        // TODO (Eryka): Tampilkan rincian tiket penerbangan secara lengkap dan rapi
        System.out.println("ID Konfirmasi: " + getConfirmationNumber() + " | Pesawat: " + flight.getFlightNumber());
    }
}