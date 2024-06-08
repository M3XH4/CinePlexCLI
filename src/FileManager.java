import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {
    private static final String cinemaFile = getSourcePath() + "\\bin\\cinema.txt";
    private static final String printerFile = getSourcePath() + "\\bin\\printer.txt";
    private static final String productsFile = getSourcePath() + "\\bin\\products.dat";

    private static String getSourcePath() {
        Path currentPath = Paths.get("");
        return currentPath.toAbsolutePath() + "\\src";
    }

    public static void createFile() throws IOException {
        File cinemafile = new File(cinemaFile);
        File printerfile = new File(printerFile);
        File productsfile = new File(productsFile);

        if (!cinemafile.exists()) {
            cinemafile.createNewFile();
        }
        if (!printerfile.exists()) {
            printerfile.createNewFile();
            savePrinter(0);
        }
        if (!productsfile.exists()) {
            productsfile.createNewFile();
        }
    }
    public static void savePrinter(int printer) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(printerFile));

        writer.write("Printer: " + printer);

        writer.close();
    }
    public static int loadPrinter() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(printerFile));
        String line;
        int tempPrinter = 0;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Printer: ")) {
                String printerCode = line.substring(9);
                tempPrinter = Integer.parseInt(printerCode);
            }
        }

        return tempPrinter;
    }
    public static void saveBookings(ArrayList<Cinema> cinemas) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(cinemaFile));

        for (Cinema cinema : cinemas) {
            writer.write("Cinema: " + cinema.getId());
            writer.newLine();
            if (cinema.getMovie() != null) {
                writer.write("Movie: " + cinema.getMovie().getName());
            } else {
                writer.write("Movie: None");
            }
            writer.newLine();
            if (!cinema.getMovie().getDetails().isEmpty()) {
                writer.write("Details: " + cinema.getMovie().getDetails());
            } else {
                writer.write("Details: None");
            }
            writer.newLine();
            for (Seat seat : cinema.getSeats()) {
                writer.write(seat.getSeatID() + ":" + seat.isAvailable());
                writer.newLine();
            }
        }

        writer.close();
    }
    public static ArrayList<Cinema> loadBookings() throws IOException {
        ArrayList<Cinema> cinemas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(cinemaFile));
        String line;
        Cinema currentCinema = null;
        String name = "";
        String details;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Cinema: ")) {
                String cinemaID = line.trim().substring(8);
                currentCinema = new Cinema(cinemaID);
                cinemas.add(currentCinema);
            } else if (currentCinema != null) {
                if (line.startsWith("Movie: ")) {
                    String movieName = line.substring(7);
                    if (!movieName.equals("None")) {
                        name = movieName;
                    }
                } else if (line.startsWith("Details: ")) {
                    String movieDetails = line.substring(9);
                    details = movieDetails;
                    if (!movieDetails.equals("None")) {
                        currentCinema.setMovie(new Movie(name, details));
                    } else {
                        currentCinema.setMovie(new Movie(name, ""));
                    }
                }
                String[] seatDetails = line.split(":");
                String seatID = seatDetails[0];
                boolean isAvailable = Boolean.parseBoolean(seatDetails[1]);
                for (Seat seat : currentCinema.getSeats()) {
                    if (seat.getSeatID().equals(seatID)) {
                        if (!isAvailable) {
                            seat.book();
                        } else {
                            seat.cancel();
                        }
                    }
                }
            }
        }
        return cinemas;
    }
    public static void saveProducts(ArrayList<Product> products) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(productsFile));
        outputStream.writeObject(products);
    }
    public static ArrayList<Product> loadProducts(){
        ArrayList<Product> tempProducts = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(productsFile))) {
            tempProducts = (ArrayList<Product>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
        return tempProducts;
    }
}
