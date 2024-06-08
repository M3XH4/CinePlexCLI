import java.awt.*;
import java.io.IOException;
import java.util.*;
public class MainClass {
    private static final String horizontalLine = "================================================================================================================";
    public static int horizontalLineLength = horizontalLine.length();

    public static void main(String[] args) throws IOException, InterruptedException {
        //Initializing Variables
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();

        //Creating File
        FileManager.createFile();

        CinemaManager cinemaManager = new CinemaManager();
        //Loading Cinema's Data From File And If It Is Empty Then Initialize CinemaManager If Not Then Load The Cinema
        //Admin With The Data From The File
        ArrayList<Cinema> loadCinema = FileManager.loadBookings();
        if (loadCinema.isEmpty()) {
            initializeCinemas(cinemaManager);
        } else {
            cinemaManager.setCinemas(loadCinema);
        }
        ProductManager productManager = new ProductManager();
        ArrayList<Product> loadProducts = FileManager.loadProducts();

        if (loadProducts == null) {
            initializeProducts(productManager);
        } else {
            productManager.setProducts(loadProducts);
        }
        //Loading Printer Data
        PrintManager.printer = FileManager.loadPrinter();

        //Initializing Booleans For Looping
        boolean showTitle;
        boolean showAdminPage;
        boolean showCinemas;
        boolean admin_PageRunning = true;
        boolean admin_EditCRunning  = true;
        boolean admin_EditSRunning = true;

        //Main Window
        do {
            showTitle = true;
            if (showTitle) {
                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                System.out.println(Titles.title);
                System.out.println(FontManager.tertiaryCombo + horizontalLine + FontManager.RESET);
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(FontManager.primaryCombo + "        BOOK" + FontManager.RESET + FontManager.secondaryCombo + ":    Book Seats For A Movie"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - "        BOOK:    Book Seats For A Movie".length())));
                System.out.println(FontManager.primaryCombo + "        BUY" + FontManager.RESET + FontManager.secondaryCombo + ":     Buy Snacks And Drinks" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - "        BUY:     Buy Snacks And Drinks".length())));
                System.out.println(FontManager.primaryCombo + "        EXIT" + FontManager.RESET + FontManager.secondaryCombo + ":    Quit Application" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - "        EXIT:    Quit Application".length())));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
            }
            showTitle = false;

            Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
            System.out.print(FontManager.responseCombo + "Which Option Would You Like To Do: " + FontManager.RESET);
            String choice = input.nextLine().toUpperCase();

            if (choice.equals("BOOK")) {
                do {
                    showCinemas = true;
                    if (showCinemas) {
                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                        System.out.println(FontManager.primaryCombo + Global.putSpaces(50) + "CINEMAS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("CINEMAS".length() + 50)));
                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        System.out.println(FontManager.secondaryCombo + "        " + Global.spacerString(51, ("Cinema " + FontManager.primaryCombo + "1: " + (!cinemaManager.getCinema("1").getMovie().getName().isEmpty() ? cinemaManager.getCinema("1").getMovie().getName() : "None"))) + FontManager.RESET + FontManager.secondaryCombo + "               " + Global.spacerString(50, ("Cinema " + FontManager.primaryCombo + "2: " + (!cinemaManager.getCinema("2").getMovie().getName().isEmpty() ? cinemaManager.getCinema("2").getMovie().getName() : "None"))) + "        " + FontManager.RESET);
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        System.out.println(FontManager.secondaryCombo + "        " + Global.spacerString(51, ("Cinema " + FontManager.primaryCombo + "3: " + (!cinemaManager.getCinema("3").getMovie().getName().isEmpty() ? cinemaManager.getCinema("3").getMovie().getName() : "None"))) + FontManager.RESET + FontManager.secondaryCombo + "               " + Global.spacerString(50, ("Cinema " + FontManager.primaryCombo + "4: " + (!cinemaManager.getCinema("4").getMovie().getName().isEmpty() ? cinemaManager.getCinema("4").getMovie().getName() : "None"))) + "        " + FontManager.RESET);
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                    }
                    showCinemas = false;
                    Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                    System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Type Cinema Number or Movie Name To Select A Cinema or Type Back To Go Back To Main Page: ");
                    String cinemaChoice = input.nextLine();
                    if (cinemaChoice.equalsIgnoreCase("Back")) {
                        break;
                    }
                    Cinema selectedCinema = (cinemaManager.getCinema(cinemaChoice) != null) ? cinemaManager.getCinema(cinemaChoice) : cinemaManager.findCinemaByMovieName(cinemaChoice);
                    if (selectedCinema == null) {
                        System.out.println(FontManager.errorCombo + Global.putSpaces(36) + "ERROR! Could Not Find Cinema" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("ERROR! Could Not Find Cinema".length() + 36)));
                    } else {

                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        System.out.println(FontManager.primaryCombo + Global.putSpaces(44) + "Cinema " + selectedCinema.getId() + " Is Selected" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("Cinema Is " + selectedCinema.getId() + " Selected").length() + 44)));
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
                            printDisplaySeats(selectedCinema);
                            System.out.print(FontManager.TEXT_WHITE_BRIGHT + "Type In Seat Number To Book Seat or Type In Back To Go Back Selecting Cinema: " + FontManager.RESET);
                            String seatNumber = input.nextLine();
                            if (seatNumber.equalsIgnoreCase("Back")) {
                                break;
                            }
                            Seat seat = Cinema.findSeat(selectedCinema.getSeats(), seatNumber);
                            if (seat != null) {
                                if (seat.isAvailable()) {
                                    PrintManager.print(selectedCinema.getMovie().getName(), selectedCinema.getId(), seat.getSeatID());
                                    Thread.sleep(300);

                                    seat.book();
                                    FileManager.saveBookings(cinemaManager.getCinemas());
                                    System.out.println(FontManager.primaryCombo + Global.putSpaces(42) + "Successfully Booked Seat " + seat.getSeatID() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("Successfully Booked Seat " + seat.getSeatID()).length() + 42)));
                                    Thread.sleep(300);
                                } else {
                                    System.out.println(FontManager.errorCombo + Global.putSpaces(20) + "I'm Sorry But The Seat " + seat.getSeatID() + " Is Currently Booked. Please Pick Another Seat. " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("I'm Sorry But The Seat " + seat.getSeatID() + " Is Currently Booked. Please Pick Another Seat. " ).length() + 20)));
                                    Thread.sleep(300);
                                }
                            } else {
                                System.out.println(FontManager.warningCombo + Global.putSpaces(25) + "WARNING! Please Input The Right Seat Number. PLease Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! Please Input The Right Seat Number. PLease Try Again...".length() + 25)));
                                Thread.sleep(300);
                            }
                        } while (true);
                    }
                } while (true);
            } else if (choice.equals("BUY")) {
                do {
                    productManager.displaySnacks();
                    productManager.displayDrinks();
                } while (true);
            } else if (choice.equals("LOGIN")) {
                String username = "";
                String password = "";

                if (!admin.isActive()) {
                    Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                    System.out.println(FontManager.primaryCombo + Global.putSpaces(43) + "ADMIN LOGIN" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("ADMIN LOGIN".length() + 43)));
                    Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                    System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                    System.out.println(FontManager.primaryCombo + "    Enter Username: " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    Enter Username: ".length()));
                    username = input.nextLine();
                    System.out.println(FontManager.primaryCombo + "    Enter Password: " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    Enter Password: ".length()));
                    password = input.nextLine();
                    System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLineLength));
                }

                if ((username.equals(admin.getUsername()) && password.equals(admin.getPassword())) || admin.isActive()) {
                    admin.setActive(true);
                    do {
                        showAdminPage = true;
                        if (showAdminPage) {
                            Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                            System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLineLength));
                            System.out.println(FontManager.primaryCombo + Global.putSpaces(48) + "WELCOME ADMIN" + FontManager.RESET + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("WELCOME ADMIN".length() + 48))));
                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                            System.out.println(FontManager.primaryCombo + Global.putSpaces(8) + "CINEMAS" + FontManager.RESET + FontManager.secondaryCombo + ":    Edit Cinemas"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("CINEMAS:    Edit Cinemas".length() + 8))));
                            System.out.println(FontManager.primaryCombo + Global.putSpaces(8) + "SNACKS" + FontManager.RESET + FontManager.secondaryCombo + ":     Edit Snacks/Drinks"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("SNACKS:     Edit Snacks/Drinks".length() + 8))));
                            System.out.println(FontManager.primaryCombo + Global.putSpaces(8) + "PRINTER" + FontManager.RESET + FontManager.secondaryCombo + ":    Edit Printer Settings"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("PRINTER:    Edit Printer Settings".length() + 8))));
                            System.out.println(FontManager.primaryCombo + Global.putSpaces(8) + "BACK" + FontManager.RESET + FontManager.secondaryCombo + ":       Go Back To Main Page"  + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, (horizontalLineLength - ("BACK:       Go Back To Main Page".length() + 8))));
                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                        }
                        showAdminPage = false;

                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                        System.out.print(FontManager.responseCombo + "Which Option Would You Like To Do: " + FontManager.RESET);
                        String managerChoice = input.nextLine().toUpperCase();

                        if (managerChoice.equals("CINEMAS")) {
                            Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                            System.out.println(FontManager.primaryCombo + Global.putSpaces(45) + "EDIT CINEMA" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("EDIT CINEMA".length() + 45)));
                            do {
                                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                try {
                                    System.out.print(FontManager.secondaryCombo + "Enter Cinema Number (1/2/3/4):" + FontManager.RESET + "  ");
                                    String cinemaID = input.nextLine();
                                    Cinema cinema = cinemaManager.getCinema(cinemaID);

                                    do {
                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                                        System.out.println(FontManager.primaryCombo + Global.putSpaces(44) + "Cinema " + cinema.getId() + " Is Selected" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("Cinema Is " + cinema.getId() + " Selected").length() + 44)));
                                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                                        System.out.println(FontManager.secondaryCombo + "    Movie Showing:          " + FontManager.BOLD + (!cinema.getMovie().getName().isEmpty() ? cinema.getMovie().getName() : "None") + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("    Movie Showing:          " + (!cinema.getMovie().getName().isEmpty() ? cinema.getMovie().getName() : "None")).length())));
                                        System.out.println(FontManager.secondaryCombo + "    Total Number of Seats:  " + FontManager.BOLD + cinema.getSeats().size() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("    Total Number of Seats:  " +  cinema.getSeats().size()).length())));
                                        System.out.println(FontManager.secondaryCombo + "    Available Seats:        " + FontManager.BOLD + cinema.totalAvailableSeats() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("    Available Seats:        " +  cinema.totalAvailableSeats()).length())));
                                        System.out.println(FontManager.secondaryCombo + "    Booked Seats:           " + FontManager.BOLD + cinema.totalBookedSeats() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("    Booked Seats:           " +  cinema.totalBookedSeats()).length())));
                                        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                        System.out.println(FontManager.primaryCombo + "     CANCEL" + FontManager.RESET + FontManager.secondaryCombo + ": Cancel Bookings of Seats" + FontManager.primaryCombo + Global.putSpaces(10) + "CHANGE" + FontManager.RESET + FontManager.secondaryCombo + ": Change Movie" + FontManager.primaryCombo + Global.putSpaces(10) + "BACK" + FontManager.RESET + FontManager.secondaryCombo + ": To Go Back To Admin Page" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 5));

                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                        System.out.print(FontManager.responseCombo + "Which Option Would You Like To Do: " + FontManager.RESET);
                                        String cinemaChoice = input.nextLine().toUpperCase();
                                        if (cinemaChoice.equals("CANCEL")) {
                                            do {
                                                printDisplaySeats(cinema);
                                                System.out.print(FontManager.secondaryCombo + "Type In Seat Number To Cancel Booking Or Type In Back To Go Back To Admin Page:" + FontManager.RESET + "  ");
                                                String cancelChoice = input.nextLine();
                                                if (cancelChoice.equalsIgnoreCase("Back")) {
                                                    break;
                                                }
                                                Seat seat = Cinema.findSeat(cinema.getSeats(), cancelChoice);
                                                if (seat != null) {
                                                    if (!seat.isAvailable()) {
                                                        System.out.println(FontManager.warningCombo + "    WARNING! This Will Cancel The Booking of Seat " + seat.getSeatID() + "." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("    WARNING! This Will Cancel The Booking of Seat " + seat.getSeatID() + ".").length()));
                                                        System.out.println(FontManager.primaryCombo + "    Would You Like To Continue (Yes/No): " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("    Would You Like To Continue (Yes/No): ").length()));
                                                        do {
                                                            Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                                            System.out.print(FontManager.responseCombo + "Your Response - " + FontManager.RESET);
                                                            String warningChoice = input.nextLine().toUpperCase();
                                                            if (warningChoice.isBlank()) {
                                                                System.out.println(FontManager.warningCombo + "    WARNING! Please Don't Input Blank Texts. Please Try Again. " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    WARNING! Don't Input Blank Texts ".length()));
                                                            } else if (warningChoice.equals("YES")) {
                                                                cinema.cancelSeat(seat.getSeatID());
                                                                FileManager.saveBookings(cinemaManager.getCinemas());
                                                                Thread.sleep(300);
                                                                System.out.println(FontManager.primaryCombo + "Successfully Cancelled The Booking of Seat " + seat.getSeatID() + "." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("Successfully Cancelled The Booking of Seat " + seat.getSeatID() + "." ).length()));
                                                                break;
                                                            } else if (warningChoice.equals("NO")) {
                                                                break;
                                                            }
                                                        } while (true);
                                                    } else {
                                                        System.out.println(FontManager.warningCombo + "WARNING! Seat Is Still Available, Cannot Cancel Booking. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Seat Is Still Available, Cannot Cancel Booking. Please Try Again.".length()));
                                                    }
                                                } else {
                                                    System.out.println(FontManager.warningCombo + Global.putSpaces(25) + "WARNING! Please Input The Right Seat Number. PLease Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! Please Input The Right Seat Number. PLease Try Again...".length() + 25)));
                                                    Thread.sleep(300);
                                                }
                                            } while (true);
                                        } else if (cinemaChoice.equals("CHANGE")) {
                                            Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                            System.out.println(FontManager.primaryCombo + Global.putSpaces(44) + "CHANGE MOVIE" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("CHANGE MOVIE".length() + 44)));
                                            Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                            System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));

                                            System.out.print(FontManager.responseCombo + "Enter New Movie Name: " + FontManager.RESET);
                                            String movieName = input.nextLine();
                                            System.out.println(FontManager.warningCombo + "    WARNING! Changing The Movie Will Cancel Bookings Of This Cinema" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK,  horizontalLineLength - ("    WARNING! Changing The Movie Will Cancel Bookings Of This Cinema").length()));
                                            System.out.println(FontManager.primaryCombo + "    Would You Like To Continue? (Yes/No)" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    Would You Like To Continue? (Yes/No)".length()));
                                            do {
                                                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                                System.out.print(FontManager.responseCombo + "Your Response - " + FontManager.RESET);
                                                String warningChoice = input.nextLine().toUpperCase();
                                                if (warningChoice.isBlank()) {
                                                    System.out.println(FontManager.warningCombo + "    WARNING! Please Don't Input Blank Texts. Please Try Again. " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    WARNING! Don't Input Blank Texts ".length()));
                                                } else if (warningChoice.equals("YES")) {
                                                    Movie movie = MovieAPI.fetchMovie(movieName);
                                                    if (movie != null) {
                                                        cinema.setMovie(movie);
                                                        cinema.setSeats(new ArrayList<>());
                                                        cinema.putSeats();
                                                        Thread.sleep(300);
                                                        System.out.println(FontManager.responseCombo + "Changed The Movie To " + cinema.getMovie().getName() + FontManager.RESET);
                                                        FileManager.saveBookings(cinemaManager.getCinemas());
                                                    } else {
                                                        System.out.println(FontManager.errorCombo + "    ERROR! Could Not Find " + movieName + " Movie, Please Input The Right Movie Name. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("    ERROR! Could Not Find " + movieName + " Movie, Please Input The Right Movie Name. Please Try Again.").length()));
                                                    }
                                                    break;
                                                } else if (warningChoice.equals("NO")) {
                                                    break;
                                                }

                                            } while (true);
                                        } else if (cinemaChoice.equals("BACK")) {
                                            admin_EditCRunning = false;
                                            break;
                                        }
                                    } while (true);

                                } catch (NullPointerException e) {
                                    System.out.println(FontManager.errorCombo + Global.putSpaces(36) + "Cinema Not Found. Please Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("Cinema Not Found. Please Try Again...".length() + 36)));
                                    input.reset();
                                }

                            } while (admin_EditCRunning);
                        } else if (managerChoice.equals("SNACKS")) {

                        } else if (managerChoice.equals("PRINTER")) {
                            do {
                                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                System.out.println(FontManager.primaryCombo + Global.putSpaces(48) + "PRINTERS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length() - ("PRINTERS".length() + 48)));
                                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                PrintManager.displayPrinters();
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                PrintManager.displayCurrentPrinter();
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                System.out.print(FontManager.responseCombo + "Type In The Number of A Printer To Change Current Printer Or Type In Back To Go Back To Admin Page: " + FontManager.RESET);
                                String printerChoice = input.nextLine();
                                if (printerChoice.equalsIgnoreCase("Back")) {
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
                                    System.out.println(FontManager.warningCombo + "    WARNING! Please Input The Right Code. Please Try Again" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK,  horizontalLineLength - ("    WARNING! Please Input The Right Code. Please Try Again").length()));
                                }
                            } while (true);
                        } else if (managerChoice.equals("BACK")) {
                            admin_PageRunning = false;
                            break;
                        }
                    } while (admin_PageRunning);
                } else {
                    System.out.println(FontManager.errorCombo + Global.putSpaces(37) + "Incorrect Username Or Password" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("Incorrect Username Or Password".length() + 37)));
                    Thread.sleep(300);
                }
            } else if (choice.equals("EXIT")) {
                break;
            }
        } while (true);
    }
    //Function to Display Seats
    private static void printDisplaySeats(Cinema cinema) {
        Global.putHorizontalLine(FontManager.secondaryCombo, horizontalLineLength);
        System.out.println(FontManager.primaryCombo + Global.putSpaces(53) +  "SEATS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("SEATS".length() + 53)));
        Global.putHorizontalLine(FontManager.secondaryCombo, horizontalLineLength);
        System.out.println(FontManager.primaryCombo + "|" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - 2) + FontManager.primaryCombo + "|" +FontManager.RESET);
        Cinema.displaySeats(cinema.getSeats());
        System.out.println(FontManager.primaryCombo + "|" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - 2) + FontManager.primaryCombo + "|" +FontManager.RESET);
        Global.putHorizontalLine(FontManager.secondaryCombo, horizontalLineLength);
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
    //Function For Initializing Products
    private static void initializeProducts(ProductManager productManager) {
        productManager.addProduct(new Snack("Lay's Original Chip", 14.00));
        productManager.addProduct(new Snack("Cookies", 18.00));
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
