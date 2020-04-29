import java.util.HashMap;

public class Encryption {

    private static final String ABC_UPPERCASE
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
    private static final String ABC_UPPERCASE_REVERSED
            = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba987654321";

    private HashMap<Character, Character> pair = new HashMap<>();

    public Encryption() {
        for (int i = 0; i < ABC_UPPERCASE.length(); i++) {
            pair.put(ABC_UPPERCASE.charAt(i), ABC_UPPERCASE_REVERSED.charAt(i));
        }
    }

    public String encrypt(String row) {
        char[] charArray = row.toCharArray();
        for (int i = 0; i < row.length(); i++) {
            char toReplace = charArray[i];
            Character replaced = pair.get(toReplace);
            if (replaced != null) {
                charArray[i] = replaced;
            }
        }
        return new String(charArray);
    }
}
