package rentalstore;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReceiptTest {

    @Test
    public void should_output_receipt_with_total_price_2_when_rent_REGULAR_movie_for_one_day() {
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Titanic", MovieType.REGULAR), 1)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedReceipt =
                "<H1>Rentals for <EM>Dylan</EM></H1><P>\n" +
                        "Titanic: 2.0<BR>\n" +
                        "<P>You owe<EM>2.0</EM><P>\n" +
                        "On this rental you earned <EM>1.0</EM> frequent renter points<P>";
        assertThat(receipt.getReceiptHtml(), is(expectedReceipt));
    }

    @Test
    public void should_output_receipt_with_total_price_6_point_5_when_rent_NEW_RELEASE_movie_1_day_and_REGULAR_for_3_days() {
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Titanic", MovieType.REGULAR), 3),
                new Rental(new Movie("The Ring", MovieType.NEW_RELEASE), 1)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedReceipt =
                "<H1>Rentals for <EM>Dylan</EM></H1><P>\n" +
                        "Titanic: 3.5<BR>\n" +
                        "The Ring: 3.0<BR>\n" +
                        "<P>You owe<EM>6.5</EM><P>\n" +
                        "On this rental you earned <EM>2.0</EM> frequent renter points<P>";
        assertThat(receipt.getReceiptHtml(), is(expectedReceipt));
    }

    @Test
    public void should_return_receipt_with_total_price_8_when_rent_REGULAR_3_days_and_NEW_RELEASE_1_day_and_CHILDRENS_3_days() {
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Titanic", MovieType.REGULAR), 3),
                new Rental(new Movie("The Ring", MovieType.NEW_RELEASE), 1),
                new Rental(new Movie("One Day", MovieType.CHILDREN), 3)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedReceipt =
                "<H1>Rentals for <EM>Dylan</EM></H1><P>\n" +
                        "Titanic: 3.5<BR>\n" +
                        "The Ring: 3.0<BR>\n" +
                        "One Day: 1.5<BR>\n" +
                        "<P>You owe<EM>8.0</EM><P>\n" +
                        "On this rental you earned <EM>3.0</EM> frequent renter points<P>";
        assertThat(receipt.getReceiptHtml(), is(expectedReceipt));
    }

    @Test
    public void should_return_correct_items_details() {
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Titanic", MovieType.REGULAR), 3),
                new Rental(new Movie("The Ring", MovieType.NEW_RELEASE), 1),
                new Rental(new Movie("One Day", MovieType.CHILDREN), 3)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedItemsStr =
                "Titanic: 3.5<BR>\n" +
                        "The Ring: 3.0<BR>\n" +
                        "One Day: 1.5<BR>\n";
        assertThat(receipt.getItemsHtml(), is(expectedItemsStr));
    }

    @Test
    public void should_return_1_point_5_rental_points_when_rent_ART_movie_for_1_days(){
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("ART MOVIE", MovieType.ART), 1)
        );
        Receipt receipt = new Receipt(customer, rentals);
        assertThat(receipt.getRentalPointsEarned(), is(1.5));
    }

    @Test
    public void should_return_receipt_with_total_price_12_and_rental_point_earned_1_point_5_when_rent_ART_movie_for_2_days(){
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("ART MOVIE", MovieType.ART), 2)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedReceipt =
                "<H1>Rentals for <EM>Dylan</EM></H1><P>\n" +
                        "ART MOVIE: 12.0<BR>\n" +
                        "<P>You owe<EM>12.0</EM><P>\n" +
                        "On this rental you earned <EM>1.5</EM> frequent renter points<P>";
        assertThat(receipt.getReceiptHtml(), is(expectedReceipt));
    }


}