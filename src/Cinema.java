import java.util.ArrayList;

public class Cinema {
    private String id;
    private Movie movie;
    private ArrayList<Seat> seats;

    public Cinema(String id) {
        this.id = id;
        seats = new ArrayList<>();
        putSeats();
    }
    private void putSeats() {
        String[] rows = {"D", "C", "B", "A"};
        for(String row: rows) {
            for(int i = 1; i <= 10; i++) {
                seats.add(new Seat(row + i));
            }
        }
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

    public boolean bookSeat(String seatID) {
        for (Seat seat : seats) {
            if (seat.getSeatID().equals(seatID) && seat.isAvailable()) {
                seat.book();
                return true;
            }
        }
        return false;
    }

    public void cancelSeat(String seatID) {
        for (Seat seat : seats) {
            if (seat.getSeatID().equals(seatID)) {
                seat.cancel();
            }
        }
    }
}
