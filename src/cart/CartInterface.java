package cart;

import java.awt.print.Book;
import java.util.ArrayList;

public interface CartInterface {
    void printCarList(ArrayList<Book> p);

    boolean isCartInBook(String id);

    void insertBook(Book p);

    void removeCart(int numId);

    void deleteBook();
}
