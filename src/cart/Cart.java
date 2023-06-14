package cart;

import item.Car;

import java.awt.print.Book;
import java.util.ArrayList;

public class Cart implements CartInterface {
    public ArrayList<CartItem> mCartItem = new ArrayList<CartItem>();
    public static int mCartCount = 0;
    public Cart() {
    }

    public void printCarList(ArrayList<Car> carlist) {
        for (int i = 0; i < carlist.size(); i++) {
            Car caritem = carlist.get(i);
            System.out.print(caritem.getCarId() + " | ");
            System.out.print(caritem.getName() + " | ");
            System.out.print(caritem.getUnitPrice() + " | ");
            System.out.print(caritem.getProducer() + " | ");
            System.out.print(caritem.getDescription() + " | ");
            System.out.print(caritem.getCategory() + " | ");
            System.out.print(caritem.getReleaseDate());
            System.out.println("");
        }
    }

    public void insertCar(Car car) {
        CartItem caritem = new CartItem(car);
        mCartItem.add(caritem);
        mCartCount = mCartItem.size();
    }

    public void deleteCar() {
        mCartItem.clear();
        mCartCount = 0;
    }

    public void printCart() {
        System.out.println("장바구니 상품 목록");
        System.out.println("---------------------------------------------------");
        System.out.println("    제품ID \t|    수량 \t|  합계);");

        for (int i = 0; i < mCartItem.size(); i++) {
            System.out.print("  " + mCartItem.get(i).getCarID() + " \t| ");
            System.out.print("  " + mCartItem.get(i).getQuantity() + " \t| ");
            System.out.print("  " + mCartItem.get(i).getTotalPrice() + " \t| ");
            System.out.println("    ");
        }
        System.out.println("---------------------------------------------------");
    }
    public boolean isCartInCar(String carId) {
        boolean flag = false;

        for (int i = 0; i < mCartItem.size(); i++) {
            if (carId.equals(mCartItem.get(i).getCarID())) {
                mCartItem.get(i).setQuantity(mCartItem.get(i).getQuantity() + 1);
                flag = true;
            }
        }
        return flag;
    }

    public void removeCart(int numId) {
        mCartItem.remove(numId);
        mCartCount = mCartItem.size();
    }
    public ArrayList<CartItem> getmCartItem() {
        return mCartItem;
    }

    public void setmCartItem(ArrayList<CartItem> mCartItem) {
        this.mCartItem = mCartItem;
    }
}
