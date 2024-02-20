package jwrohn.iu.edu.Homework2.Tests;

import jwrohn.iu.edu.Homework2.model.Builder;
import jwrohn.iu.edu.Homework2.model.Guitar;
import jwrohn.iu.edu.Homework2.Repository.InventoryRepository;
import jwrohn.iu.edu.Homework2.model.Type;
import jwrohn.iu.edu.Homework2.model.Wood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InventoryRepositoryTest {

    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void setUp() {
        inventoryRepository = new InventoryRepository();
    }

    @Test
    public void testAddGuitar() {
        Guitar guitar = new Guitar("123", 1000.0, Builder.FENDER, "Model", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAPLE);
        inventoryRepository.addGuitar(guitar);
        List<Guitar> guitars = inventoryRepository.getGuitars();
        assertEquals(1, guitars.size());
        assertEquals(guitar, guitars.get(0));
    }

    @Test
    public void testAddMultipleGuitars() {
        Guitar guitar1 = new Guitar("123", 1000.00, Builder.FENDER, "Model", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAPLE);
        Guitar guitar2 = new Guitar("456", 1500.00, Builder.GIBSON, "Model", Type.ELECTRIC, Wood.ALDER, Wood.MAHOGANY);
        inventoryRepository.addGuitar(guitar1);
        inventoryRepository.addGuitar(guitar2);
        List<Guitar> guitars = inventoryRepository.getGuitars();
        assertEquals(2, guitars.size());
    }

    @Test
    public void testGetGuitar() {
        Guitar guitar = new Guitar("123", 1000.00, Builder.FENDER, "Model", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAPLE);
        inventoryRepository.addGuitar(guitar);
        Guitar retrievedGuitar = inventoryRepository.getGuitar("123");
        assertEquals(guitar, retrievedGuitar);
    }

    @Test
    public void testGetNonExistingGuitar() {
        assertNull(inventoryRepository.getGuitar("789"));
    }

    @Test
    public void testSearch() {
        Guitar guitar1 = new Guitar("123", 1000.0, Builder.FENDER, "Model", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAPLE);
        Guitar guitar2 = new Guitar("456", 1500.0, Builder.GIBSON, "Model", Type.ELECTRIC, Wood.ALDER, Wood.MAHOGANY);
        inventoryRepository.addGuitar(guitar1);
        inventoryRepository.addGuitar(guitar2);
        Guitar searchCriteria = new Guitar("123", 1000.0, Builder.FENDER, "Model", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAPLE);
        List<Guitar> searchedGuitars = inventoryRepository.search(searchCriteria);
        assertEquals(1, searchedGuitars.size());
        assertEquals(guitar1, searchedGuitars.get(0));
    }

    @Test
    public void testSearchWithNoMatch() {
        Guitar guitar1 = new Guitar("123", 1000.0, Builder.FENDER, "Model", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAPLE);
        Guitar guitar2 = new Guitar("456", 1500.0, Builder.GIBSON, "Model", Type.ELECTRIC, Wood.ALDER, Wood.MAHOGANY);
        inventoryRepository.addGuitar(guitar1);
        inventoryRepository.addGuitar(guitar2);
        Guitar searchCriteria = new Guitar("789", 2000.0, Builder.FENDER, "Model", Type.ACOUSTIC, Wood.MAHOGANY, Wood.MAPLE);
        List<Guitar> searchedGuitars = inventoryRepository.search(searchCriteria);
        assertEquals(0, searchedGuitars.size());
    }
}