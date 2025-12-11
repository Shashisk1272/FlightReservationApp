package Test;

import Java.Flight;
import Java.FlightService;
import Java.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    private FlightService flightService;

    @BeforeEach
    void setUp() {
        flightService = new FlightService();
    }

    @Test
    void searchFlights_returnsFlightsForValidDestinationAndDate() {
        LocalDate date = LocalDate.of(2025, 12, 15);  // FL001
        List<Flight> flights = flightService.searchFlights("New York", date);

        assertNotNull(flights);
        assertFalse(flights.isEmpty(), "Expected at least one flight to New York on 2025-12-15");

        for (Flight flight : flights) {
            assertEquals("New York", flight.getDestination());
            assertEquals(date, flight.getDepartureTime().toLocalDate());
            assertTrue(flight.getAvailableSeats() > 0);
        }
    }

    @Test
    void searchFlights_returnsEmptyListWhenNoFlights() {
        List<Flight> flights = flightService.searchFlights("Tokyo", LocalDate.of(2030, 1, 1));

        assertNotNull(flights);
        assertTrue(flights.isEmpty(), "Expected zero flights for an invalid destination");
    }

    @Test
    void bookFlight_reducesAvailableSeats() {
        Flight flight = flightService.getAllFlights().get(0); // FL001
        int originalSeats = flight.getAvailableSeats();

        Reservation reservation = flightService.bookFlight("Alice", flight, 2);

        assertNotNull(reservation);
        Assertions.assertEquals("Alice", reservation.getCustomerName());
        Assertions.assertEquals(2, reservation.getSeatsBooked());
        assertEquals(originalSeats - 2, flight.getAvailableSeats());
    }

    @Test
    void bookFlight_exactAvailableSeatsIsAllowed() {
        Flight flight = flightService.getAllFlights().get(1); // Second flight (any with enough seats)
        int targetSeats = flight.getAvailableSeats();

        Reservation reservation = flightService.bookFlight("Bob", flight, targetSeats);

        assertNotNull(reservation);
        assertEquals(0, flight.getAvailableSeats(), "Seats should be exactly zero after booking all");
    }

    @Test
    void bookFlight_moreSeatsThanAvailable_throwsException() {
        Flight flight = flightService.getAllFlights().get(2); // FL003
        int originalSeats = flight.getAvailableSeats();

        int tooManySeats = originalSeats + 1;

        assertThrows(IllegalArgumentException.class,
                () -> flightService.bookFlight("Charlie", flight, tooManySeats));

        // Ensure seats are unchanged
        assertEquals(originalSeats, flight.getAvailableSeats());
    }

    @Test
    void getReservationsForCustomer_returnsCorrectReservations() {
        Flight flight = flightService.getAllFlights().get(3); // FL004

        flightService.bookFlight("Diana", flight, 1);
        flightService.bookFlight("DIANA", flight, 2); // Test case-insensitive match
        flightService.bookFlight("Other", flight, 1);

        List<Reservation> reservations = flightService.getReservationsForCustomer("diana");

        assertEquals(2, reservations.size());
    }
}
