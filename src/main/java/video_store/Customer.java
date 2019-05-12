package video_store;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Enumeration;

public class Customer {

    public Customer(String name) {
        this.name = name;
    }

    private String name;

    private double totalAmount = 0;
    private int frequentRenterPoints = 0;
    private ArrayList<Rental> rentals = new ArrayList<>();
    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        return header() + rentalLines() + footer();

    }

    private String rentalLines() {
        String lines = "";
        for  ( Rental rental  : rentals ) {
            lines += rentalLine(rental);

        }
        return lines;
    }

    private String rentalLine(Rental rental) {
        String result = "";
        double rentalAmount = determineAmount(rental);
        totalAmount += rentalAmount;
        frequentRenterPoints += determineFrequentPoints(rental);
        return formatRentalLine(rental, rentalAmount);
    }

    private String formatRentalLine(Rental rental, double rentalAmount) {
        return "\t" + rental.getMovie().getTitle() + "\t"
            + String.valueOf(rentalAmount) + "\n";
    }

    private int determineFrequentPoints(Rental rental) {
        boolean hasEarnedBonusPoint = rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
                && rental.getDaysRented() > 1;
        if (hasEarnedBonusPoint)
            return 2;
        else
            return 1;
    }

    private double determineAmount(Rental rental) {
        double rentalAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                rentalAmount += 2;
                if (rental.getDaysRented() > 2) {
                    rentalAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                rentalAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                rentalAmount += 1.5;
                if (rental.getDaysRented() > 3) {
                    rentalAmount += (rental.getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return rentalAmount;
    }

    private String header() {
        return "Rental Record for " + getName() + "\n";
    }

    private String footer() {
        String result = "";
        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";
        return result;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public String getName() {
        return name;
    }

}
