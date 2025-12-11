package Java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FlightService {

    private final List<Flight> flights = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public FlightService() {
        // Preload some sample flights (in-memory data)
        SampleDataLoader.loadSampleFlights(flights);
    }
    public List<Flight> searchFlights(String destination, LocalDate date) {
        Objects.requireNonNull(destination, "destination must not be null");
        Objects.requireNonNull(date, "date must not be null");

        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            boolean destinationMatches = flight.getDestination().equalsIgnoreCase(destination.trim());
            boolean dateMatches = flight.getDepartureTime().toLocalDate().equals(date);

            if (destinationMatches && dateMatches && flight.getAvailableSeats() > 0) {
                result.add(flight);
            }
        }
        return result;
    }

    public Reservation bookFlight(String customerName, Flight flight, int seats) {
        Objects.requireNonNull(customerName, "customerName must not be null");
        Objects.requireNonNull(flight, "flight must not be null");

        if (!flights.contains(flight)) {
            throw new IllegalArgumentException("Java.Flight is not managed by this service");
        }

        // This will throw if seats would go below zero
        flight.reserveSeats(seats);

        Reservation reservation = new Reservation(customerName, flight, seats);
        reservations.add(reservation);
        return reservation;
    }
    public List<Reservation> getReservationsForCustomer(String customerName) {
        Objects.requireNonNull(customerName, "customerName must not be null");
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerName().equalsIgnoreCase(customerName.trim())) {
                result.add(reservation);
            }
        }
        return result;

}

public List<Flight> getAllFlights() {
        return Collections.unmodifiableList(flights);
    }
}
