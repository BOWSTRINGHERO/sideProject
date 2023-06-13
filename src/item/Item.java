package item;

public abstract class Item {
    String carId;
    String name;
    int unitPrice;

    public Item(String carId, String name, int unitPrice) {
        this.carId = carId;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    abstract String getCarId();

    abstract String getName();

    abstract int getUnitPrice();

    abstract void setCarId(String carId);

    abstract void setName(String name);

    abstract void setUnitPrice(int unitPrice);
}
