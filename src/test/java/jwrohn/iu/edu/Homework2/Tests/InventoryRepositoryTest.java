package jwrohn.iu.edu.Homework2.Tests;
import jwrohn.iu.edu.Homework2.model.Guitar;
import jwrohn.iu.edu.Homework2.Repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryRepositoryTest {

    private static final String TEST_DATABASE_NAME = "test_guitars_database.txt";

    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void setUp() {
        inventoryRepository = new InventoryRepository(TEST_DATABASE_NAME);
    }


    @Test
    public void testAddGuitar() throws IOException {
        Guitar testGuitar = new Guitar("123", 1000.0, "Fender", "Stratocaster", "Electric", "Alder", "Maple");
        boolean result = inventoryRepository.addGuitar(testGuitar);
        assertTrue(result);
        List<String> lines = Files.readAllLines(Paths.get(TEST_DATABASE_NAME));
        assertEquals(1, lines.size());
        assertEquals("123,1000.0,Fender,Stratocaster,Electric,Alder,Maple", lines.get(0));
    }

    @Test
    public void testGetGuitar() throws IOException {
        Guitar testGuitar = new Guitar("456", 1200.0, "Gibson", "Les Paul", "Electric", "Mahogany", "Maple");
        inventoryRepository.addGuitar(testGuitar);
        Guitar result = inventoryRepository.getGuitar("456");
        assertNotNull(result);
        assertEquals(testGuitar, result);
    }

    @Test
    public void testGetGuitarNotFound() throws IOException {
        Guitar result = inventoryRepository.getGuitar("789");
        assertNull(result);
    }

    @Test
    public void testSearch() throws IOException {
        Guitar testGuitar1 = new Guitar("101", 800.0, "Taylor", "Acoustic-1", "Acoustic", "Mahogany", "Spruce");
        Guitar testGuitar2 = new Guitar("102", 900.0, "Martin", "Acoustic-2", "Acoustic", "Rosewood", "Cedar");
        inventoryRepository.addGuitar(testGuitar1);
        inventoryRepository.addGuitar(testGuitar2);
        List<Guitar> result = inventoryRepository.search(new Guitar(null, 0.0, "Taylor", null, "Acoustic", null, null));
        assertEquals(1, result.size());
        assertEquals(testGuitar1, result.get(0));
    }

    @Test
    public void testSearchNoMatch() throws IOException {
        List<Guitar> result = inventoryRepository.search(new Guitar(null, 0.0, "Fender", null, "Electric", null, null));
        assertEquals(0, result.size());
    }
}