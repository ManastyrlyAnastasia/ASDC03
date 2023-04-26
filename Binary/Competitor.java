import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Competitor {
    private String lastName;
    private String firstName;
    private String country;
    private int birthYear;
    private int tableNumber;
    private String category;

    public Competitor(String lastName, String firstName, String country, int birthYear, int tableNumber, String category) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.country = country;
        this.birthYear = birthYear;
        this.tableNumber = tableNumber;
        this.category = category;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCountry() {
        return country;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getCategory() {
        return category;
    }
}


