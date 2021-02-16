package tddexample;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightController {

    private Map<String, Flight> flights;

    public FlightController() {
        flights = new HashMap<String, Flight>();
    }

    public void addFlight(Flight flight) {
        if (flights.containsKey(flight.getReference())) {
            throw new DuplicateFlightException();
        }

        flights.put(flight.getReference(), flight);
    }

    public void deleteFlight(String reference) {
        if (flights.remove(reference) == null) {
            throw new FlightNotFoundException();
        }
    }

    public Flight findFlightByReference(String flightReference) {
        Flight flight = flights.get(flightReference);
        if (flight == null) {
            throw new FlightNotFoundException();
        }

        return flight;
    }

    public List<Flight> findFlightsByOrigin(String origin) {
        List<Flight> filteredFlights = new ArrayList<Flight>();
        Collection<Flight> currentFlightCollection = flights.values();

        for (Flight f : currentFlightCollection) {
            if (f.getOrigin().equals(origin)) {
                filteredFlights.add(f);
            }
        }

        return filteredFlights;
    }

    public List<Flight> findFlightsByDepartureTimeRange(LocalDateTime from, LocalDateTime to) {
        List<Flight> result = new ArrayList<Flight>();

        for (Flight flight : flights.values()) {
            if (isFlightInsideRangeClosedBounds(flight, from, to)) {
                result.add(flight);
            }
        }

        return result;
    }

    private boolean isFlightInsideRangeClosedBounds(Flight flight, LocalDateTime from,
            LocalDateTime to) {
        return flight.getDepartureTime().isAfter(from.minusMinutes(1))
                && flight.getDepartureTime().isBefore(to.plusMinutes(1));
    }

}
