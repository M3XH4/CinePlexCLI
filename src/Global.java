import java.awt.*;

public class Global {
    public static String putBackgroundColor(String color, int spaces) {
        return color + " ".repeat(Math.max(0, spaces)) + FontManager.RESET;
    }
    public static void putHorizontalLine(String color, int spaces) {
        System.out.println(color + "=".repeat(Math.max(0, spaces)) + FontManager.RESET);
    }
    public static void clearScreen() {
        System.out.print(FontManager.ERASE_ENTIRE_SCREEN);
        System.out.flush();
    }
}
