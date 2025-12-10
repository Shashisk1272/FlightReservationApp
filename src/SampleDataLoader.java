import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SampleDataLoader {
    private SampleDataLoader() {
    }

    static void loadSampleFlights(List<Flight> flights) {
        LocalDate today = LocalDate.now();

        flights.add(new Flight("AI101", "New York",
                LocalDateTime.of(today, LocalTime.of(9, 30)), 50));
        flights.add(new Flight("AI102", "Chicago",
                LocalDateTime.of(today, LocalTime.of(15, 0)), 20));
        flights.add(new Flight("6E201", "Seattle",
                LocalDateTime.of(today.plusDays(1), LocalTime.of(11, 15)), 30));
        flights.add(new Flight("6E202", "Dallas",
                LocalDateTime.of(today.plusDays(1), LocalTime.of(18, 45)), 10));
        flights.add(new Flight("SG301", "penn",
                LocalDateTime.of(today, LocalTime.of(13, 0)), 5));
        flights.add(new Flight("SG302", "Pits",
                LocalDateTime.of(today.plusDays(2), LocalTime.of(8, 0)), 100));
    }
}
