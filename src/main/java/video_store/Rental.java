package video_store;

public class Rental {
    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    private Movie movie;
    private int daysRented;

    double determineAmount() {
       return  movie.determineAmount(daysRented);
    }

    int determineFrequentPoints() {
        return movie.determineFrequentPoints(daysRented);
    }
}
