package com.example.demo.refacing;

import java.util.Enumeration;
import java.util.Vector;

public class Demo1 {

}

abstract class Price {
    abstract int getPriceCode();
    abstract public double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    };
}

class ChildrensPrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }


}

class NewReleasePrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}

class RegularPrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
}

class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private Price price;

    public Movie(String title, Price price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(Price price) {
       this.price = price;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}

class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie _movie, int _daysRented) {
        this._movie = _movie;
        this._daysRented = _daysRented;
    }

    public Movie get_movie() {
        return _movie;
    }

    public void set_movie(Movie _movie) {
        this._movie = _movie;
    }

    public int get_daysRented() {
        return _daysRented;
    }

    public void set_daysRented(int _daysRented) {
        this._daysRented = _daysRented;
    }

    public double getCharge() {

        return _movie.getCharge(_daysRented);
    }

    public int getFrequentRenterPoints() {

        return _movie.getFrequentRenterPoints(_daysRented);
    }
}

class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String statement() {
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += "\t" + each.get_movie().getTitle() + "\t" +
                    String.valueOf(each.getCharge());
        }
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getFrequentRenterPoints()) +
                " frequent renter points";
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private int getFrequentRenterPoints() {
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }


}