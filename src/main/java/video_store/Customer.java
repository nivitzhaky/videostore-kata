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
        double thisAmount = 0;

        // determines the amount for each line
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2) {
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3) {
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                }
                break;
        }

        frequentRenterPoints++;

        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
            && rental.getDaysRented() > 1)
            frequentRenterPoints++;

        result += "\t" + rental.getMovie().getTitle() + "\t"
            + String.valueOf(thisAmount) + "\n";
        totalAmount += thisAmount;
        return result;
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
