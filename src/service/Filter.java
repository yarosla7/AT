package service;

import com.gridnine.testing.FlightBuilder.Flight;

import java.util.List;
/**
 * Интерфейс Filter содержит три метода для фильтрации списка рейсов.
 * @author yarosla7
 * @since 21/07/2023 */

public interface Filter {
    /**
     Метод departureToNow() фильтрует список рейсов, оставляя только те, которые еще не начали свой путь.

     @param flights список рейсов
     @return отфильтрованный список рейсов */
    List<Flight> departureToNow(List<Flight> flights);

    /**
     Метод arrivalBeforeDeparture() фильтрует список рейсов, оставляя только те, которые прибывают до даты вылета.

     @param flights список рейсов
     @return отфильтрованный список рейсов */
    List<Flight> arrivalBeforeDeparture (List<Flight> flights);

    /**
     Метод lessThenTwoHours() фильтрует список рейсов, оставляя только те, которые длительность своего пути менее 2 часов.

     @param flights список рейсов
     @return отфильтрованный список рейсов */
    List<Flight> lessThenTwoHours (List<Flight> flights);
}