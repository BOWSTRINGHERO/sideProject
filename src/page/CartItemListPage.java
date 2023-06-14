package page;

import cart.Cart;
import cart.CartItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartItemListPage extends JPanel {
    JTable cartTable;
    Object[] tableHeader = {"제품ID", "제품명", "단가(가격)", "수량", "총가격"};

    Cart mCart = new Cart();
    public static int mSelectRow = -1;

    public CartItemListPage(JPanel panel, Cart cart) {
        Font ft;
        ft = new Font("함초롱돋움", Font.BOLD, 15);
        this.mCart = cart;
        this.setLayout(null);

        Rectangle rect = panel.getBounds();
        System.out.println(rect);
        this.setPreferredSize(rect.getSize());

        JPanel carPanel = new JPanel();
        carPanel.setBounds(0, 0, 1000, 400);
        add(carPanel);

        ArrayList<CartItem> cartItem = mCart.getmCartItem();
        Object[][] content = new Object[cartItem.size()][tableHeader.length];
        Integer totalPrice = 0;
        for (int i = 0; i < cartItem.size(); i++) {
            CartItem item = cartItem.get(i);
            content[i][0] = item.getCarID();
            content[i][1] = item.getItemCar().getName();
            content[i][2] = item.getItemCar().getUnitPrice();
            content[i][3] = item.getQuantity();
            content[i][4] = item.getTotalPrice();
            totalPrice += item.getQuantity() * item.getItemCar().getUnitPrice();
        }

        cartTable = new JTable(content, tableHeader);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(600, 350));
        jScrollPane.setViewportView(cartTable);
        carPanel.add(jScrollPane);

        JPanel totalPricePanel = new JPanel();
        totalPricePanel.setBounds(0, 400, 1000, 50);
//        totalPricePanel.setBackground(Color.RED);
        JLabel totalPriceLabel = new JLabel("총금액 : " + totalPrice + "원");
        totalPriceLabel.setForeground(Color.RED);
        totalPriceLabel.setFont(ft);
        totalPricePanel.add(totalPriceLabel);
        add(totalPricePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds(0, 450, 1000, 50);
        add(buttonPanel);

        JLabel buttonLabel = new JLabel("장바구니 비우기");
        buttonLabel.setFont(ft);
        JButton clearButton = new JButton();
        clearButton.add(buttonLabel);
        buttonPanel.add(clearButton);

        clearButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<CartItem> cartItem = cart.getmCartItem();
                if (cart.mCartCount == 0) {
                    JOptionPane.showMessageDialog(clearButton, "장바구니에 항목이 없습니다.");
                } else {
                    int select = JOptionPane.showConfirmDialog(clearButton, "장바구니의 모든 항목을 삭제하겠습니가? ");
                    if (select == 0) {
                        TableModel tableModel = new DefaultTableModel(new Object[0][0], tableHeader);
                        cartTable.setModel(tableModel);
                        totalPriceLabel.setText("총 금액 : " + 0 + " 원");

                        JOptionPane.showMessageDialog(clearButton, "장바구니의 모든 항목을 삭제했습니다.");

                        cart.deleteCar();
                    }
                }
            }
        });

        JLabel removeLabel = new JLabel("장바구니 항목 삭제하기");
        removeLabel.setFont(ft);
        JButton removeButton = new JButton();
        removeButton.add(removeLabel);
        buttonPanel.add(removeButton);

        removeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cart.mCartCount == 0)
                    JOptionPane.showMessageDialog((clearButton), "장바구니에 항목이 없습니다.");
                else if (mSelectRow == -1)
                    JOptionPane.showMessageDialog(clearButton, "장바구니에서 삭제할 항목을 선택하세요.");
                else {
                    ArrayList<CartItem> cartItem = cart.getmCartItem();
                    cart.mCartCount -= 1;
                    Object[][] content = new Object[cartItem.size()][tableHeader.length];
                    Integer totalPrice = 0;
                    for (int i = 0; i < cartItem.size(); i++) {
                        CartItem item = cartItem.get(i);
                        content[i][0] = item.getCarID();
                        content[i][1] = item.getItemCar().getName();
                        content[i][2] = item.getItemCar().getUnitPrice();
                        content[i][3] = item.getQuantity();
                        content[i][4] = item.getTotalPrice();
                        totalPrice += item.getQuantity() * item.getItemCar().getUnitPrice();
                    }
                    TableModel tableModel = new DefaultTableModel(content, tableHeader);
                    totalPriceLabel.setText("총금액 : " + totalPrice + " 원");
                    cartTable.setModel(tableModel);
                    mSelectRow = -1;
                }
            }
        });

        cartTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = cartTable.getSelectedRow();
                mSelectRow = row;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JLabel refreshLabel = new JLabel("장바구니 새로 고침");
        refreshLabel.setFont(ft);
        JButton refreshButton = new JButton();
        refreshButton.add(refreshLabel);
        buttonPanel.add(refreshButton);

        refreshButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<CartItem> cartItem = cart.getmCartItem();
                Object[][] content = new Object[cartItem.size()][tableHeader.length];
                Integer totalPrice = 0;
                for (int i = 0; i < cartItem.size(); i++) {
                    CartItem item = cartItem.get(i);
                    content[i][0] = item.getCarID();
                    content[i][1] = item.getItemCar().getName();
                    content[i][2] = item.getItemCar().getUnitPrice();
                    content[i][3] = item.getQuantity();
                    content[i][4] = item.getTotalPrice();
                    totalPrice += item.getQuantity() * item.getItemCar().getUnitPrice();
                }
                TableModel tableModel = new DefaultTableModel(content, tableHeader);
                totalPriceLabel.setText("총 금액 : " + totalPrice + " 원");
                cartTable.setModel(tableModel);
            }
        });
    }

    public static void main(String[] args) {
        Cart mCart = new Cart();
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 1000, 750);
        frame.setLayout(null);

        JPanel mPagePanel = new JPanel();
        mPagePanel.setBounds(0, 150, 1000, 750);

        frame.add(mPagePanel);
        mPagePanel.add("장바구니의 상품 목록 보기", new CartItemListPage(mPagePanel, mCart));
//        mPagePanel.add("", new CartItemListPage(mPagePanel, mCart));
        frame.setVisible(true);
    }
}
