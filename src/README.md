# Java.Flight Java.Reservation System (Java Console Application)

This project is a console-based Java.Flight Java.Reservation System implemented in Java. It allows users to:
Search for flights by destination and date
Book flight seats
View all reservations made by a specific customer
The system uses in-memory lists to store data (no database), making it simple and focused on core software design principles.

### Features

# 1. Search Flights

## Users can search flights by:

Destination
Date (format: yyyy-MM-dd)
Only flights with available seats are returned.

# 2. Book Flights

Users can:

Choose a flight from the available list
Enter number of seats to book
Prevent overbooking (system validates seat availability)

# 3. View Reservations

Users can view all reservations made under a specific customer name (case-insensitive).

Project Structure
flight-reservation/
-> src/
        -> main/java/com/example/flightreservation/
            -> Java.Flight.java
            -> Java.Reservation.java
            -> Java.FlightService.java
            -> Java.SampleDataLoader.java
            -> Java.FlightReservationApp.java
        
        -> test/java/com/example/flightreservation/
            -> Test.FlightServiceTest.java

-> README.md

How to Run the Application
Prerequisites

Java 17 or above

When you run the application, you will see:

1. Search flights by destination and date
2. Book a flight
3. View all reservations for a customer
4. Exit


Follow on-screen prompts for each action.

# Design Decisions:

# 1. In-Memory Data Structure

Used List<Java.Flight> and List<Java.Reservation> to store data.
Meets requirement: data does not need to persist after application stops.

# 2. Separation of Concerns

Java.Flight and Java.Reservation: domain entities
Java.FlightService: business logic (searching, booking, managing reservations)
Java.FlightReservationApp: console UI handling user interactions
This improves readability and maintainability.

# 3. Validation

Prevents overbooking by checking availableSeats before updating.
Ensures positive seat numbers.
Case-insensitive matching for user-friendly searches.

# 4. Static Test Data

Java.SampleDataLoader provides predictable flight data (Dec 2025), useful for testing and demonstrations.

# 5. Robust Unit Testing

Written using JUnit 5

# Covers:

Java.Flight search logic
Booking logic
Overbooking prevention
Customer reservation lookup
Ensures reliability and correctness.

# 6. Extensibility in Mind

Even though minimal, the structure supports future upgrades:
Connect to database
Expose REST APIs
Add authentication
Expand booking features (cancellation, modification, passenger details)

# **Real-World Considerations**

If this were a production-grade system:
Use a database like PostgreSQL/MySQL
Add concurrency protection for booking
Implement REST endpoints using Spring Boot
Introduce logging, monitoring, and exception handling
Add unique reservation IDs
Integrate payment gateways for real bookings
Add UI (web or mobile) instead of a console interface

This project intentionally keeps things simple to focus on core Java and OOP fundamentals.