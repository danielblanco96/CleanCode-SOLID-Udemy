package tddexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlightControllerTest {

    private FlightController flightController;

    @BeforeEach
    private void setUp() {
        flightController = new FlightController();
    }

    @Test
    public void givenEmptyFlightController_whenFlightAdded_thenGetByReferenceReturnsThatFlight() {
        Flight flight = getDefaultFlight();
        flightController.addFlight(flight);

        assertEquals(flight, flightController.findFlightByReference(flight.getReference()));
    }

    @Test
    public void givenEmptyFlightController_whenGetByReference_thenThrowFlightNotFoundException() {
        assertThrows(FlightNotFoundException.class, () -> {
            flightController.findFlightByReference("1");
        });
    }

    @Test
    public void givenFlightControllerWithOneFlight_whenFlightWithSameReferenceAdded_thenThrowDuplicateFlightException() {
        Flight flight = getDefaultFlight();

        flightController.addFlight(flight);

        assertThrows(DuplicateFlightException.class, () -> {
            flightController.addFlight(new Flight(flight.getReference(), null, null, null));
        });
    }

    @Test
    public void givenFlightControllerWithOneFlight_whenFlightWithDifferentReferenceAdded_thenGetByReferenceReturnsBothFlights() {
        Flight firstFlight = getDefaultFlight();

        Flight secondFlight = new Flight("2", firstFlight.getDepartureTime(), "Madrid", "London");

        flightController.addFlight(firstFlight);
        flightController.addFlight(secondFlight);

        assertEquals(firstFlight,
                flightController.findFlightByReference(firstFlight.getReference()));
        assertEquals(secondFlight,
                flightController.findFlightByReference(secondFlight.getReference()));
    }

    @Test
    public void givenFlightControllerWithMultipleFlights_whenFindByOrigin_thenReturnListWithFlights() {
        Flight firstMadridFlight = new Flight("1", null, "Madrid", null);
        Flight secondMadridFlight = new Flight("2", null, "Madrid", null);
        Flight firstBarcelonaFlight = new Flight("3", null, "Barcelona", null);
        Flight firstLondonFlight = new Flight("4", null, "London", null);

        flightController.addFlight(firstMadridFlight);
        flightController.addFlight(secondMadridFlight);
        flightController.addFlight(firstBarcelonaFlight);
        flightController.addFlight(firstLondonFlight);

        List<Flight> expectedFlights = new ArrayList<Flight>();
        expectedFlights.add(firstMadridFlight);
        expectedFlights.add(secondMadridFlight);

        assertEquals(expectedFlights, flightController.findFlightsByOrigin("Madrid"));
    }

    @Test
    public void givenFlightControllerWithFlight_whenDeleteFlight_thenGetReferenceReturnsFlightNotFoundException() {
        Flight flight = getDefaultFlight();
        flightController.addFlight(flight);

        flightController.deleteFlight(flight.getReference());

        assertThrows(FlightNotFoundException.class, () -> {
            flightController.findFlightByReference(flight.getReference());
        });
    }

    @Test
    public void givenEmptyFlightController_whenDeleteFlight_thenThrowFlightNotFoundException() {
        assertThrows(FlightNotFoundException.class, () -> {
            flightController.findFlightByReference("1");
        });
    }

    @Test
    public void givenEmptyFlightController_whenFindFlightsByDepartureTimeRange_thenReturnEmptyList() {
        LocalDateTime from = LocalDateTime.of(LocalDate.of(2021, 1, 1), LocalTime.of(10, 30));
        LocalDateTime to = LocalDateTime.of(LocalDate.of(2021, 1, 2), LocalTime.of(10, 30));

        List<Flight> result = flightController.findFlightsByDepartureTimeRange(from, to);

        assertTrue(result.isEmpty());
    }

    @Test
    public void givenFlightControllerWithFlights_whenFindFlightsByDepartureTimeRange_thenReturnFlightsInRange() {
        Flight firstFlight = getDefaultFlight();
        Flight secondFlight = getDefaultFlight();
        Flight thirdFlight = getDefaultFlight();

        LocalDate secondFlightDate = LocalDate.of(2021, 01, 05);
        LocalTime secondFlightTime = LocalTime.of(10, 40);
        LocalDate thirdFlightDate = LocalDate.of(2021, 01, 05);
        LocalTime thirdFlightTime = LocalTime.of(10, 41);

        secondFlight.setReference("2");
        secondFlight.setDepartureTime(LocalDateTime.of(secondFlightDate, secondFlightTime));
        thirdFlight.setReference("3");
        thirdFlight.setDepartureTime(LocalDateTime.of(thirdFlightDate, thirdFlightTime));

        flightController.addFlight(firstFlight);
        flightController.addFlight(secondFlight);
        flightController.addFlight(thirdFlight);

        LocalDateTime searchFrom = LocalDateTime.of(LocalDate.of(2021, 1, 1), LocalTime.of(10, 30));
        LocalDateTime searchTo = LocalDateTime.of(LocalDate.of(2021, 1, 5), LocalTime.of(10, 40));

        List<Flight> result = flightController.findFlightsByDepartureTimeRange(searchFrom, searchTo);
        
        assertEquals(Arrays.asList(firstFlight, secondFlight), result);
    }

    private Flight getDefaultFlight() {
        String flightReference = "1";
        LocalDate flightDate = LocalDate.of(2021, 01, 01);
        LocalTime flightTime = LocalTime.of(10, 30);
        String flightOrigin = "Madrid";
        String flightDestination = "Barcelona";

        return new Flight(flightReference, LocalDateTime.of(flightDate, flightTime),
                flightOrigin, flightDestination);
    }
}
