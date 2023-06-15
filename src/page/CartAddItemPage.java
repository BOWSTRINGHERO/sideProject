package page;

import cart.Cart;
import item.Car;
import item.CarInit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CartAddItemPage extends JPanel {

    ImageIcon imageCar;
    int mSelectRow = 0;
    Cart mCart;

    public CartAddItemPage(JPanel panel, Cart cart) {
        Font ft;
        ft = new Font("함초롱돋움", Font.BOLD, 15);

        setLayout(null);

        Rectangle rect = panel.getBounds();
        System.out.println(rect);
        setPreferredSize(rect.getSize());

        mCart = cart;

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(20, 0, 300, 400);
        imageCar = new ImageIcon("images/11.png");
        imageCar = new ImageIcon("images/12.png");
        imageCar = new ImageIcon("images/13.png");
        imageCar = new ImageIcon("images/14.png");

        imageCar.setImage(imageCar.getImage().getScaledInstance(250, 300, Image.SCALE_DEFAULT));
        JLabel label = new JLabel(imageCar);
        imagePanel.add(label);
        add(imagePanel);

        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(300, 0, 700, 400);
        add(tablePanel);

        ArrayList<Car> carlist = CarInit.getmCarList();
        Object[] tableHeader = {"제품 ID", "제품명", "가격", "생산자", "제품설명", "분류", "출시일"};
        Object[][] content = new Object[carlist.size()][tableHeader.length];

        for (int i = 0; i < carlist.size(); i++) {
            Car caritem = carlist.get(i);
            content[i][0] = caritem.getCarId();
            content[i][1] = caritem.getName();
            content[i][2] = caritem.getUnitPrice();
            content[i][3] = caritem.getProducer();
            content[i][4] = caritem.getDescription();
            content[i][5] = caritem.getCategory();
            content[i][6] = caritem.getReleaseDate();
        }

        JTable carTable = new JTable(content, tableHeader);
        carTable.setRowSelectionInterval(0, 0);
        carTable.getSelectedColumn();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(600, 350));
        jScrollPane.setViewportView(carTable);
        tablePanel.add(jScrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 400, 1000, 400);
        add(buttonPanel);
        JLabel buttonLabel = new JLabel("장바구니 담기");
        buttonLabel.setFont(ft);
        JButton addButton = new JButton();
        addButton.add(buttonLabel);
        buttonPanel.add(addButton);

        carTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = carTable.getSelectedRow();
                int col = carTable.getSelectedColumn();
                mSelectRow = row;
                Object value = carTable.getValueAt(row, 0);
                String str = value + ".jpg";

                imageCar = new ImageIcon("images/" + str);
                imageCar.setImage(imageCar.getImage().getScaledInstance(250, 300, Image.SCALE_DEFAULT));
                JLabel label = new JLabel(imageCar);
                imagePanel.removeAll();
                imagePanel.add(label);
                imagePanel.revalidate();
                imagePanel.repaint();
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Car> carlist = CarInit.getmCarList();
                int select = JOptionPane.showConfirmDialog(addButton, "장바구니에 추가하겠습니까?");
                if (select == 0) {
                    int numId = mSelectRow;
                    if (!isCartInCar(carlist.get(numId).getCarId())) {
                        mCart.insertCar(carlist.get(numId));
                    }
                    JOptionPane.showMessageDialog(addButton, "추가했습니다.");
                }
            }
        });
    }

    public boolean isCartInCar(String carId) {
        return mCart.isCartInCar(carId);
    }
}
