package service;

import com.gridnine.testing.FlightBuilder.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterImpl implements Filter {
    @Override
    public List<Flight> departureToNow(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Flight flight : flights) {
            if (flight.getSegments().get(0).getDepartureDate().isBefore(now)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    @Override
    public List<Flight> arrivalBeforeDeparture(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            LocalDateTime depDate = flight.getSegments().get(flight.getSegments().size() - 1).getDepartureDate();
            if (flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate().isBefore(depDate)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    @Override
    public List<Flight> lessThenTwoHours(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        int minGroundTime = 2;
        for (Flight flight : flights) {
            int groundTime = 0;
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                groundTime += flight.getSegments().get(i + 1).getDepartureDate().getHour() -
                        flight.getSegments().get(i).getArrivalDate().getHour();
            }
            if (groundTime >= minGroundTime) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}