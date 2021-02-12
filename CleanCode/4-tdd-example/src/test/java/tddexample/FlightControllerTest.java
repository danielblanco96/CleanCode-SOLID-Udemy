package tddexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
        String flightReference = "1";
        LocalDate flightDate = LocalDate.of(2021, 01, 01);
        LocalTime flightTime = LocalTime.of(10, 30);
        String flightOrigin = "Madrid";
        String flightDestination = "Barcelona";

        Flight flight = new Flight(flightReference, LocalDateTime.of(flightDate, flightTime),
                flightOrigin, flightDestination);

        flightController.addFlight(flight);

        assertEquals(flight, flightController.findFlightByReference(flightReference));
    }

    @Test
    public void givenEmptyFlightController_whenGetByReference_thenThrowFlightNotFoundException() {
        assertThrows(FlightNotFoundException.class, () -> {
            flightController.findFlightByReference("1");
        });
    }

    @Test
    public void givenFlightControllerWithOneFlight_whenFlightWithSameReferenceAdded_thenThrowDuplicateFlightException() {
        String flightReference = "1";
        LocalDate flightDate = LocalDate.of(2021, 01, 01);
        LocalTime flightTime = LocalTime.of(10, 30);
        String flightOrigin = "Madrid";
        String flightDestination = "Barcelona";

        Flight flight = new Flight(flightReference, LocalDateTime.of(flightDate, flightTime),
                flightOrigin, flightDestination);

        flightController.addFlight(flight);

        assertThrows(DuplicateFlightException.class, () -> {
            flightController.addFlight(new Flight("1", null, null, null));
        });
    }

    @Test
    public void givenFlightControllerWithOneFlight_whenFlightWithDifferentReferenceAdded_thenGetByReferenceReturnsBothFlights() {
        String flightReference = "1";
        LocalDate flightDate = LocalDate.of(2021, 01, 01);
        LocalTime flightTime = LocalTime.of(10, 30);
        String flightOrigin = "Madrid";
        String flightDestination = "Barcelona";

        Flight firstFlight = new Flight(flightReference, LocalDateTime.of(flightDate, flightTime),
                flightOrigin, flightDestination);

        String secondFlightReference = "2";
        String secondFlightDestination = "London";
        Flight secondFlight =
                new Flight(secondFlightReference, LocalDateTime.of(flightDate, flightTime),
                        flightOrigin, secondFlightDestination);

        flightController.addFlight(firstFlight);
        flightController.addFlight(secondFlight);

        assertEquals(firstFlight, flightController.findFlightByReference(flightReference));
        assertEquals(secondFlight, flightController.findFlightByReference(secondFlightReference));
    }

    @Test
    public void givenFlightControllerWithMultipleFlights_whenFindByOrigin_returnListWithFlights() {
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
}
