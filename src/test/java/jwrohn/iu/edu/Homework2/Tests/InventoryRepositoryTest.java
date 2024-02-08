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
        Guitar testGuitar = new Guitar("123", 1000.0, Guitar.Builder.FENDER.toString(), "Stratocaster", Guitar.Type.ELECTRIC.toString(), Guitar.Wood.ALDER.toString(), Guitar.Wood.MAPLE.toString());
        boolean result = inventoryRepository.addGuitar(testGuitar);
        assertTrue(result);
        List<String> lines = Files.readAllLines(Paths.get(TEST_DATABASE_NAME));
        assertEquals(1, lines.size());
        assertEquals("123,1000.0,Fender,Stratocaster,electric,Alder,Maple", lines.get(0));
    }

    @Test
    public void testGetGuitar() throws IOException {
        Guitar testGuitar = new Guitar("456", 1200.0, Guitar.Builder.GIBSON.toString(), "Les Paul", Guitar.Type.ELECTRIC.toString(), Guitar.Wood.MAHOGANY.toString(), Guitar.Wood.MAPLE.toString());
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
        Guitar testGuitar1 = new Guitar("101", 800.0, Guitar.Builder.MARTIN.toString(), "Acoustic-1", Guitar.Type.ACOUSTIC.toString(), Guitar.Wood.MAHOGANY.toString(), Guitar.Wood.MAPLE.toString());
        Guitar testGuitar2 = new Guitar("102", 900.0, Guitar.Builder.RYAN.toString(), "Acoustic-2", Guitar.Type.ACOUSTIC.toString(), Guitar.Wood.BRAZILIAN_ROSEWOOD.toString(), Guitar.Wood.CEDAR.toString());
        inventoryRepository.addGuitar(testGuitar1);
        inventoryRepository.addGuitar(testGuitar2);
        List<Guitar> result = inventoryRepository.search(new Guitar(null, 0.0, Guitar.Builder.RYAN.toString(), null, Guitar.Type.ACOUSTIC.toString(), null, null));
        assertEquals(1, result.size());
        assertEquals(testGuitar2, result.get(0));
    }

    @Test
    public void testSearchNoMatch() throws IOException {
        List<Guitar> result = inventoryRepository.search(new Guitar(null, 0.0, Guitar.Builder.FENDER.toString(), null, Guitar.Type.ELECTRIC.toString(), null, null));
        assertEquals(0, result.size());
    }
}