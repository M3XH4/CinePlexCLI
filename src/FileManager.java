import java.io.*;
import java.util.*;

public class FileManager {
    private static final String cinemaFile = "src\\bin\\cinema.txt";

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
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Cinema: ")) {
                String cinemaID = line.trim().substring(8);
                currentCinema = new Cinema(cinemaID);
                cinemas.add(currentCinema);
            } else if (currentCinema != null) {
                if (line.startsWith("Movie: ")) {
                    String movieName = line.substring(7);
                    if (!movieName.equals("None")) {
                        currentCinema.setMovie(new Movie(movieName, ""));
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

    private static Cinema findCinema(ArrayList<Cinema> cinemas, String id) {
        for (Cinema cinema : cinemas) {
            if (cinema.getId().equals(id)) {
                return cinema;
            }
        }
        return null;
    }
}
