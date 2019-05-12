package video_store;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int determineFrequentPoints(int daysRented) {
        boolean hasEarnedBonusPoint =  daysRented > 1;
        if (hasEarnedBonusPoint)
            return 2;
        else
            return 1;
    }
}
