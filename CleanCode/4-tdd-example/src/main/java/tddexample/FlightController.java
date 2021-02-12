package tddexample;

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

}
