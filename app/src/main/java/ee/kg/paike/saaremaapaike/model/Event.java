package ee.kg.paike.saaremaapaike.model;

public class Event {

    public String link;
    public String day;
    public String date;
    public String heading;
    public String location;
    public String category;

    public Event(String link, String day, String date, String heading, String location, String category) {

        this.link = link;
        this.day = day;
        this.date = date;
        this.heading = heading;
        this.location = location;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Link: " + link + " Päev: " + day + "Kuupäev: " + date + "Pealkiri: " + heading + "Asukoht: " + location + "Kategooria: " + category;
    }
}
