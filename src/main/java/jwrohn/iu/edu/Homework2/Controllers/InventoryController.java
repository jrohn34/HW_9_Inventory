package jwrohn.iu.edu.Homework2.Controllers;

import jwrohn.iu.edu.Homework2.model.Guitar;
import jwrohn.iu.edu.Homework2.model.Builder;
import jwrohn.iu.edu.Homework2.model.Type;
import jwrohn.iu.edu.Homework2.model.Wood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jwrohn.iu.edu.Homework2.Repository.InventoryRepository;


import java.util.List;

@RestController
public class InventoryController {

    private final InventoryRepository inventory;

    @Autowired
    public InventoryController(InventoryRepository inventory) {
        this.inventory = inventory;
    }

    @GetMapping("/search")
    public List<Guitar> searchGuitars(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Builder builder,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Type type,
            @RequestParam(required = false) Wood backWood,
            @RequestParam(required = false) Wood topWood) {

        Guitar Criteria = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        return this.inventory.search(Criteria);
    }


    @GetMapping("/find/{serialNumber}")
    public Guitar getGuitar(@PathVariable String serialNumber){
        return this.inventory.getGuitar(serialNumber);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Guitar guitar){
        this.inventory.addGuitar(guitar);
        return true;
    }
}