package com.king.urban.refactor;

/**
 * 租赁
 *
 * @author liukai
 */
public class Rental {

    private Movie _movie;

    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        this._movie = movie;
        this._daysRented = daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    /**
     * charge费用
     */
    protected double getCharge() {
        return _movie.getCharge(_daysRented);
    }


    protected int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
    }

}
