package util;

/**
 * Created by Hasmik on 27.06.2017.
 */
public class Validator {
    public Validator() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.equals("");

    }
}
