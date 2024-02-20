package jwrohn.iu.edu.Homework2.model;
public class Guitar {
    private String serialNumber;
    private Double price;
    private Builder builder;
    private String model;
    private Type type;
    private Wood backWood;
    private Wood topWood;

    public Guitar(String serialNumber, Double price, Builder builder, String model, Type type, Wood backWood, Wood topWood){
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
    }


    public String getSerialNumber() {
        return this.serialNumber;
    }

    public Double getPrice() {
        return this.price;
    }

    public Builder getBuilder(){
        return this.builder;
    }

    // returns string val of model
    public String getModel(){
        return this.model;
    }

    public Type getType(){
        return this.type;
    }

    public Wood getBackWood(){
        return this.backWood;
    }

    public Wood getTopWood(){
        return this.topWood;
    }

    public boolean isSame(Guitar other) {
        if (other == null) {
            return false;
        }
        if (this.serialNumber != null && !this.serialNumber.equals(other.getSerialNumber())) {
            return false;
        }
        if (this.price != null && !this.price.equals(other.getPrice())) {
            return false;
        }
        if (this.builder != null && !this.builder.equals(other.getBuilder())) {
            return false;
        }
        if (this.model != null && !this.model.equals(other.getModel())) {
            return false;
        }
        if (this.type != null && !this.type.equals(other.getType())) {
            return false;
        }
        if (this.backWood != null && !this.backWood.equals(other.getBackWood())) {
            return false;
        }
        if (this.topWood != null && !this.topWood.equals(other.getTopWood())) {
            return false;
        }
        return true;
    }
}