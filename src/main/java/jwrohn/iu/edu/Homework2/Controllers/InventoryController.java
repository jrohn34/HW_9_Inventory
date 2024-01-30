package jwrohn.iu.edu.Homework2.Controllers;

import jwrohn.iu.edu.Homework2.model.Guitar;
import org.springframework.web.bind.annotation.*;
import jwrohn.iu.edu.Homework2.model.Guitar;
import jwrohn.iu.edu.Homework2.Repository.InventoryRepository;


import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/search")
    public List<Guitar> searchGuitars(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String builder,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String backWood,
            @RequestParam(required = false) String topWood
    ) {
        return inventoryRepository.search(new Guitar(serialNumber, price, builder, model, type, backWood, topWood));
    }

    @PostMapping("/add")
    public void addGuitar(@RequestBody Guitar guitar) {
        inventoryRepository.addGuitar(guitar);
    }

    @GetMapping("/find/{serialNumber}")
    public Guitar findGuitarBySerialNumber(@PathVariable String serialNumber) {
        return inventoryRepository.getGuitar(serialNumber);
    }
}