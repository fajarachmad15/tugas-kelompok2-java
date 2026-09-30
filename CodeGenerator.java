import java.util.Random;

// Final class utilitas untuk menghasilkan kode konfirmasi
public final class CodeGenerator {
    private CodeGenerator() {}

    public static String generateConfirmationCode() {
        Random random = new Random();
        int code = random.nextInt(1_000_000);
        return String.format("%06d", code); 
    }
}