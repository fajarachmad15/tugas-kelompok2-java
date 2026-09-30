public class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private String travelDate;
    private double price;
    private int availableSeats;

    public Flight(String flightNumber, String origin, String destination, String travelDate, double price, int availableSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.travelDate = travelDate;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() { return flightNumber; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getTravelDate() { return travelDate; }
    public double getPrice() { return price; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    @Override
    public String toString() {
        return String.format("[%s] %s -> %s | Tgl: %s | Rp%.2f | Sisa: %d kursi",
                flightNumber, origin, destination, travelDate, price, availableSeats);
    }
}