package page;

import member.Admin;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginDialog extends JDialog {
    public static void main(String[] args) {

        AdminLoginDialog adminLoginDialog;
        JFrame frame = new JFrame();
        adminLoginDialog = new AdminLoginDialog(frame, "");
        adminLoginDialog.setVisible(true);
    }
    JTextField pwField, idField;
    public boolean isLogin = false;

    public AdminLoginDialog(JFrame frame, String str) {
        super(frame, "관리자로그인", true);

        Font ft;
        ft = new Font("함초롱돋움", Font.BOLD, 15);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - 400) / 2, (screenSize.height - 300) / 2);
        setSize(400, 300);
        setLayout(null);

        JPanel titilePanel = new JPanel();
        titilePanel.setBounds(0, 20, 400, 50);
        add(titilePanel);
        JLabel titleLabel = new JLabel("관리자 로그인");
        titleLabel.setFont(new Font("함초롱돋움", Font.BOLD, 20));

        JPanel idPanel = new JPanel();
        idPanel.setBounds(0, 70, 400, 50);
        add(idPanel);
        JLabel idLabel = new JLabel("ID : ");
        idLabel.setFont(ft);
        idField = new JTextField(10);
        idField.setFont(ft);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JPanel pwPanel = new JPanel();
        pwPanel.setBounds(0, 120, 400, 50);
        add(pwPanel);
        JLabel pwLabel = new JLabel("PWD : ");
        pwLabel.setFont(ft);
        pwField = new JTextField(10);
        pwField.setFont(ft);
        pwPanel.add(pwLabel);
        pwPanel.add(pwField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 170, 400, 50);
        add(buttonPanel);
        JLabel okLabel = new JLabel("확인");
        okLabel.setFont(ft);
        JButton okButton = new JButton();
        okButton.add(okLabel);
        buttonPanel.add(okButton);

        JLabel cancelLabel = new JLabel("취소");
        cancelLabel.setFont(ft);
        JButton cancelBtn = new JButton();
        cancelBtn.add(cancelLabel);
        buttonPanel.add(cancelBtn);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin = new Admin("", -1);
                System.out.println(pwField.getText() + idField.getText());
                System.out.println(admin.getId() + admin.getPassword());

                if (admin.getId().equals(idField.getText()) && admin.getPassword().equals(pwField.getText())) {
                    isLogin = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(okButton, "관리자 정보가 일치하지 않습니다");
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLogin = false;
                dispose();
            }
        });
    }
}

