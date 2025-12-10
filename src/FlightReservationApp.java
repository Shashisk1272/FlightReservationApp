import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlightReservationApp {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        FlightService flightService = new FlightService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Flight Reservation System ===");

        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1 -> handleSearchFlights(scanner, flightService);
                case 2 -> handleBookFlight(scanner, flightService);
                case 3 -> handleViewReservations(scanner, flightService);
                case 4 -> {
                    System.out.println("Exiting application. Goodbye!");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Search flights by destination and date");
        System.out.println("2. Book a flight");
        System.out.println("3. View all reservations for a customer");
        System.out.println("4. Exit");
    }

    private static void handleSearchFlights(Scanner scanner, FlightService flightService) {
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine().trim();

        LocalDate date = readDate(scanner, "Enter date (yyyy-MM-dd): ");

        List<Flight> flights = flightService.searchFlights(destination, date);
        if (flights.isEmpty()) {
            System.out.println("No available flights found for " + destination + " on " + date);
        } else {
            System.out.println("Available flights:");
            for (int i = 0; i < flights.size(); i++) {
                System.out.println((i + 1) + ". " + flights.get(i));
            }
        }
    }

    private static void handleBookFlight(Scanner scanner, FlightService flightService) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();

        System.out.println("Available flights:");
        List<Flight> flights = flightService.getAllFlights();
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i));
        }

        if (flights.isEmpty()) {
            System.out.println("No flights available to book.");
            return;
        }

        int index = readInt(scanner, "Select flight number from the list (1-" + flights.size() + "): ");
        if (index < 1 || index > flights.size()) {
            System.out.println("Invalid selection. Booking cancelled.");
            return;
        }

        Flight selectedFlight = flights.get(index - 1);

        int seats = readInt(scanner, "Enter number of seats to book: ");
        if (seats <= 0) {
            System.out.println("Seats must be greater than 0. Booking cancelled.");
            return;
        }

        try {
            Reservation reservation = flightService.bookFlight(customerName, selectedFlight, seats);
            System.out.println("Booking successful: " + reservation);
        } catch (IllegalArgumentException ex) {
            System.out.println("Booking failed: " + ex.getMessage());
        }
    }

    private static void handleViewReservations(Scanner scanner, FlightService flightService) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();

        List<Reservation> reservations = flightService.getReservationsForCustomer(customerName);
        if (reservations.isEmpty()) {
            System.out.println("No reservations found for customer: " + customerName);
        } else {
            System.out.println("Reservations for " + customerName + ":");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private static LocalDate readDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd");
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }
}
