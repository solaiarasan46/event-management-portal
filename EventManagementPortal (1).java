import java.util.ArrayList;
import java.util.Scanner;

public class EventManagementPortal {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Event> events = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static int nextEventId = 1;
    static int nextBookingId = 1;

    static class Event {
        int id;
        String name;
        String date;
        String venue;
        int availableSeats;

        Event(int id, String name, String date, String venue, int availableSeats) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.venue = venue;
            this.availableSeats = availableSeats;
        }

        void display() {
            System.out.println("Event ID: " + id
                    + " | Name: " + name
                    + " | Date: " + date
                    + " | Venue: " + venue
                    + " | Available seats: " + availableSeats);
        }
    }

    static class Booking {
        int bookingId;
        int eventId;
        String customerName;
        int tickets;

        Booking(int bookingId, int eventId, String customerName, int tickets) {
            this.bookingId = bookingId;
            this.eventId = eventId;
            this.customerName = customerName;
            this.tickets = tickets;
        }

        void display() {
            System.out.println("Booking ID: " + bookingId
                    + " | Event ID: " + eventId
                    + " | Customer: " + customerName
                    + " | Tickets: " + tickets);
        }
    }

    public static void main(String[] args) {
        // Sample event so the portal can be tested immediately.
        events.add(new Event(nextEventId++, "College Cultural Fest",
                "2026-11-15", "College Auditorium", 100));

        int choice;
        do {
            System.out.println("\n===== EVENT MANAGEMENT PORTAL =====");
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Book Event Tickets");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    viewEvents();
                    break;
                case 3:
                    bookEvent();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    System.out.println("Thank you for using Event Management Portal!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 to 5.");
            }
        } while (choice != 5);

        sc.close();
    }

    static void addEvent() {
        System.out.print("Enter event name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter event date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();

        System.out.print("Enter venue: ");
        String venue = sc.nextLine().trim();

        System.out.print("Enter number of seats: ");
        int seats = readInt();

        if (name.isEmpty() || date.isEmpty() || venue.isEmpty() || seats <= 0) {
            System.out.println("Invalid details. Please enter all details and a positive seat count.");
            return;
        }

        events.add(new Event(nextEventId++, name, date, venue, seats));
        System.out.println("Event added successfully!");
    }

    static void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        System.out.println("\n--- Available Events ---");
        for (Event event : events) {
            event.display();
        }
    }

    static void bookEvent() {
        if (events.isEmpty()) {
            System.out.println("No events available for booking.");
            return;
        }

        viewEvents();

        System.out.print("Enter the Event ID you want to book: ");
        int eventId = readInt();
        Event selectedEvent = findEvent(eventId);

        if (selectedEvent == null) {
            System.out.println("Event ID not found.");
            return;
        }

        System.out.print("Enter your name: ");
        String customerName = sc.nextLine().trim();

        System.out.print("Enter number of tickets: ");
        int tickets = readInt();

        if (customerName.isEmpty() || tickets <= 0) {
            System.out.println("Please enter a valid name and ticket count.");
            return;
        }

        if (tickets > selectedEvent.availableSeats) {
            System.out.println("Sorry, only " + selectedEvent.availableSeats + " seats are available.");
            return;
        }

        selectedEvent.availableSeats -= tickets;
        bookings.add(new Booking(nextBookingId++, eventId, customerName, tickets));
        System.out.println("Booking successful!");
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\n--- Booking Details ---");
        for (Booking booking : bookings) {
            booking.display();
        }
    }

    static Event findEvent(int id) {
        for (Event event : events) {
            if (event.id == id) {
                return event;
            }
        }
        return null;
    }

    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.nextLine();
        }
        int value = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline.
        return value;
    }
}
