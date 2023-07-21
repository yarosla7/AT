package com.gridnine.testing;

import service.Filter;
import service.FilterImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Filter filter = new FilterImpl();

        List<FlightBuilder.Flight> list = FlightBuilder.createFlights();
        System.out.println("list = " + list);

        System.out.println("filter.departureToNow(list) = " + filter.departureToNow(list));
        System.out.println("filter.arrivalBeforeDeparture(list) = " + filter.arrivalBeforeDeparture(list));
        System.out.println("filter.lessThenTwoHours(list) = " + filter.lessThenTwoHours(list));
    }
}