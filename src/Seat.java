public class Seat {
    private String seatID;
    private boolean isAvailable;

    public Seat(String seatID) {
        setSeatID(seatID);
        setAvailable(true);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }


    public void book() {
        isAvailable = false;
    }
    public void cancel() {
        isAvailable = true;
    }
}
