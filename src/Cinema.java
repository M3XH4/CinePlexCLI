import java.io.IOException;
import java.util.ArrayList;

public class Cinema {
    private String id;
    private Movie movie;
    private ArrayList<Seat> seats;

    public Cinema(String id) {
        setId(id);
        setSeats(new ArrayList<>());
        putSeats();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }


    public void putSeats() {
        String[] rows = {"G", "F", "E", "D", "C", "B", "A"};
        for(String row : rows) {
            for (int i = 1; i <= 6; i++) {
                seats.add(new Seat(row + i));
            }
        }
    }

    public int totalBookedSeats() {
        ArrayList<Seat> tempSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isAvailable()) {
                tempSeats.add(seat);
            }
        }
        return tempSeats.size();
    }

    public int totalAvailableSeats() {
        ArrayList<Seat> tempSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                tempSeats.add(seat);
            }
        }
        return tempSeats.size();
    }

    public void bookSeat(CinemaManager cinemaManager, String seatID) throws InterruptedException, IOException {
        for (Seat seat : seats) {
            if (seat.getSeatID().equals(seatID) && seat.isAvailable()) {
                seat.book();
                FileManager.saveCinemas(cinemaManager.getCinemas());
                Thread.sleep(300);
                System.out.println(FontManager.primaryCombo + Global.putSpaces(42) + "Successfully Booked Seat " + seat.getSeatID() + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK, MainClass.horizontalLineLength - (("Successfully Booked Seat " + seat.getSeatID()).length() + 42)));
            }
        }
    }

    public void cancelSeat(CinemaManager cinemaManager, String seatID) throws InterruptedException, IOException {
        for (Seat seat : seats) {
            if (seat.getSeatID().equals(seatID) && !(seat.isAvailable())) {
                seat.cancel();
                FileManager.saveCinemas(cinemaManager.getCinemas());
                Thread.sleep(300);
                System.out.println(FontManager.primaryCombo + "Successfully Cancelled The Booking of Seat " + seat.getSeatID() + "." + Global.putBackgroundColor(FontManager.BACKGROUND_BLACK,  MainClass.horizontalLineLength - ("Successfully Cancelled The Booking of Seat " + seat.getSeatID() + "." ).length()));
            }
        }
    }

    public static void displaySeats(ArrayList<Seat> seats) {
        String[] rows = {"G", "F", "E", "D", "C", "B", "A"};
        for (String row : rows) {
            System.out.print(FontManager.primaryCombo + "|" + FontManager.RESET);
            for (int i = 1; i <= 3; i++) {
                Seat seat = findSeat(seats, row + i);
                if (seat != null) {
                    System.out.print(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "[ " + FontManager.BOLD + seat.getSeatID() + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ": " + (seat.isAvailable() ? FontManager.TEXT_GREEN_BRIGHT + "Available" : FontManager.TEXT_RED_BRIGHT + "Booked   ") + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + " ] " + FontManager.RESET);
                }
            }
            System.out.print(FontManager.BACKGROUND_BLACK + "   " + FontManager.RESET);
            for (int i = 4; i <= 6; i++) {
                Seat seat = findSeat(seats, row + i);
                if (seat != null) {
                    if (i <= 5) {
                        System.out.print(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "[ " + FontManager.BOLD + seat.getSeatID() + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ": " + (seat.isAvailable() ? FontManager.TEXT_GREEN_BRIGHT + "Available" : FontManager.TEXT_RED_BRIGHT + "Booked   ") + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + " ] " + FontManager.RESET);
                    } else {
                        System.out.print(FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + "[ " + FontManager.BOLD + seat.getSeatID() + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + ": " + (seat.isAvailable() ? FontManager.TEXT_GREEN_BRIGHT + "Available" : FontManager.TEXT_RED_BRIGHT + "Booked   ") + FontManager.RESET + FontManager.BACKGROUND_BLACK + FontManager.TEXT_WHITE_BRIGHT + " ]" + FontManager.RESET);
                    }
                }
            }
            System.out.print(FontManager.primaryCombo + "|" + FontManager.RESET);
            System.out.println();
        }
    }

    public static Seat findSeat(ArrayList<Seat> seats, String seatID) {
        for (Seat seat : seats) {
            if (seat.getSeatID().equals(seatID)) {
                return seat;
            }
        }
        return null;
    }
}
