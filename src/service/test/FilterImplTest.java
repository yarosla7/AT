package service.test;

import com.gridnine.testing.FlightBuilder.Flight;
import com.gridnine.testing.FlightBuilder.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.FilterImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterImplTest {

    @Test
    void testDepartureToNow() {
        // given
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().minusMonths(2), LocalDateTime.now().plusHours(4)))));
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(8).plusMonths(2)))));
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.departureToNow(flights);

        // then
        Assertions.assertEquals(1, filtered.size());
        Assertions.assertEquals(flights.get(0), filtered.get(0));
    }

    @Test
    void testDepartureToNowWithNoFlights() {
        // given
        List<Flight> flights = new ArrayList<>();
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.departureToNow(flights);

        // then
        Assertions.assertEquals(0, filtered.size());
    }

    @Test
    void testDepartureToNowWithFutureFlights() {
        // given
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4)))));
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(8)))));
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.departureToNow(flights);

        // then
        Assertions.assertEquals(0, filtered.size());
    }

    @Test
    void testDepartureToNowWithPastFlights() {
        // given
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().minusHours(2), LocalDateTime.now().minusHours(1)))));
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().minusHours(6), LocalDateTime.now().minusHours(4)))));
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.departureToNow(flights);

        // then
        Assertions.assertEquals(2, filtered.size());
    }

    //==================================================================================================================
    @Test
    void testArrivalBeforeDepartureWithNoMatches() {
        // given
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4)))));
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(8)))));
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.arrivalBeforeDeparture(flights);

        // then
        Assertions.assertEquals(0, filtered.size());
    }


    @Test
    void testArrivalBeforeDepartureWithEmptyList() {
        // given
        List<Flight> flights = new ArrayList<>();
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.arrivalBeforeDeparture(flights);

        // then
        Assertions.assertTrue(filtered.isEmpty());
    }

    @Test
    void testArrivalBeforeDepartureWithOneFlight() {
        // given
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4)))));
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.arrivalBeforeDeparture(flights);

        // then
        Assertions.assertTrue(filtered.isEmpty());
    }

    //===========================================================================================================

    @Test
    void testLessThenTwoHours() {
        // given
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3)),
                        new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(6)))));
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5)),
                        new Segment(LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(9)))));
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(7)),
                        new Segment(LocalDateTime.now().plusHours(8), LocalDateTime.now().plusHours(10)))));
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.lessThenTwoHours(flights);

        // then
        Assertions.assertEquals(1, filtered.size());
        Assertions.assertEquals(flights.get(1), filtered.get(0));
    }

    @Test
    void testLessThenTwoHoursEmpty() {
        // given
        List<Flight> flights = new ArrayList<>();
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.lessThenTwoHours(flights);

        // then
        Assertions.assertTrue(filtered.isEmpty());
    }

    @Test
    void testLessThenTwoHoursOneFlight() {
        // given
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(
                List.of(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3)),
                        new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(6)))));
        FilterImpl filter = new FilterImpl();

        // when
        List<Flight> filtered = filter.lessThenTwoHours(flights);

        // then
        Assertions.assertTrue(filtered.isEmpty());
    }
}