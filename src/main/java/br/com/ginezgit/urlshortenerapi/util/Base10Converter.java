package br.com.ginezgit.urlshortenerapi.util;

public class Base10Converter {

    private static char symbols[] = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l','m', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static String convert(int number, int base) {
        return convert(number, base, 0, "");
    }

    private static String convert(int number, int base, int position, String result) {
        if (number < Math.pow(base, position + 1)) {
            return symbols[(number / (int) Math.pow(base, position))] + result;
        } else {
            int remainder = (number % (int) Math.pow(base, position + 1));
            return convert(number - remainder, base, position + 1, symbols[remainder / (int) (Math.pow(base, position))] + result);
        }
    }

}