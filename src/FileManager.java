import java.io.*;
import java.util.*;

public class FileManager {
    private static final String cinemaFile = "src\\bin\\cinema.txt";

    public static void saveBookings(ArrayList<Cinema> cinemas) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(cinemaFile));

        for (Cinema cinema : cinemas) {
            writer.write("Cinema: " + cinema.getId());
            writer.newLine();
            for (Seat seat : cinema.getSeats()) {
                writer.write(seat.getSeatID() + ":" + seat.isAvailable());
                writer.newLine();
            }
        }

        writer.close();
    }

    public static void loadBookings(ArrayList<Cinema> cinemas) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cinemaFile));
        String line;
        Cinema currentCinema = null;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Cinema:")) {
                String cinemaID = line.substring(7);
                currentCinema = findCinema(cinemas, cinemaID);
            } else if (currentCinema != null) {
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
