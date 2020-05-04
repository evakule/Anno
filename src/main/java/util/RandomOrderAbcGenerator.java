package util;

public class RandomOrderAbcGenerator {
    public static void main(String[] args) {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int stringRange = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringRange; i++) {
            int randomNumber = getRandomIntegerBetweenRange(0, s.length() - 1);
            char charFromString = s.charAt(randomNumber);
            sb.append(charFromString);
            s = removeCharFromString(s, charFromString);
        }
        System.out.println(sb.toString().toLowerCase());
        System.out.println(sb.toString());
    }

    public static int getRandomIntegerBetweenRange(double min, double max) {
        return (int) ((int) (Math.random() * ((max - min) + 1)) + min);
    }

    private static String removeCharFromString(String s, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
