package sd.urlshortening.utils;

public class Base62Converter {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();


    public static String fromDecimal(long number) {
        if (number == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int remainder = (int) (number % BASE);
            sb.append(ALPHABET.charAt(remainder));
            number /= BASE;
        }

        return sb.reverse().toString();
    }

    public static long toDecimal(String base62) {
        long result = 0;
        for (int i = 0; i < base62.length(); i++) {
            result = result * BASE + ALPHABET.indexOf(base62.charAt(i));
        }
        return result;
    }
}
