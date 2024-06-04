import java.io.*;
import java.util.*;

public class FileManager {
    private static final String cinemaFile = "src\\cinema.txt";

    public static void saveBookings(ArrayList<Seat> seats) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(cinemaFile));
        for (Seat seat : seats) {
            writer.write(seat.getSeatID() + ":" + seat.isAvailable());
            writer.newLine();
        }
        writer.close();
    }

    public static void loadBookings(ArrayList<Seat> seats) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cinemaFile));
        String seatTXT;
        while ((seatTXT = reader.readLine()) != null) {
            String[] seatDetails = seatTXT.split(":");
            String seatID = seatDetails[0];
            boolean isAvailable = Boolean.parseBoolean(seatDetails[1]);
            for (Seat seat : seats) {
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
