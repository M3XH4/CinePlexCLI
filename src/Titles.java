public class Titles {
    static String title =
            FontManager.BACKGROUND_RED + Global.putSpaces(25) + " ██████╗██╗███╗   ██╗███████╗██████╗ ██╗     ███████╗██╗  ██╗                          " + FontManager.RESET + "\n" +
            FontManager.BACKGROUND_RED + Global.putSpaces(25) + "██╔════╝██║████╗  ██║██╔════╝██╔══██╗██║     ██╔════╝╚██╗██╔╝                          " + FontManager.RESET + "\n" +
            FontManager.BACKGROUND_RED + Global.putSpaces(25) + "██║     ██║██╔██╗ ██║█████╗  ██████╔╝██║     █████╗   ╚███╔╝                           " + FontManager.RESET + "\n" +
            FontManager.BACKGROUND_RED + Global.putSpaces(25) + "██║     ██║██║╚██╗██║██╔══╝  ██╔═══╝ ██║     ██╔══╝   ██╔██╗                           " + FontManager.RESET + "\n" +
            FontManager.BACKGROUND_RED + Global.putSpaces(25) + "╚██████╗██║██║ ╚████║███████╗██║     ███████╗███████╗██╔╝ ██╗                          " + FontManager.RESET + "\n" +
            FontManager.BACKGROUND_RED + Global.putSpaces(25) + " ╚═════╝╚═╝╚═╝  ╚═══╝╚══════╝╚═╝     ╚══════╝╚══════╝╚═╝  ╚═╝                          " + FontManager.RESET;

    public static void displayTitle() {
        Global.putHorizontalLine(FontManager.tertiaryCombo, MainClass.horizontalLineLength);
        System.out.println(title);
        Global.putHorizontalLine(FontManager.tertiaryCombo, MainClass.horizontalLineLength);
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength));
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength));
        System.out.println(FontManager.primaryCombo + "        BOOK" + FontManager.RESET + FontManager.secondaryCombo + ":    Book Seats For A Movie"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (MainClass.horizontalLineLength - "        BOOK:    Book Seats For A Movie".length())));
        System.out.println(FontManager.primaryCombo + "        BUY" + FontManager.RESET + FontManager.secondaryCombo + ":     Buy Snacks And Drinks" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (MainClass.horizontalLineLength - "        BUY:     Buy Snacks And Drinks".length())));
        System.out.println(FontManager.primaryCombo + "        EXIT" + FontManager.RESET + FontManager.secondaryCombo + ":    Quit Application" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (MainClass.horizontalLineLength - "        EXIT:    Quit Application".length())));
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength));
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength));
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength));
    }
}
