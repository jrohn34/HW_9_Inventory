package jwrohn.iu.edu.Homework2.Repository;

import jwrohn.iu.edu.Homework2.model.Guitar;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryRepository {
    private List<Guitar> guitars;

    public InventoryRepository(){
        this.guitars = new ArrayList<>();
    }

    public List<Guitar> getGuitars() {
        return this.guitars;
    }

    public Guitar getGuitar(String serialNumber){
        for(Guitar one : this.guitars)
            if(one.getSerialNumber().equals(serialNumber))
                return one;
        return null;
    }

    public void addGuitar(Guitar New) {
        this.guitars.add(New);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/JoshuaRohn/Desktop/Homework2/src/main/java/jwrohn/Homework2/Repository/guitars_database.txt", true))) {
            String guitarInfo = New.getSerialNumber() + "," + New.getPrice() + "," + New.getBuilder() + "," + New.getModel() + "," + New.getType() + "," + New.getBackWood() + "," + New.getTopWood();
            writer.write(guitarInfo);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Guitar> search(Guitar searchCriteria) {
        List<Guitar> sameGuitars = new ArrayList<>();
        for (Guitar guitar : this.guitars) {
            if (guitar.isSame(searchCriteria)) {
                sameGuitars.add(guitar);
            }
        }
        return sameGuitars;
    }
}