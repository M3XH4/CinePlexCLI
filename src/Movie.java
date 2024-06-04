public class Movie {
    private String name;
    private String details;

    public Movie(String name, String details) {
        this.details = details;
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
