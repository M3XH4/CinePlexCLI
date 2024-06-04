import java.util.*;
public class MainClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String horizontalLine = "=====================================================================";
        
        Global.clearScreen();
        System.out.println(FontManager.TEXT_BLACK + FontManager.BACKGROUND_RED + horizontalLine + FontManager.RESET);
        System.out.println(Titles.title1);
        System.out.println(FontManager.TEXT_BLACK + FontManager.BACKGROUND_RED + horizontalLine + FontManager.RESET);
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
        System.out.println(FontManager.BACKGROUND_BLACK + "\t\t" + FontManager.BOLD + "BOOK" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":\tBook Seats For A Movie "  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 30));
        System.out.println(FontManager.BACKGROUND_BLACK + "\t\t" + FontManager.BOLD + "BUY" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":\tBuy Snacks And Drinks " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 31));
        System.out.println(FontManager.BACKGROUND_BLACK + "\t\t" + FontManager.BOLD + "EXIT" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":\tQuit Application" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 37));
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
        do {
            System.out.println(Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLine.length()));

            System.out.println("What Would You Like To Do?");
            System.out.print("Your Answer - ");
            String choice = input.nextLine().toUpperCase();

            if (choice.equals("BOOK")) {
                System.out.println("PICK A MOVIE");
                System.out.println("CINEMA 1 - Beauty and the Beast");
                System.out.println("CINEMA 2 - John Wick 4");
                System.out.println("CINEMA 3 - Avenger's Endgame");
                System.out.println("CINEMA 4 - Spider-man Homecoming");
                break;
            } else if (choice.equals("BUY")) {
                break;
            } else if (choice.equals("EXIT")) {
                break;
            }
        } while (true);
    }
}
