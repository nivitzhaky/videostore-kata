package video_store.unit_tests;

import org.junit.Before;
import org.junit.Test;
import video_store.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VideoStoreTest {
    private final Movie newReleaseMovie = new NewReleaseMovie("New Release");
    private final Movie newReleaseMovie2 = new NewReleaseMovie("New Release2");
    private final Movie childrensMovie = new ChildrensMovie("Childrens");
    private final Movie regularMovie = new RegularMovie("Regular");
    private final Movie regularMovie2 = new RegularMovie("Regular2");
    private final Movie regularMovie3 = new RegularMovie("Regular3");

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("customer");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(newReleaseMovie, 3));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(9.0));
        assertThat(customer.getFrequentRenterPoints(),is(2));
    }

    @Test
    public void testSingleNewReleaseOneDayStatement() {
        customer.addRental(new Rental(newReleaseMovie, 1));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(3.0));
        assertThat(customer.getFrequentRenterPoints(),is(1));
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(newReleaseMovie, 3));
        customer.addRental(new Rental(newReleaseMovie2, 3));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(18.0));
        assertThat(customer.getFrequentRenterPoints(),is(4));
    }

    @Test
    public void testSingleChildrensStatement() {
        customer.addRental(new Rental(childrensMovie, 3));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(1.5));
        assertThat(customer.getFrequentRenterPoints(),is(1));
    }

    @Test
    public void testSingleChildrensStatementRentedMoreThanThreeDaysAgo() {
        customer.addRental(new Rental(childrensMovie, 4));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(3.0));
        assertThat(customer.getFrequentRenterPoints(),is(1));
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(regularMovie2, 2));
        customer.addRental(new Rental(regularMovie3, 3));
        customer.statement();
        assertThat(customer.getTotalAmount(),is(7.5));
        assertThat(customer.getFrequentRenterPoints(),is(3));
    }
    @Test
    public void testFormat() {
        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(regularMovie2, 2));
        customer.addRental(new Rental(regularMovie3, 3));

        assertThat(
                customer.statement(),
                is("Rental Record for customer\n" +
                        "\tRegular\t2.0\n" +
                        "\tRegular2\t2.0\n" +
                        "\tRegular3\t3.5\n" +
                        "You owed 7.5\nYou earned 3 frequent renter points\n"));
    }

}
