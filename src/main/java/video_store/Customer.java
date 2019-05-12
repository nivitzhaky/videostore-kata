package video_store;

import java.util.ArrayList;

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
        double rentalAmount = rental.determineAmount();
        totalAmount += rentalAmount;
        frequentRenterPoints += rental.determineFrequentPoints();
        return formatRentalLine(rental, rentalAmount);
    }

    private String formatRentalLine(Rental rental, double rentalAmount) {
        return "\t" + rental.getMovie().getTitle() + "\t"
            + String.valueOf(rentalAmount) + "\n";
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
