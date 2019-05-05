package video_store.unit_tests;

import org.junit.Before;
import org.junit.Test;
import video_store.Customer;
import video_store.Movie;
import video_store.Rental;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VideoStoreTest {
    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE), 3));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(9.0));
        assertThat(customer.getFrequentRenterPoints(),is(2));
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE), 3));
        customer.addRental(new Rental(new Movie("The Tigger Movie", Movie.NEW_RELEASE), 3));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(18.0));
        assertThat(customer.getFrequentRenterPoints(),is(4));
    }

    @Test
    public void testSingleChildrensStatement() {
        customer.addRental(new Rental(new Movie("The Tigger Movie", Movie.CHILDRENS), 3));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(1.5));
        assertThat(customer.getFrequentRenterPoints(),is(1));
    }

    @Test
    public void testSingleChildrensStatementRentedMoreThanThreeDaysAgo() {
        customer.addRental(new Rental(new Movie("The Tigger Movie", Movie.CHILDRENS), 4));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(3.0));
        assertThat(customer.getFrequentRenterPoints(),is(1));
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(new Movie("Plan 9 from Outer Space", Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie("8 1/2", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Eraserhead", Movie.REGULAR), 3));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(7.5));
        assertThat(customer.getFrequentRenterPoints(),is(3));
    }
    @Test
    public void testFormat() {
        customer.addRental(new Rental(new Movie("Plan 9 from Outer Space", Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie("8 1/2", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Eraserhead", Movie.REGULAR), 3));

        assertThat(
                customer.statement(),
                is("Rental Record for Fred\n" +
                        "\tPlan 9 from Outer Space\t2.0\n" +
                        "\t8 1/2\t2.0\n\tEraserhead\t3.5\n" +
                        "You owed 7.5\nYou earned 3 frequent renter points\n"));
    }

}
