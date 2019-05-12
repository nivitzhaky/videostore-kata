package video_store;

public abstract class Movie {

    private String title;

    Movie(String title) {
        this.title = title;
    }


    String getTitle() {
        return title;
    }

    abstract double determineAmount(int daysRented);

    abstract int determineFrequentPoints(int daysRented);
}
