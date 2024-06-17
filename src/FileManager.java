import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {
    private static final String cinemaFile = getSourcePath() + "\\cinema.txt";
    private static final String printerFile = getSourcePath() + "\\printer.txt";
    private static final String productsFile = getSourcePath() + "\\products.dat";

    private static String getSourcePath() {
        Path currentPath = Paths.get("");
        Path srcPath = currentPath.resolve("src");
        Path binPath = srcPath.resolve("bin");
        if (!Files.exists(binPath)) {
            try {
                Files.createDirectories(binPath);
            } catch (IOException e) {
                System.out.print(FontManager.errorCombo + "ERROR! Cannot Create Bin Folder..." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - "ERROR! Cannot Create Bin Folder...".length()));
            }
        }
        return binPath.toAbsolutePath().toString();
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
        int tempPrinterCode = 0;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Printer: ")) {
                String printerCode = line.substring(9);
                tempPrinterCode = Integer.parseInt(printerCode);
            }
        }
        return tempPrinterCode;
    }
    public static void saveCinemas(ArrayList<Cinema> cinemas) throws IOException {
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
    public static ArrayList<Cinema> loadCinemas() throws IOException {
        ArrayList<Cinema> tempCinemas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(cinemaFile));
        String line;

        Cinema currentCinema = null;
        String name = "";
        String details;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Cinema: ")) {
                String cinemaID = line.trim().substring(8);
                currentCinema = new Cinema(cinemaID);
                tempCinemas.add(currentCinema);
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
        return tempCinemas;
    }

    public static void saveProducts(ArrayList<Product> products) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(productsFile));
        outputStream.writeObject(products);
    }

    public static ArrayList<Product> loadProducts(){
        ArrayList<Product> tempProducts;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(productsFile))) {
            tempProducts = (ArrayList<Product>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
        return tempProducts;
    }
}
