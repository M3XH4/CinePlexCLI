
public class Global {

    public static String putSpaces(int spaces) {
        return " ".repeat(Math.max(0, spaces));
    }

    public static String putBackgroundColor(String color, int spaces) {
        return color + " ".repeat(Math.max(0, spaces)) + FontManager.RESET;
    }

    public static void putHorizontalLine(String color, int spaces) {
        System.out.println(color + "=".repeat(Math.max(0, spaces)) + FontManager.RESET);
    }

    public static String spacerString(int spaces, String itemString) {
        int itemNameLength = spaces - itemString.length();
        return itemString + " ".repeat(Math.max(0, itemNameLength));
    }

}
