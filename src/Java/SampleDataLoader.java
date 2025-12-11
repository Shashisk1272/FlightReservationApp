package Java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SampleDataLoader {
    private SampleDataLoader() {
    }

    static void loadSampleFlights(List<Flight> flights) {
        LocalDate today = LocalDate.now();

        flights.add(new Flight("FL001", "New York", LocalDateTime.of(2025, 12, 15, 10, 0), 100));
        flights.add(new Flight("FL002", "Los Angeles", LocalDateTime.of(2025, 12, 16, 14, 30), 50));
        flights.add(new Flight("FL003", "Chicago", LocalDateTime.of(2025, 12, 17, 9, 15), 75));
        flights.add(new Flight("FL004", "Miami", LocalDateTime.of(2025, 12, 18, 16, 0), 60));
        flights.add(new Flight("FL005", "Seattle", LocalDateTime.of(2025, 12, 19, 12, 45), 80));
        flights.add(new Flight("FL006", "Boston", LocalDateTime.of(2025, 12, 20, 8, 30), 90));
        flights.add(new Flight("FL007", "San Francisco", LocalDateTime.of(2025, 12, 21, 15, 0), 70));
        flights.add(new Flight("FL008", "Denver", LocalDateTime.of(2025, 12, 22, 11, 15), 65));
        flights.add(new Flight("FL009", "Atlanta", LocalDateTime.of(2025, 12, 23, 13, 45), 85));
        flights.add(new Flight("FL010", "Dallas", LocalDateTime.of(2025, 12, 24, 17, 0), 55));
        flights.add(new Flight("FL011", "Orlando", LocalDateTime.of(2025, 12, 25, 9, 30), 95));
        flights.add(new Flight("FL012", "Las Vegas", LocalDateTime.of(2025, 12, 26, 14, 0), 60));
        flights.add(new Flight("FL013", "Houston", LocalDateTime.of(2025, 12, 27, 10, 45), 75));
        flights.add(new Flight("FL014", "Philadelphia", LocalDateTime.of(2025, 12, 28, 12, 15), 80));
        flights.add(new Flight("FL015", "Phoenix", LocalDateTime.of(2025, 12, 29, 16, 30), 70));


    }
}
