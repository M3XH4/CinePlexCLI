import java.awt.*;
import java.io.IOException;
import java.util.*;
public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Initializing Variables
        Scanner input = new Scanner(System.in);
        CinemaManager cinemaManager = new CinemaManager();
        String horizontalLine = "================================================================================================================";
        int horizontalLineLength = horizontalLine.length();

        //Creating File
        FileManager.createFile();

        //Loading Cinema's Data From File And If It Is Empty Then Initialize CinemaManager If Not Then Load The Cinema
        //Manager With The Data From The File
        ArrayList<Cinema> loadCinema = FileManager.loadBookings();
        if (loadCinema.isEmpty()) {
            initializeCinemas(cinemaManager);
        } else {
            cinemaManager.setCinemas(loadCinema);
        }

        //Loading Printer Data
        PrintManager.printer = FileManager.loadPrinter();

        //Initializing Booleans For Looping
        boolean showTitle;
        boolean showAdminPage;
        boolean showCinemas;
        boolean showSeats;
        boolean admin_PageRunning = true;
        boolean admin_EditCRunning  = true;
        boolean admin_EditSRunning = true;


        //Main Window
        do {
            showTitle = true;
            if (showTitle) {
                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                System.out.println(Titles.title);
                System.out.println(FontManager.TEXT_BLACK + FontManager.BACKGROUND_RED + horizontalLine + FontManager.RESET);
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "        BOOK" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ":    Book Seats For A Movie"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - "        BOOK:    Book Seats For A Movie".length())));
                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "        BUY" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ":     Buy Snacks And Drinks" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - "        BUY:     Buy Snacks And Drinks".length())));
                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "        EXIT" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ":    Quit Application" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - "        EXIT:    Quit Application".length())));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
            }
            showTitle = false;

            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
            System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Which Option Would You Like To Do: " + FontManager.RESET);
            String choice = input.nextLine().toUpperCase();

            if (choice.equals("BOOK")) {
                showCinemas = true;
                do {
                    showSeats = true;
                    if (showCinemas) {
                        Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(50) + "CINEMAS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("CINEMAS".length() + 50)));
                        Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "        " + Global.spacerString(51, ("Cinema " + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "1: " + (!cinemaManager.getCinema("1").getMovie().getName().isEmpty() ? cinemaManager.getCinema("1").getMovie().getName() : "None"))) + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "               " + Global.spacerString(48, ("Cinema " + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "2: " + (!cinemaManager.getCinema("2").getMovie().getName().isEmpty() ? cinemaManager.getCinema("2").getMovie().getName() : "None"))) + "        " + FontManager.RESET);
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "        " + Global.spacerString(51, ("Cinema " + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "3: " + (!cinemaManager.getCinema("3").getMovie().getName().isEmpty() ? cinemaManager.getCinema("3").getMovie().getName() : "None"))) + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "               " + Global.spacerString(48, ("Cinema " + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "4: " + (!cinemaManager.getCinema("4").getMovie().getName().isEmpty() ? cinemaManager.getCinema("4").getMovie().getName() : "None"))) + "        " + FontManager.RESET);
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                    }
                    showCinemas = false;
                    Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                    System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Type Cinema Number or Movie Name To Select A Cinema or Type Back To Go Back To Main Page: ");
                    String cinemaChoice = input.nextLine();
                    if (cinemaChoice.equalsIgnoreCase("Back")) {
                        break;
                    }
                    Cinema selectedCinema = (cinemaManager.getCinema(cinemaChoice) != null) ? cinemaManager.getCinema(cinemaChoice) : cinemaManager.findCinemaByMovieName(cinemaChoice);
                    if (selectedCinema == null) {
                        System.out.println(FontManager.TEXT_RED_BRIGHT + FontManager.BOLD + FontManager.BACKGROUND_BLACK + Global.putSpaces(36) + "ERROR! Could Not Find Cinema" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("ERROR! Could Not Find Cinema".length() + 36)));
                    } else {
                        showCinemas = true;

                        Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + FontManager.TEXT_WHITE_BRIGHT + Global.putSpaces(44) + "Cinema " + selectedCinema.getId() + " Is Selected" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("Cinema Is " + selectedCinema.getId() + " Selected").length() + 44)));
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "         Movie:     " + FontManager.BOLD + selectedCinema.getMovie().getName() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("         Movie:     " + selectedCinema.getMovie().getName()).length()));

                        int lineLength = horizontalLineLength - 28;
                        ArrayList<String> wrappedDetails = wrapText(selectedCinema.getMovie().getDetails(), lineLength);
                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "         Synopsis:  " + FontManager.BOLD + wrappedDetails.getFirst() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("         Synopsis:  " + wrappedDetails.getFirst()).length()));
                        for (int i = 1; i < wrappedDetails.size(); i++) {
                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "                    " + FontManager.BOLD + wrappedDetails.get(i) + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("                    " + wrappedDetails.get(i)).length()));
                        }
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));

                        do {
                            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(53) +  "SEATS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("SEATS".length() + 53)));
                            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                            Cinema.displaySeats(selectedCinema.getSeats());
                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                            System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Type In Seat Number To Book Seat or Type In Back To Go Back Selecting Cinema: " + FontManager.RESET);
                            String seatNumber = input.nextLine();
                            if (seatNumber.equalsIgnoreCase("Back")) {
                                showTitle = true;
                                break;
                            }
                            Seat seat = Cinema.findSeat(selectedCinema.getSeats(), seatNumber);
                            if (seat != null) {
                                if (seat.isAvailable()) {
                                    PrintManager.print(selectedCinema.getMovie().getName(), selectedCinema.getId(), seat.getSeatID());
                                    Thread.sleep(300);

                                    seat.book();
                                    FileManager.saveBookings(cinemaManager.getCinemas());
                                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + FontManager.TEXT_WHITE_BRIGHT + Global.putSpaces(42) + "Successfully Booked Seat " + seat.getSeatID() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("Successfully Booked Seat " + seat.getSeatID()).length() + 42)));
                                    Thread.sleep(300);
                                } else {
                                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + FontManager.TEXT_RED_BRIGHT + Global.putSpaces(20) + "I'm Sorry But The Seat " + seat.getSeatID() + " Is Currently Booked. Please Pick Another Seat. " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("I'm Sorry But The Seat " + seat.getSeatID() + " Is Currently Booked. Please Pick Another Seat. " ).length() + 20)));
                                    Thread.sleep(300);
                                }
                            } else {
                                System.out.println(FontManager.TEXT_YELLOW_BRIGHT + FontManager.BACKGROUND_BLACK + Global.putSpaces(25) + "WARNING! Please Input The Right Seat Number. PLease Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! Please Input The Right Seat Number. PLease Try Again...".length() + 25)));
                                Thread.sleep(300);
                            }
                        } while (true);


                    }
                } while (true);
            } else if (choice.equals("BUY")) {
                break;
            } else if (choice.equals("LOGIN")) {
                showAdminPage = true;

                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 43) + FontManager.BACKGROUND_BLACK + FontManager.BOLD + "ADMIN LOGIN" + FontManager.RESET + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("ADMIN LOGIN".length() + 43)));
                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "    Enter Username: " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    Enter Username: ".length()));
                String username = input.nextLine();
                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "    Enter Password: " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    Enter Password: ".length()));
                String password = input.nextLine();
                if (username.equals("manager") && password.equals("man123")) {
                    System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLineLength));
                    System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLineLength));
                    do {
                        if (showAdminPage) {
                            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                            System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLineLength));
                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(48) + "WELCOME ADMIN" + FontManager.RESET + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("WELCOME ADMIN".length() + 48))));
                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(8) + "CINEMAS" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ":    Edit Cinemas"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("CINEMAS:    Edit Cinemas".length() + 8))));
                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(8) + "SNACKS" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ":     Edit Snacks/Drinks"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("SNACKS:     Edit Snacks/Drinks".length() + 8))));
                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(8) + "PRINTER" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ":    Edit Printer Settings"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("PRINTER:    Edit Printer Settings".length() + 8))));
                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(8) + "BACK" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ":       Go Back In Main Page"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("BACK:       Go Back In Main Page".length() + 8))));
                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        }
                        showAdminPage = false;
                        Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                        System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Which Option Would You Like To Do: " + FontManager.RESET);
                        String managerChoice = input.nextLine().toUpperCase();
                        if (managerChoice.equals("CINEMAS")) {
                            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(45) + "EDIT CINEMA" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("EDIT CINEMA".length() + 45)));
                            do {
                                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                try {
                                    System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Enter Cinema Number: " + FontManager.RESET);
                                    String cinemaID = input.nextLine();
                                    Cinema cinema = cinemaManager.getCinema(cinemaID);

                                    do {
                                        Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(44) + "Cinema " + cinema.getId() + " Is Selected" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("Cinema Is " + cinema.getId() + " Selected").length() + 44)));
                                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "    Movie Showing:          " + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + (!cinema.getMovie().getName().isEmpty() ? cinema.getMovie().getName() : "None") + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("    Movie Showing:          " + (!cinema.getMovie().getName().isEmpty() ? cinema.getMovie().getName() : "None")).length())));
                                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "    Total Number of Seats:  " + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + cinema.getSeats().size() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("    Total Number of Seats:  " +  cinema.getSeats().size()).length())));
                                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "    Available Seats:        " + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + cinema.totalAvailableSeats() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("    Available Seats:        " +  cinema.totalAvailableSeats()).length())));
                                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "    Booked Seats:           " + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + cinema.totalBookedSeats() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("    Booked Seats:           " +  cinema.totalBookedSeats()).length())));
                                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                                        Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                        System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "                    CHANGE" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ": Change Movie" + Global.putSpaces(18) + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "BACK" + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ": To Go Back To Admin Page" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 23));

                                        Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                        System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Which Option Would You Like To Do: " + FontManager.RESET);
                                        String cinemaChoice = input.nextLine().toUpperCase();
                                        if (cinemaChoice.equals("CHANGE")) {
                                            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + Global.putSpaces(44) + "CHANGE MOVIE" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("CHANGE MOVIE".length() + 44)));
                                            Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));

                                            System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Enter New Movie Name: " + FontManager.RESET);
                                            String movieName = input.nextLine();
                                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_YELLOW_BRIGHT + FontManager.BOLD + "    WARNING! Changing The Movie Will Cancel Bookings Of This Cinema" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK,  horizontalLineLength - ("    WARNING! Changing The Movie Will Cancel Bookings Of This Cinema").length()));
                                            System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "    Would You Like To Continue? (Yes/No)" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    Would You Like To Continue? (Yes/No)".length()));
                                            do {
                                                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                                System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Your Response - " + FontManager.RESET);
                                                String warningChoice = input.nextLine().toUpperCase();
                                                if (warningChoice.isBlank()) {
                                                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_YELLOW_BRIGHT + "    WARNING! Please Don't Input Blank Texts. Please Try Again. " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    WARNING! Don't Input Blank Texts ".length()));
                                                }
                                                else if (warningChoice.equals("YES")) {
                                                    Movie movie = MovieAPI.fetchMovie(movieName);
                                                    if (movie != null) {
                                                        cinema.setMovie(movie);
                                                        cinema.setSeats(new ArrayList<>());
                                                        cinema.putSeats();
                                                        System.out.println(FontManager.TEXT_WHITE_BRIGHT + "Changed The Movie To " + cinema.getMovie().getName() + FontManager.RESET);

                                                        FileManager.saveBookings(cinemaManager.getCinemas());
                                                    }
                                                    break;

                                                } else if (warningChoice.equals("NO")) {
                                                    break;
                                                }

                                            } while (true);
                                        } else if (cinemaChoice.equals("BACK")) {
                                            showAdminPage = true;
                                            admin_EditCRunning = false;
                                            break;
                                        }
                                    } while (true);

                                } catch (NullPointerException e) {
                                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_RED + Global.putSpaces(36) + "Cinema Not Found. Please Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("Cinema Not Found. Please Try Again...".length() + 36)));
                                    input.reset();
                                }
                            } while (admin_EditCRunning);
                        } else if (managerChoice.equals("SNACKS")) {

                        } else if (managerChoice.equals("PRINTER")) {
                            do {
                                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                System.out.println(FontManager.BACKGROUND_BLACK + FontManager.BOLD + FontManager.TEXT_WHITE_BRIGHT + Global.putSpaces(48) + "PRINTERS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length() - ("PRINTERS".length() + 48)));
                                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                PrintManager.displayPrinters();
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                PrintManager.displayCurrentPrinter();
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                Global.putHorizontalLine(FontManager.BACKGROUND_RED + FontManager.TEXT_BLACK, horizontalLineLength);
                                System.out.print(FontManager.TEXT_WHITE_BRIGHT + FontManager.BOLD + "Type In The Number of A Printer To Change Current Printer Or Type In Back To Go Back To Admin Page: " + FontManager.BOLD);
                                String printerChoice = input.nextLine();
                                if (printerChoice.equalsIgnoreCase("Back")) {
                                    showAdminPage = true;
                                    break;
                                }
                                try {
                                    int printerCode = (Integer.parseInt(printerChoice)) -1;

                                    if (printerCode >= 0 && printerCode < PrintManager.printServices.length) {
                                        PrintManager.printer = printerCode;
                                        FileManager.savePrinter(printerCode);
                                    } else {
                                        throw new NumberFormatException();
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_YELLOW_BRIGHT + FontManager.BOLD + "    WARNING! Please Input The Right Code. Please Try Again" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK,  horizontalLineLength - ("    WARNING! Please Input The Right Code. Please Try Again").length()));
                                }
                            } while (true);
                        } else if (managerChoice.equals("BACK")) {
                            showTitle = true;
                            admin_PageRunning = false;
                            break;
                        }
                    } while (admin_PageRunning);
                } else {
                    System.out.println(FontManager.BACKGROUND_BLACK + FontManager.TEXT_RED + FontManager.BOLD + Global.putSpaces(37) + "Incorrect Username Or Password" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("Incorrect Username Or Password".length() + 37)));
                    showTitle = true;
                }
            } else if (choice.equals("EXIT")) {
                break;
            }
        } while (true);
    }

    //Function For Initializing Cinema
    private static void initializeCinemas(CinemaManager cinemaManager) throws IOException {
        cinemaManager.addCinema(new Cinema("1"));
        cinemaManager.addCinema(new Cinema("2"));
        cinemaManager.addCinema(new Cinema("3"));
        cinemaManager.addCinema(new Cinema("4"));
        cinemaManager.getCinema("1").setMovie(MovieAPI.fetchMovie("Avengers: Endgame"));
        cinemaManager.getCinema("2").setMovie(MovieAPI.fetchMovie("Inside Out"));
        cinemaManager.getCinema("3").setMovie(MovieAPI.fetchMovie("John Wick"));
        cinemaManager.getCinema("4").setMovie(MovieAPI.fetchMovie("Saw X"));
    }

    //Function For Wrapping Text
    private static ArrayList<String> wrapText(String text, int lineLength) {
        ArrayList<String> wrappedString = new ArrayList<>();
        int start = 0;

        while (start < text.length()) {
            int end = Math.min(start + lineLength, text.length());
            if (end < text.length() && !Character.isWhitespace(text.charAt(end))) {
                while (end > start && !Character.isWhitespace(text.charAt(end))) {
                    end--;
                }
            }
            wrappedString.add(text.substring(start, end).trim());
            start = end + 1;
        }
        return wrappedString;
    }
}
