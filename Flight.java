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
    
    /** Setter dengan validasi: jumlah kursi tidak boleh negatif. */
    public void setAvailableSeats(int availableSeats) {
        if (availableSeats < 0) {
            throw new IllegalArgumentException("Jumlah kursi tidak boleh negatif.");
        }
        this.availableSeats = availableSeats;
    }
 
    /** True jika kursi yang tersisa cukup untuk jumlah penumpang tertentu. */
    public boolean hasEnoughSeats(int passengers) {
        return passengers > 0 && availableSeats >= passengers;
    }
 
    /** Potong kuota kursi saat pemesanan berhasil. */
    public void reduceSeats(int passengers) {
        if (!hasEnoughSeats(passengers)) {
            throw new IllegalStateException("Kursi tidak mencukupi untuk penerbangan " + flightNumber);
        }
        availableSeats -= passengers;
    }
 
    /** Kembalikan kuota kursi (dipakai saat pembatalan reservasi). */
    public void restoreSeats(int passengers) {
        if (passengers > 0) {
            availableSeats += passengers;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s -> %s | Tgl: %s | Rp%.2f | Sisa: %d kursi",
                flightNumber, origin, destination, travelDate, price, availableSeats);
    }
}