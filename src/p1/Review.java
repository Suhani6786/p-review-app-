package p1;

public class Review {
    private String restaurantName;
    private int rating;
    private String comment;

    public Review(String restaurantName, int rating, String comment) {
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.comment = comment;
    }

    public String getReviewDetails() {
        return "Restaurant: " + restaurantName + " | Rating: " + rating + " | Comment: " + comment;
    }
}
