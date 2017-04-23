package ee.kg.paike.saaremaapaike.model;

public class Event {

    public String day;
    public String date;
    public String heading;
    public String location;
    public String category;

    public Event(String day, String date, String heading, String location, String category) {

        this.day = day;
        this.date = date;
        this.heading = heading;
        this.location = location;
        this.category = category;
    }
}
