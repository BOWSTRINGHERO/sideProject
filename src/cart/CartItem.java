package cart;

import java.awt.print.Book;

public class CartItem {
    private Book itemCar;
    private String carID;
    private int quantity;
    private int totalPrice;

    public CartItem() {
    }

    public CartItem(Book carlist) {
        this.itemCar = carlist;
        this.carID = carlist.getCarId();
        this.quantity = 1;
        updateTotalPrice();
    }

    public Book getItemCar() {
        return itemCar;
    }

    public void setItemCar(Book itemCar) {
        this.itemCar = itemCar;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void updateTotalPrice() {
        totalPrice = this.itemCar.getUnitPrice() * this.quantity;
    }
}
