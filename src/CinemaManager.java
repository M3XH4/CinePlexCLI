import java.util.ArrayList;

public class CinemaManager {
    private ArrayList<Movie> movies;
    private ArrayList<Seat> seats;

    public CinemaManager() {
        movies = new ArrayList<>();
        seats = new ArrayList<>();
        putSeats();
    }
    private void putSeats() {
        String[] rows = {"C", "B", "A"};
        for(String row: rows) {
            for(int i = 1; i <= 10; i++) {
                seats.add(new Seat(row + i));
            }
        }
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void editMovie(int index, String name, String details) {
        if (index >= 0 && index < movies.size()) {
            Movie movie = movies.get(index);
            movie.setName(name);
            movie.setDetails(details);
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
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
