package p1;

import java.util.ArrayList;

public class Reviewer extends User {
    private ArrayList<Review> reviews;

    public Reviewer(String username, String password) {
        super(username, password);
        this.reviews = new ArrayList<>();
    }

    public void writeReview(String restaurantName, int rating, String comment) {
        reviews.add(new Review(restaurantName, rating, comment));
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
