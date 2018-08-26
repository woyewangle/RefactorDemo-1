package rentalstore;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {
    private Customer customer;
    private List<Rental> rentals;

    public Receipt(){

    }

    public Receipt(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
    }

    private double getItemSubTotalPrice(Rental rental){
        double subTotalPrice = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                subTotalPrice += 2;
                if (rental.getDayRented() > 2) {
                    subTotalPrice += (rental.getDayRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                subTotalPrice += rental.getDayRented() * 3;
                break;
            case Movie.CHILDREN:
                subTotalPrice += 1.5;
                if (rental.getDayRented() > 3) {
                    subTotalPrice += (rental.getDayRented() - 3) * 1.5;
                }
                break;
        }
        return subTotalPrice;
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for (Rental rental : rentals) {
            totalPrice += getItemSubTotalPrice(rental);
        }
        return totalPrice;
    }

    public String getHeaderHtml(){
        return "<H1>Rentals for <EM>" + this.customer.getName() + "</EM></H1><P>\n";
    }

    public String getItemHtml(Rental rental){
        return rental.getMovie().getTitle() + ": " + this.getItemSubTotalPrice(rental) + "<BR>\n";
    }

    public String getItemsHtml(){
        StringBuilder itemsStrBuilder = new StringBuilder();
        this.rentals.stream().forEach(rental -> itemsStrBuilder.append(getItemHtml(rental)));
        return itemsStrBuilder.toString();
    }

    public int getRentalPointsEarned(){
        int rentalPoints = 0;
        for (Rental rental : rentals) {
            rentalPoints++;
            if(rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDayRented() > 1)
                rentalPoints++;
        }
        return rentalPoints;
    }

    public String getReceiptStr(){
        String result = "Rental Record for " + this.customer.getName() + "\n";
        List<Rental> rentals = this.rentals.stream().distinct().collect(Collectors.toList());
        Iterator<Rental> rentalSetIterator = rentals.iterator();
        while (rentalSetIterator.hasNext()) {
            Rental each = rentalSetIterator.next();
            double subTotalPrice = getItemSubTotalPrice(each);
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(subTotalPrice) + "\n";
        }
        //add footer lines
        result += "Amount owed is " + getTotalPrice() + "\n";
        result += "You earned " + getRentalPointsEarned() + " frequent renter points";
        return result;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
