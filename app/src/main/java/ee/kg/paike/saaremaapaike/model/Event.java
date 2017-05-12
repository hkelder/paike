package ee.kg.paike.saaremaapaike.model;

public class Event {

    public ListItemType type;
    public String link;
    public String day;
    public String date;
    public String heading;
    public String location;
    public String category;
    public String imageUrl;
    public String promotionUrl;
    public String promotionLink;


     Event(String link, String day, String date, String heading, String location, String category, String imageUrl, String promotionUrl, String promotionLink, ListItemType type) {
        this.link = link;
        this.day = day;
        this.date = date;
        this.heading = heading;
        this.location = location;
        this.category = category;
        this.imageUrl = imageUrl;
        this.type = type;
        this.promotionUrl = promotionUrl;
        this.promotionLink = promotionLink;
    }

    public static Event createEventItem(String link, String day, String date, String heading, String location, String category, String imageUrl) {
        return new Event(link, day, date, heading, location, category, imageUrl, null, null, ListItemType.EVENT);
    }

    public static Event createPromotionItem(String promotionUrl, String promotionLink) {
        return new Event (null, null, null, null, null, null, null, promotionUrl, promotionLink, ListItemType.PROMOTION);
    }

    @Override
    public String toString() {
        return "Link: " + link + " Päev: " + day + " Kuupäev: " + date + " Pealkiri: " + heading + " Asukoht: " + location + " Kategooria: " + category + " Pildi link:" + imageUrl + " Promotion link:" + promotionLink + " Promotion url:" + promotionUrl;
    }

    public enum ListItemType {EVENT, PROMOTION}
}

