import java.awt.*;
import java.io.IOException;
import java.util.*;
public class MainClass {
    public static void main(String[] args) throws IOException {
        CinemaManager cinemaManager = new CinemaManager();
        initializeCinemas(cinemaManager);
        Scanner input = new Scanner(System.in);
        String horizontalLine = "=====================================================================";
        boolean showTitle = true;
        do {
            displaySeats(cinemaManager.getCinema("2").getSeats());
            FileManager.saveBookings(cinemaManager.getCinemas());
            if (showTitle) {
                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLine.length());
                System.out.println(Titles.title1);
                System.out.println(FontManager.TEXT_BLACK + FontManager.BACKGROUND_RED + horizontalLine + FontManager.RESET);
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + "        BOOK" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":    Book Seats For A Movie"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLine.length() - "        BOOK:    Book Seats For A Movie".length())));
                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + "        BUY" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":    Buy Snacks And Drinks" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLine.length() - "        BUY:    Buy Snacks And Drinks".length())));
                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + "        EXIT" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":    Quit Application" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLine.length() - "        EXIT:    Quit Application".length())));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
            }
            showTitle = false;
            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLine.length());

            System.out.println("What Would You Like To Do?");
            System.out.print("Your Answer - ");
            String choice = input.nextLine().toUpperCase();

            if (choice.equals("BOOK")) {
                System.out.println("CINEMAS");
                System.out.println("Cinema 1: " + (!cinemaManager.getCinema("1").getMovie().getName().isEmpty() ? cinemaManager.getCinema("1").getMovie().getName() : "None"));
                System.out.println("Cinema 2: " + (!cinemaManager.getCinema("2").getMovie().getName().isEmpty() ? cinemaManager.getCinema("2").getMovie().getName() : "None"));
                System.out.println("Cinema 3: " + (!cinemaManager.getCinema("3").getMovie().getName().isEmpty() ? cinemaManager.getCinema("3").getMovie().getName() : "None"));
                System.out.println("Cinema 4: " + (!cinemaManager.getCinema("4").getMovie().getName().isEmpty() ? cinemaManager.getCinema("4").getMovie().getName() : "None"));
                break;
            } else if (choice.equals("BUY")) {
                break;
            } else if (choice.equals("LOGIN")) {
                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLine.length());
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 28) + FontManager.BACKGROUND_BLACK + FontManager.BOLD + "ADMIN LOGIN" + FontManager.RESET + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 30));
                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLine.length());
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                System.out.println(FontManager.BACKGROUND_BLACK +  "\tEnter Username: " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 49));
                String username = input.nextLine();
                System.out.println(FontManager.BACKGROUND_BLACK +  "\tEnter Password: " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 49));
                String password = input.nextLine();
                if (username.equals("manager") && password.equals("man123")) {
                    System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                    System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                    Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLine.length());
                    System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + "                           WELCOME ADMIN" + FontManager.RESET + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLine.length() - "                           WELCOME ADMIN".length())));
                    System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + "        CINEMAS" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":    Edit Cinemas"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLine.length() - "        CINEMAS:    Edit Cinemas".length())));
                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + "        SNACKS" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":     Edit Snacks/Drinks"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLine.length() - "        SNACKS:     Edit Snacks/Drinks".length())));
                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + "        BACK" + FontManager.RESET + FontManager.BACKGROUND_BLACK + ":       Go Back In Main Page"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLine.length() - "        BACK:       Go Back In Main Page".length())));
                    System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                    System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                    do {
                        Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLine.length());
                        System.out.println("Which Option Would You Like To Do: ");
                        String managerChoice = input.nextLine().toUpperCase();
                        if (managerChoice.equals("CINEMAS")) {
                            System.out.print("Enter Cinema Number: ");
                            String cinemaID = input.nextLine();
                            Cinema cinema = cinemaManager.getCinema(cinemaID);
                            if (cinema == null) {
                                System.out.println("Cinema not found.");
                            }
                            System.out.println("Cinema " + cinema.getId() + " is Selected");
                            System.out.println("Movie:    " + (!cinema.getMovie().getName().isEmpty() ? cinema.getMovie().getName() : "None"));
                            System.out.println("Would You Like To: (Change/Back)");
                            String cinemaChoice = input.nextLine().toUpperCase();
                            if (cinemaChoice.equals("CHANGE")) {
                                System.out.println("Enter Movie Name");
                                String movieName = input.nextLine();
                                Movie movie = new Movie("A", movieName);
                                cinema.setMovie(movie);
                                cinema.setSeats(new ArrayList<>());
                                System.out.println("Changed Movies To " + cinema.getMovie().getName());
                            }
                        } else if (managerChoice.equals("SNACKS")) {

                        } else if (managerChoice.equals("BACK")) {
                            break;
                        }
                    } while (true);
                } else {
                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_RED + FontManager.BOLD + "                  Incorrect Username Or Password" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 21));
                }
                showTitle = true;
            } else if (choice.equals("EXIT")) {
                break;
            }
        } while (true);
    }

    private static void displaySeats(ArrayList<Seat> seats) {
        String[] rows = {"D", "C", "B", "A"};
        for (String row : rows) {
            for (int i = 1; i <= 5; i++) {
                Seat seat = findSeat(seats, row + i);
                if (seat != null) {
                    System.out.print(FontManager.BACKGROUND_BLACK + "[ " + FontManager.BOLD + seat.getSeatID() + FontManager.RESET + FontManager.BACKGROUND_BLACK + ": " + (seat.isAvailable() ? FontManager.TEXT_GREEN_BRIGHT + "Available" : FontManager.TEXT_RED_BRIGHT + "Booked   ") + FontManager.RESET + FontManager.BACKGROUND_BLACK + " ] " + FontManager.RESET);
                }
            }
            System.out.print(FontManager.BACKGROUND_BLACK + "   " + FontManager.RESET);
            for (int i = 6; i <= 10; i++) {
                Seat seat = findSeat(seats, row + i);
                if (seat != null) {
                    System.out.print(FontManager.BACKGROUND_BLACK + "[ " + FontManager.BOLD + seat.getSeatID() + FontManager.RESET + FontManager.BACKGROUND_BLACK + ": " + (seat.isAvailable() ? FontManager.TEXT_GREEN_BRIGHT + "Available" : FontManager.TEXT_RED_BRIGHT + "Booked   ") + FontManager.RESET + FontManager.BACKGROUND_BLACK + " ] " + FontManager.RESET);                }
            }
            System.out.println();
        }
    }

    private static Seat findSeat(ArrayList<Seat> seats, String seatID) {
        for (Seat seat : seats) {
            if (seat.getSeatID().equals(seatID)) {
                return seat;
            }
        }
        return null;
    }
    private static void initializeCinemas(CinemaManager cinemaManager) {
        cinemaManager.addCinema(new Cinema("1"));
        cinemaManager.addCinema(new Cinema("2"));
        cinemaManager.addCinema(new Cinema("3"));
        cinemaManager.addCinema(new Cinema("4"));
        cinemaManager.getCinema("1").setMovie(new Movie("Avengers: Endgame", ""));
        cinemaManager.getCinema("2").setMovie(new Movie("Inside Out 3", ""));
        cinemaManager.getCinema("3").setMovie(new Movie("John Wick 4", ""));
        cinemaManager.getCinema("4").setMovie(new Movie("Beauty and the Beast", ""));
    }
}
