package jwrohn.iu.edu.Homework2.Repository;

import jwrohn.iu.edu.Homework2.model.Guitar;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {

    private static final String NEW_LINE = System.lineSeparator();
    private static String DATABASE_NAME = "guitars_database.txt";

    private static void appendToFile(Path path, String content) throws IOException {
        Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public boolean addGuitar(Guitar guitar) {
        Path path = Paths.get(DATABASE_NAME);
        String data = guitar.getSerialNumber() + "," +
                guitar.getPrice() + "," +
                guitar.getBuilder() + "," +
                guitar.getModel() + "," +
                guitar.getType() + "," +
                guitar.getBackWood() + "," +
                guitar.getTopWood();
        try {
            appendToFile(path, data + NEW_LINE);
            return true;
        } catch (IOException e) {
            System.err.println("Error adding guitar to file: " + e.getMessage());
            return false;
        }
    }

    public Guitar getGuitar(String serialNumber) {
        Path path = Paths.get(DATABASE_NAME);
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[0].equals(serialNumber)) {
                    return new Guitar(
                            parts[0],
                            Double.parseDouble(parts[1]),
                            parts[2],
                            parts[3],
                            parts[4],
                            parts[5],
                            parts[6]
                    );
                }
            }
        } catch (IOException e) {
            // Log the error or handle it appropriately
            System.err.println("Error reading guitar from file: " + e.getMessage());
        }

        return null;
    }

    public List<Guitar> search(Guitar searchCriteria) {
        Path path = Paths.get(DATABASE_NAME);
        List<Guitar> matchingGuitars = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 7 && matchesSearchCriteria(parts, searchCriteria)) {
                    matchingGuitars.add(new Guitar(
                            parts[0],
                            Double.parseDouble(parts[1]),
                            parts[2],
                            parts[3],
                            parts[4],
                            parts[5],
                            parts[6]
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error searching for guitars in file: " + e.getMessage());
        }

        return matchingGuitars;
    }

    private boolean matchesSearchCriteria(String[] guitarParts, Guitar searchCriteria) {
        return guitarParts[2].equalsIgnoreCase(searchCriteria.getBuilder())
                && guitarParts[3].equalsIgnoreCase(searchCriteria.getModel())
                && guitarParts[4].equalsIgnoreCase(searchCriteria.getType())
                && guitarParts[5].equalsIgnoreCase(searchCriteria.getBackWood())
                && guitarParts[6].equalsIgnoreCase(searchCriteria.getTopWood());
    }
    public InventoryRepository(String databaseName) {
        this.DATABASE_NAME = databaseName;
    }
}