import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Competitor {
    String lastName;
    String firstName;
    String country;
    int tableNumber;
    int birthYear;
    String category;

    public Competitor(String lastName, String firstName, String country, int tableNumber, int birthYear, String category) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.country = country;
        this.tableNumber = tableNumber;
        this.birthYear = birthYear;
        this.category = category;
    }

    @Override
    public String toString() {
        return lastName + "," + firstName + "," + country + "," + tableNumber + "," + birthYear + "," + category;
    }
}

class LinkedList {
    Node head;

    class Node {
        Competitor competitor;
        Node next;

        Node(Competitor competitor) {
            this.competitor = competitor;
            this.next = null;
        }
    }

    public void insert(Competitor competitor) {
        Node newNode = new Node(competitor);
        if (head == null) {
            head = newNode;
        } else {
            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        System.out.println("Competitor added: " + competitor.lastName + ", " + competitor.firstName);
    }

    public void search(String lastName) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.competitor.lastName.equals(lastName)) {
                System.out.println("Competitor found: " + current.competitor.toString());
                found = true;
                break;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("Competitor with last name " + lastName + " not found.");
        }
    }

    public void delete(String lastName) {
        Node current = head;
        Node prev = null;
        boolean found = false;
        while (current != null) {
            if (current.competitor.lastName.equals(lastName)) {
                found = true;
                break;
            }
            prev = current;
            current = current.next;
        }
        if (!found) {
            System.out.println("Competitor with last name " + lastName + " not found.");
        } else {
            if (prev == null) {
                head = current.next;
            } else {
                prev.next = current.next;
            }
            System.out.println("Competitor deleted: " + current.competitor.toString());
        }
    }


    public void traverse() {
        Node current = head;
        System.out.println("Competitors: ");
        while (current != null) {
            System.out.println(current.competitor.toString());
            current = current.next;
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList list = new LinkedList();
        String filePath = "C:\\Users\\ACER\\Desktop\\настя\\2 курс\\ASDC\\LL3_ASDC\\src\\competitor.csv";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Scanner scanner = null;
        int index = 0;
        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            String lastName = "";
            String firstName = "";
            String country = "";
            int tableNumber = 0;
            int birthYear = 0;
            String category = "";
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0) {
                    lastName = data;
                } else if (index == 1) {
                    firstName = data;
                } else if (index == 2) {
                    country = data;
                } else if (index == 3) {
                    tableNumber = Integer.parseInt(data);
                } else if (index == 4) {
                    birthYear = Integer.parseInt(data);
                } else if (index == 5) {
                    category = data;
                } else {
                    System.out.println("Invalid data: " + data);
                }
                index++;
            }
            index = 0;
            Competitor competitor = new Competitor(lastName, firstName, country, tableNumber, birthYear, category);
            list.insert(competitor);
        }
        scanner.close();
        reader.close();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1 - Insert competitor");
            System.out.println("2 - Search competitor by last name");
            System.out.println("3 - Delete competitor by last name");
            System.out.println("4 - Traverse competitors list");
            System.out.println("0 - Exit");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nEnter competitor details:");
                    System.out.print("Last name: ");
                    String lastName = input.next();
                    System.out.print("First name: ");
                    String firstName = input.next();
                    System.out.print("Country: ");
                    String country = input.next();
                    System.out.print("Table number: ");
                    int tableNumber = input.nextInt();
                    System.out.print("Birth year: ");
                    int birthYear = input.nextInt();
                    System.out.print("Category: ");
                    String category = input.next();
                    Competitor competitor = new Competitor(lastName, firstName, country, tableNumber, birthYear, category);
                    list.insert(competitor);
                    break;
                case 2:
                    System.out.println("\nEnter competitor last name: ");
                    String searchLastName = input.next();
                    list.search(searchLastName);
                    break;
                case 3:
                    System.out.println("\nEnter competitor last name: ");
                    String deleteLastName = input.next();
                    list.delete(deleteLastName);
                    break;
                case 4:
                    list.traverse();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
