package cart;

import item.Car;

import java.util.ArrayList;

public interface CartInterface {
    void printCarList(ArrayList<Car> p);

    boolean isCartInCar(String id);

    void insertCar(Car p);

    void removeCart(int numId);

    void deleteCar();
}
