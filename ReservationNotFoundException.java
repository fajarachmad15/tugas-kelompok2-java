// Custom exception saat pembatalan tiket tidak ditemukan
public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}