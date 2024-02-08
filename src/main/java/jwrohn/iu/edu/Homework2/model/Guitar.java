package jwrohn.iu.edu.Homework2.model;
import java.util.Objects;

public class Guitar {

    private String serialNumber;
    private double price;
    private String builder;
    private String model;
    private String type;
    private String backWood;
    private String topWood;


    public Guitar(String serialNumber, double price, String builder, String model, String type, String backWood, String topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackWood() {
        return backWood;
    }

    public void setBackWood(String backWood) {
        this.backWood = backWood;
    }

    public String getTopWood() {
        return topWood;
    }

    public void setTopWood(String topWood) {
        this.topWood = topWood;
    }
    public enum Builder{
        FENDER, MARTIN,GIBSON,COLLINGS,OLSON,RYAN,PRS,ANY;

        @Override
        public String toString() {
            switch(this){
                case FENDER:    return "Fender";
                case MARTIN:    return "Martin";
                case GIBSON:    return "Gibson";
                case COLLINGS:  return "Collings";
                case OLSON:     return "Olson";
                case RYAN:      return "Ryan";
                case PRS:       return "PRS";
                default:        return "Unspecified";
            }
        }
    }
    public enum Type{
        ACOUSTIC,ELECTRIC;

        @Override
        public String toString() {
            switch (this){
                case ACOUSTIC:  return "acoustic";
                case ELECTRIC:  return  "electric";
                default:        return "unspecified";
            }
        }
    }
    public enum Wood{
        INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO,CEDAR,ADIRONDACK,ALDER,SITKA;
        public String toString(){
            switch(this){
                case INDIAN_ROSEWOOD: return "Indian Rosewood";
                case BRAZILIAN_ROSEWOOD:    return "Brazilian Rosewood";
                case MAHOGANY:  return  "Mahogany";
                case MAPLE: return "Maple";
                case COCOBOLO:  return "Cocobolo";
                case CEDAR:     return  "Cedar";
                case ADIRONDACK:    return "Adirondack";
                case ALDER:     return "Alder";
                case SITKA:     return "Sitka";
                default:    return  "Unspecified";

            }
        }
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Guitar other = (Guitar) obj;
        return Double.compare(other.price, price) == 0 &&
                Objects.equals(serialNumber, other.serialNumber) &&
                Objects.equals(builder, other.builder) &&
                Objects.equals(model, other.model) &&
                Objects.equals(type, other.type) &&
                Objects.equals(backWood, other.backWood) &&
                Objects.equals(topWood, other.topWood);
    }
}
