import java.util.*;

public class CinemaManager {
    private ArrayList<Cinema> cinemas;

    public CinemaManager() {
        setCinemas(new ArrayList<>());
    }
    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }
    public Cinema getCinema(String id) {
        for (Cinema cinema : cinemas) {
            if (cinema.getId().equals(id)) {
                return cinema;
            }
        }
        return null;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public Cinema findCinemaByMovieName(String movieName) {
        for (Cinema cinema : cinemas) {
            if (cinema.getMovie().getName().equals(movieName)) {
                return cinema;
            }
        }
        return null;
    }
}
