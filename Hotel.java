public class Hotel {
    private String hotelId;
    private String name;
    private String city;
    private double pricePerNight;
    private int availableRooms;

    public Hotel(String hotelId, String name, String city, double pricePerNight, int availableRooms) {
        this.hotelId = hotelId;
        this.name = name;
        this.city = city;
        this.pricePerNight = pricePerNight;
        this.availableRooms = availableRooms;
    }

    public String getHotelId() { return hotelId; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public double getPricePerNight() { return pricePerNight; }
    public int getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(int availableRooms) { this.availableRooms = availableRooms; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) | Rp%.2f/malam | Sisa: %d kamar",
                hotelId, name, city, pricePerNight, availableRooms);
    }
}