package com.king.urban.refactor;

public class MovieTest {

    public static void main(String[] args) {
        Movie movie1 = new Movie("变形金刚", 2);
        Movie movie2 = new Movie("老人与海", 0);
        Movie movie3 = new Movie("大话西游", 1);


        Customer customer = new Customer("liukai");
        customer.addRental(new Rental(movie1, 2));
        customer.addRental(new Rental(movie2, 5));
        customer.addRental(new Rental(movie3, 4));

        String result = customer.statement();
        System.out.println(result);
    }
}
