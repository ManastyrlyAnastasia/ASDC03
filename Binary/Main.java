import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\ACER\\Desktop\\настя\\2 курс\\ASDC\\LL3_ASDC\\src\\competitor.csv";
        List<Competitor> competitors = CompetitorReader.readCompetitors(filePath);
        for (Competitor competitor : competitors) {
            System.out.println(competitor.getLastName() + " " + competitor.getFirstName() + " " + competitor.getCountry() + " " + competitor.getBirthYear() + " " + competitor.getTableNumber() + " " + competitor.getCategory());
        }
    }
}
