import java.io.IOException;
import java.util.*;
public class MainClass {
    private static final String horizontalLine = "================================================================================================================";
    public static int horizontalLineLength = horizontalLine.length();

    // Initializing Cinema Manager Object
    public static CinemaManager cinemaManager = new CinemaManager();
    // Initializing Product Manager Object
    public static ProductManager productManager = new ProductManager();

    public static void main(String[] args) throws IOException, InterruptedException {
        // Declaring Variables
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();

        // Declaring Booleans For Showing Specified Displays
        boolean showTitle;
        boolean showAdminPage;
        boolean showCinemas;
        // Declaring Booleans For Looping
        boolean shopRunning;
        boolean adminPageRunning = true;
        boolean adminEditCRunning  = true;
        boolean adminEditSRunning = true;
        // Creating File If Files Exists Then Don't Create File But If It Exists
        // Then Create A File For The File That Does Not Exist
        FileManager.createFile();

        // Loading Cinema's Data From File And If It Is Empty Then Initialize CinemaManager If Not Then Load The Cinema
        // Manager With The Data From The File
        ArrayList<Cinema> loadCinema = FileManager.loadBookings();
        // If File Is Empty Then Load The Cinema Manager With Default Values Else Then Put The Values On The File
        // To The File Manager
        if (loadCinema.isEmpty()) {
            initializeCinemas(cinemaManager);
        } else {
            cinemaManager.setCinemas(loadCinema);
        }

        //Loading Product's Data From File And If It Is Empty Then Initialize CinemaManager If Not Then Load The Product
        //Manager With The Data From The File
        ArrayList<Product> loadProducts = FileManager.loadProducts();
        if (loadProducts != null && !loadProducts.isEmpty()) {
            productManager.setProducts(loadProducts);
        } else {
            initializeProducts(productManager);
        }

        //Loading Printer Data To Declare What Printer Is Being Used
        PrintManager.printer = FileManager.loadPrinter();

        /* Initiate Looping on This Part So That It Would Always Execute The Code Blocks
           Again and Again Unless The User Chooses To Exit The Application Which Will Stop
           The Loop And Terminate The Program
        */
        do {
            showTitle = true;
            //Displaying Title Page
            if (showTitle) {
                Titles.displayTitle();
            }
            showTitle = false;

            //Asking User Which Option Would He Like To Do Either Booking, Buying, Or Exit Application
            Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
            System.out.print(FontManager.responseCombo + "Which Option Would You Like To Do: " + FontManager.RESET);
            String choice = input.nextLine().toUpperCase();
            //On This If-ElseIf-Else Statement
            if (choice.equals("BOOK")) {
                do {
                    showCinemas = true;
                    if (showCinemas) {
                        displayCinemas(cinemaManager);
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
                            System.out.println(FontManager.primaryCombo + "Each Seat Is Priced At 250.00 PHP." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("Each Seat Is Priced At 250.00 PHP.").length()));
                            Global.putHorizontalLine(FontManager.primaryCombo, horizontalLineLength);
                            System.out.print(FontManager.responseCombo + "Type In Seat Number To Book Seat or Type In Back To Go Back Selecting Cinema: " + FontManager.RESET);
                            String seatNumber = input.nextLine();
                            if (seatNumber.equalsIgnoreCase("Back")) {
                                break;
                            }
                            handleSeatReservation(cinemaManager, selectedCinema, seatNumber, "BOOK");
                        } while (true);
                    }
                } while (true);
            } else if (choice.equals("BUY")) {
                shopRunning = true;
                double total = 0;
                int loopRun = 0;
                do {
                    productManager.displaySnacks();
                    productManager.displayDrinks();
                    Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                    System.out.print(FontManager.responseCombo + "Type In The Name Of The Product To Purchase It or Type in Back To Go Back To Main Page");
                    if (loopRun > 0) {
                        System.out.print(FontManager.responseCombo + " Or Type In Checkout\nTo Proceed To Purchase Items: " + FontManager.RESET);
                    } else {
                        System.out.print(": " + FontManager.RESET);
                    }
                    String purchaseChoice = input.nextLine();
                    if (purchaseChoice.equalsIgnoreCase("Back")) {
                        break;
                    } else if (purchaseChoice.equalsIgnoreCase("Checkout")) {
                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                        System.out.println(FontManager.primaryCombo + String.format("Total Cost: %.2f PHP", total) + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - String.format("Total Cost: %.2f PHP", total).length()));
                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                        if (handlePayment(true, total)) {
                            shopRunning = false;
                        }
                        break;
                    } else {
                        Product selectedProduct = productManager.findProduct(purchaseChoice);
                        if (selectedProduct == null) {
                            System.out.println(FontManager.warningCombo + "WARNING! Please Type In The Right Product Name. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Please Type In The Right Product Name. Please Try Again.".length()));
                            continue;
                        }
                        Drinks.Size selectedSize = productManager.getDrinksSize(selectedProduct);
                        int quantity = getQuantity(selectedProduct);

                        if (selectedProduct instanceof Drinks drinks) {
                            productManager.getSizes().add(selectedSize);
                            total += drinks.getPrices().get(selectedSize) * quantity;
                        } else {
                            productManager.getSizes().add(null);
                            total += selectedProduct.getPrice() * quantity;
                        }

                        productManager.getShoppingCart().add(selectedProduct);
                        productManager.getQuantities().add(quantity);

                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                        System.out.println(FontManager.primaryCombo + String.format("Total Cost: %.2f PHP", total) + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - String.format("Total Cost: %.2f PHP", total).length()));
                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                        do {
                            System.out.print(FontManager.responseCombo + "Do You Want To Add Another Product (Yes/No): " + FontManager.RESET);
                            String addChoice = input.nextLine().toUpperCase();
                            if (addChoice.equals("YES")) {
                                loopRun++;
                                break;
                            }
                            if (addChoice.equals("NO")) {
                                if (handlePayment(true, total)) {
                                    shopRunning = false;
                                }
                                break;
                            } else {
                                System.out.println(FontManager.warningCombo + "WARNING! Please Enter The Right Choices. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Please Enter The Right Choices. Please Try Again.".length()));
                                continue;
                            }
                        } while (true);
                    }
                } while (shopRunning);
            } else if (choice.equals("LOGIN")) {
                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                System.out.println(FontManager.primaryCombo + Global.putSpaces(43) + "ADMIN LOGIN" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("ADMIN LOGIN".length() + 43)));
                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
                System.out.print(FontManager.primaryCombo + "    Enter Username: " + FontManager.RESET);
                String username = input.nextLine();
                System.out.print(FontManager.primaryCombo + "    Enter Password: " + FontManager.RESET);
                String password = input.nextLine();
                System.out.println(Global.putBackgroundColor( FontManager.BACKGROUND_BLACK, horizontalLineLength));

                if ((username.equals(admin.getUsername()) && password.equals(admin.getPassword()))) {
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
                            System.out.println(FontManager.primaryCombo + Global.putSpaces(48) + "EDIT CINEMA" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("EDIT CINEMA".length() + 48)));
                            do {
                                try {
                                    displayCinemas(cinemaManager);
                                    System.out.print(FontManager.secondaryCombo + "Type In Cinema Number (1/2/3/4):" + FontManager.RESET + "  ");
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
                                                handleSeatReservation(cinemaManager, cinema, cancelChoice, "CANCEL");
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
                                            adminEditCRunning = false;
                                            break;
                                        }
                                    } while (true);

                                } catch (NullPointerException e) {
                                    System.out.println(FontManager.errorCombo + Global.putSpaces(36) + "Cinema Not Found. Please Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("Cinema Not Found. Please Try Again...".length() + 36)));
                                    input.reset();
                                }
                            } while (adminEditCRunning);
                        } else if (managerChoice.equals("SNACKS")) {
                            do {
                                productManager.displaySnacks();
                                productManager.displayDrinks();
                                Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                System.out.println(FontManager.primaryCombo + "    ADD" + FontManager.secondaryCombo + ":    To Add Products" + Global.putSpaces(57) + FontManager.primaryCombo + "EDIT" + FontManager.TEXT_WHITE_BRIGHT + ": Edit Products" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 9));
                                System.out.println(FontManager.primaryCombo + "    DELETE" + FontManager.secondaryCombo + ": To Delete Products" + Global.putSpaces(54) + FontManager.primaryCombo + "BACK" + FontManager.TEXT_WHITE_BRIGHT + ": Back To Admin Page" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, 4));
                                System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLine.length()));
                                do {
                                    Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                    System.out.print(FontManager.responseCombo + "Choose Which Option Would You Like To Do: " + FontManager.RESET);
                                    String snacksChoice = input.nextLine().toUpperCase();
                                    if (snacksChoice.equals("ADD")) {
                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                        System.out.println(FontManager.primaryCombo + Global.putSpaces(50) + "ADD PRODUCT" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("ADD PRODUCT").length() + 50)));
                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);

                                        do {
                                            System.out.print(FontManager.responseCombo + "Type In The Name Of The Product Or Type In Back To Go Back To Selection: " + FontManager.RESET);
                                            String nameChoice = input.nextLine();
                                            if (nameChoice.equalsIgnoreCase("Back")) {
                                                break;
                                            }
                                            boolean nameExist = false;

                                            for (Product product : productManager.getProducts()) {
                                                if (product.getName().equalsIgnoreCase(nameChoice)) {
                                                    nameExist = true;
                                                    System.out.println(FontManager.warningCombo + "WARNING! The Name " + nameChoice + " Already Exists. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! The Name " + nameChoice + " Already Exists. Please Try Again.").length()));
                                                    Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                                    break;
                                                }
                                            }

                                            if (!nameExist) {
                                                System.out.print(FontManager.responseCombo + "What Is The Type Of The Product (Snacks/Drinks): " + FontManager.RESET);
                                                String type = input.nextLine().toUpperCase();
                                                if (type.equals("SNACKS")) {
                                                    do {
                                                        try {
                                                            System.out.print(FontManager.responseCombo + "What Is The Price Of " + nameChoice + ": " + FontManager.RESET);
                                                            double price = input.nextDouble();
                                                            input.nextLine();
                                                            productManager.addProduct(new Snack(nameChoice, price));
                                                            FileManager.saveProducts(productManager.getProducts());
                                                            break;
                                                        } catch (NumberFormatException e) {
                                                            System.out.println(FontManager.warningCombo + "WARNING! Invalid Input. Please Enter A Valid Amount." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Invalid Input. Please Enter A Valid Amount.".length()));
                                                        }
                                                    } while (true);
                                                    break;
                                                } else if (type.equals("DRINKS")) {
                                                    ArrayList<Double> prices = new ArrayList<>();
                                                    for (Drinks.Size size : Drinks.Size.values()) {
                                                        do {
                                                            try {
                                                                System.out.print(FontManager.responseCombo + "What Is The Price Of " + nameChoice + " (" + size.toString().toUpperCase() + "): " + FontManager.RESET);
                                                                double price = input.nextDouble();
                                                                input.nextLine();
                                                                prices.add(price);
                                                                break;
                                                            } catch (NumberFormatException e) {
                                                                System.out.println(FontManager.warningCombo + "WARNING! Invalid Input. Please Enter A Valid Amount." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Invalid Input. Please Enter A Valid Amount.".length()));
                                                            }
                                                        } while (true);
                                                    }
                                                    productManager.addProduct(new Drinks(nameChoice, prices.get(0), prices.get(1), prices.get(2)));
                                                    FileManager.saveProducts(productManager.getProducts());
                                                    break;
                                                }
                                            }
                                        } while (true);
                                        break;
                                    } else if (snacksChoice.equals("EDIT")) {
                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                        System.out.println(FontManager.primaryCombo + Global.putSpaces(50) + "EDIT PRODUCT" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("EDIT PRODUCT").length() + 50)));
                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                        do {
                                            System.out.print(FontManager.responseCombo + "Enter The Name Of The Product To Be Deleted Or Type In Back To Go Back To Selection: " + FontManager.RESET);
                                            String nameChoice = input.nextLine();
                                            if (nameChoice.equalsIgnoreCase("Back")) {
                                                break;
                                            }
                                            ArrayList<Product> products = productManager.findProductsByName(nameChoice);
                                            if (products.isEmpty()) {
                                                System.out.println(FontManager.warningCombo + "WARNING! No Products Found With The Name: " + nameChoice + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! No Products Found With The Name: " + nameChoice).length()));
                                                continue;
                                            }
                                            for (Product product : products) {
                                                System.out.print(FontManager.responseCombo + "Enter New Name For " + product.getName() + " Or Leave Blank For Default: " + FontManager.RESET);
                                                String newName = input.nextLine();
                                                if (product instanceof Drinks drinks) {
                                                    do {
                                                        try {
                                                            for (Drinks.Size size : Drinks.Size.values()) {
                                                                System.out.print(FontManager.responseCombo + "Enter New Price For " + drinks.getName() + " (" + size.toString().toUpperCase() + ") Or Leave Blank For Default:\t" + FontManager.RESET);
                                                                String newPriceString = input.nextLine().trim();

                                                                if (newPriceString.isEmpty()) {
                                                                    continue;
                                                                }

                                                                double newPrice = Double.parseDouble(newPriceString);
                                                                if (newPrice <= 0) {
                                                                    System.out.print(FontManager.warningCombo + "WARNING! Please Don't Enter A Negative Amount. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! Please Don't Enter A Negative Amount. Please Try Again.").length()));
                                                                    break;
                                                                }
                                                                productManager.updateProduct(drinks, newName, drinks.getPrices().get(size), newPrice);
                                                            }
                                                            break;
                                                        } catch (NumberFormatException e) {
                                                            System.out.println(FontManager.warningCombo + "WARNING! Invalid Input. Please Enter A Valid Amount Or Text." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Invalid Input. Please Enter A Valid Amount Or Text.".length()));
                                                        }
                                                    } while (true);
                                                } else {
                                                    do {
                                                        try {
                                                            System.out.print(FontManager.responseCombo + "Enter New Price For " + product.getName() + " Or Leave Blank For Default:\t" + FontManager.RESET);
                                                            String newPriceString = input.nextLine().trim();

                                                            if (newPriceString.isEmpty()) {
                                                                productManager.updateProduct(product, newName, product.getPrice(), product.getPrice());
                                                                break;
                                                            }

                                                            double newPrice = Double.parseDouble(newPriceString);
                                                            if (newPrice <= 0) {
                                                                System.out.print(FontManager.warningCombo + "WARNING! Please Don't Enter A Negative Amount. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! Please Don't Enter A Negative Amount. Please Try Again.").length()));
                                                                continue;
                                                            }
                                                            productManager.updateProduct(product, newName, product.getPrice(), newPrice);
                                                            break;
                                                        } catch (NumberFormatException e) {
                                                            System.out.println(FontManager.warningCombo + "WARNING! Invalid Input. Please Enter A Valid Amount Or Text." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Invalid Input. Please Enter A Valid Amount Or Text.".length()));
                                                        }
                                                    } while (true);
                                                }
                                            }
                                            break;
                                        } while (true);
                                        break;
                                    } else if (snacksChoice.equals("DELETE")) {
                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                        System.out.println(FontManager.primaryCombo + Global.putSpaces(49) + "DELETE PRODUCT" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("DELETE PRODUCT").length() + 49)));
                                        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
                                        do {
                                            System.out.print(FontManager.responseCombo + "Enter The Name Of The Product To Be Deleted Or Type In Back To Go Back To Selections: " + FontManager.RESET);
                                            String nameChoice = input.nextLine();
                                            if (nameChoice.equalsIgnoreCase("Back")) {
                                                break;
                                            }
                                            ArrayList<Product> products = productManager.findProductsByName(nameChoice);
                                            if (products.isEmpty()) {
                                                System.out.println(FontManager.warningCombo + "WARNING! No Products Found With The Name: " + nameChoice + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! No Products Found With The Name: " + nameChoice).length()));
                                                continue;
                                            }
                                            for (Product product : products) {
                                                productManager.removeProduct(product);
                                            }
                                            break;
                                        } while (true);
                                        break;
                                    } else if (snacksChoice.equals("BACK")) {
                                        adminEditSRunning = false;
                                        break;
                                    }
                                } while (true);
                            } while (adminEditSRunning);
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
                            adminPageRunning = false;
                            break;
                        }
                    } while (adminPageRunning);
                } else {
                    System.out.println(FontManager.errorCombo + Global.putSpaces(37) + "Incorrect Username Or Password" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("Incorrect Username Or Password".length() + 37)));
                    Thread.sleep(300);
                }
            } else if (choice.equals("EXIT")) {
                break;
            }
        } while (true);
    }

    //FUNCTIONS
    private static int getQuantity(Product product) {
        Scanner input = new Scanner(System.in);
        int quantity;
        do {
            System.out.print(FontManager.responseCombo + "How Many " + product.getName() + " Are You Purchasing: " + FontManager.RESET);
            try {
                quantity = input.nextInt();
                input.nextLine();
                if (!(quantity > 0)) {
                    System.out.println(FontManager.warningCombo + Global.putSpaces(25) + "WARNING! Please Input A Positive Integer. PLease Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! Please Input A Positive Integer. PLease Try Again...".length() + 25)));
                } else {
                    return quantity;
                }
            } catch (InputMismatchException e) {
                System.out.println(FontManager.warningCombo + Global.putSpaces(25) + "WARNING! Please Input A Valid Integer. PLease Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! Please Input A Valid Integer. PLease Try Again...".length() + 25)));
                input.nextLine();
            }
        } while (true);
    }
    private static boolean handlePayment(boolean printReceipt, double total) {
        do {
            Scanner input = new Scanner(System.in);
            System.out.print(FontManager.responseCombo + "Enter The Amount To Pay Or Type 'Cancel' To Cancel The Purchase: " + FontManager.RESET);
            String payment = input.nextLine();
            if (payment.equalsIgnoreCase("Cancel")) {
                Global.putHorizontalLine(FontManager.primaryCombo, horizontalLineLength);
                System.out.println(FontManager.warningCombo + Global.putSpaces(45) + "Purchase Cancelled." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("Purchase Cancelled.").length() + 45)));
                return false;
            }
            try {
                double paymentAmount = Double.parseDouble(payment);
                if (paymentAmount >= total) {
                    double change = paymentAmount - total;
                    System.out.println(FontManager.primaryCombo + String.format("Payment Accepted. Your Change Is %.2f PHP", change) + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - String.format("Payment Accepted. Your Change Is %.2f PHP", change).length()));
                    if (printReceipt) {
                        PrintManager.print(productManager.getShoppingCart(), productManager.getQuantities(), productManager.getSizes(), total, paymentAmount, change);
                    }
                    return true;
                } else {
                    System.out.println(FontManager.warningCombo + "WARNING! Insufficient Amount. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Insufficient Amount. Please Try Again.".length()));
                }
            } catch (NumberFormatException e) {
                System.out.println(FontManager.warningCombo + "WARNING! Invalid Input. Please Enter A Valid Amount Or Text." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Invalid Input. Please Enter A Valid Amount Or Text.".length()));
            }
        } while (true);
    }
    private static void handleSeatReservation(CinemaManager cinemaManager, Cinema cinema, String seatNumber, String reservation) throws InterruptedException, IOException {
        Seat seat = Cinema.findSeat(cinema.getSeats(), seatNumber);
        if (seat != null) {
            if (reservation.equals("BOOK")) {
                if (seat.isAvailable()) {
                    if (handlePayment(false,250.00)) {
                        cinema.bookSeat(cinemaManager, seatNumber);
                        Thread.sleep(300);
                        PrintManager.print(cinema.getMovie().getName(), cinema.getId(), seat.getSeatID());
                    }
                } else {
                    System.out.println(FontManager.errorCombo + Global.putSpaces(20) + "I'm Sorry But The Seat " + seat.getSeatID() + " Is Currently Booked. Please Pick Another Seat. " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (("I'm Sorry But The Seat " + seat.getSeatID() + " Is Currently Booked. Please Pick Another Seat. " ).length() + 20)));
                    Thread.sleep(300);
                }
            } else if (reservation.equals("CANCEL")) {
                if (!seat.isAvailable()) {
                    do {
                        if (handleWarning("This Will Cancel The Booking of Seat " + seat.getSeatID() + ".")) {
                            cinema.cancelSeat(cinemaManager, seat.getSeatID());
                            break;
                        }
                    } while (true);
                } else {
                    System.out.println(FontManager.warningCombo + "WARNING! Seat Is Still Available, Cannot Cancel Booking. Please Try Again." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "WARNING! Seat Is Still Available, Cannot Cancel Booking. Please Try Again.".length()));
                }
            }
        } else {
            System.out.println(FontManager.warningCombo + Global.putSpaces(25) + "WARNING! Please Input The Right Seat Number. PLease Try Again..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("WARNING! Please Input The Right Seat Number. PLease Try Again...".length() + 25)));
            Thread.sleep(300);
        }
    }
    private static boolean handleWarning(String warning) {
        Scanner input = new Scanner(System.in);
        System.out.println(FontManager.warningCombo + "    WARNING! " + warning + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - (warning).length()));
        System.out.println(FontManager.primaryCombo + "    Would You Like To Continue (Yes/No): " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("    Would You Like To Continue (Yes/No): ").length()));
        do {
            Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
            System.out.print(FontManager.responseCombo + "Your Response - " + FontManager.RESET);
            String warningChoice = input.nextLine().toUpperCase();
            if (warningChoice.isBlank()) {
                System.out.println(FontManager.warningCombo + "    WARNING! Please Don't Input Blank Texts. Please Try Again. " + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - "    WARNING! Don't Input Blank Texts ".length()));
            } else if (warningChoice.equals("YES")) {
                return true;
            } else if (warningChoice.equals("NO")) {
                return false;
            }
        } while (true);
    }
    private static void displayCinemas(CinemaManager cinemaManager) {
        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
        System.out.println(FontManager.primaryCombo + Global.putSpaces(50) + "CINEMAS" + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength - ("CINEMAS".length() + 50)));
        Global.putHorizontalLine(FontManager.tertiaryCombo, horizontalLineLength);
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
        System.out.println(FontManager.secondaryCombo + "        " + Global.spacerString(51, ("Cinema " + FontManager.primaryCombo + "1: " + (!cinemaManager.getCinema("1").getMovie().getName().isEmpty() ? cinemaManager.getCinema("1").getMovie().getName() : "None"))) + FontManager.RESET + FontManager.secondaryCombo + "               " + Global.spacerString(50, ("Cinema " + FontManager.primaryCombo + "2: " + (!cinemaManager.getCinema("2").getMovie().getName().isEmpty() ? cinemaManager.getCinema("2").getMovie().getName() : "None"))) + "        " + FontManager.RESET);
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
        System.out.println(FontManager.secondaryCombo + "        " + Global.spacerString(51, ("Cinema " + FontManager.primaryCombo + "3: " + (!cinemaManager.getCinema("3").getMovie().getName().isEmpty() ? cinemaManager.getCinema("3").getMovie().getName() : "None"))) + FontManager.RESET + FontManager.secondaryCombo + "               " + Global.spacerString(50, ("Cinema " + FontManager.primaryCombo + "4: " + (!cinemaManager.getCinema("4").getMovie().getName().isEmpty() ? cinemaManager.getCinema("4").getMovie().getName() : "None"))) + "        " + FontManager.RESET);
        System.out.println(Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, horizontalLineLength));
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
    private static void initializeProducts(ProductManager productManager) throws IOException {
        productManager.addProduct(new Snack("Popcorn", 45.00));
        productManager.addProduct(new Snack("Fries", 35.00));
        productManager.addProduct(new Drinks("Water", 15.00, 20.00, 25.00));
        productManager.addProduct(new Drinks("Coke", 25.00, 35.00, 40.00));
        FileManager.saveProducts(productManager.getProducts());
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
