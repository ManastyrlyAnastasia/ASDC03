import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompetitorReader {
    public static List<Competitor> readCompetitors(String filePath) {
        List<Competitor> competitors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String lastName = fields[0].trim();
                String firstName = fields[1].trim();
                String country = fields[2].trim();
                int birthYear = Integer.parseInt(fields[3].trim());
                int tableNumber = Integer.parseInt(fields[4].trim());
                String category = fields[5].trim();
                Competitor competitor = new Competitor(lastName, firstName, country, birthYear, tableNumber, category);
                competitors.add(competitor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return competitors;
    }
}