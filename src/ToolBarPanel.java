import javax.swing.*;
import java.awt.*;

public class ToolBarPanel extends JPanel {
    private JLabel user;
    private JButton jButton1;
    private JTextField searchText;

    public ToolBarPanel() {
        user = new javax.swing.JLabel("DElaram");
        jButton1 = new javax.swing.JButton();
        searchText = new javax.swing.JTextField("Delaram");

        setBackground(new java.awt.Color(25, 16, 17));

        user.setBackground(new java.awt.Color(0, 0, 102));
        user.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        user.setForeground(new java.awt.Color(153, 153, 255));
        user.setText(AccountManagement.getActiveAccount().getName() + "/  IP  :" + AccountManagement.getActiveAccount().getIP());

        jButton1.setBackground(new java.awt.Color(25, 16, 17));
        jButton1.setForeground(new java.awt.Color(204, 204, 255));
        jButton1.setText("search");

        searchText.setBackground(new java.awt.Color(25, 16, 17));
        searchText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchText.setForeground(new java.awt.Color(153, 0, 255));
        searchText.setToolTipText("search");
        searchText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 4, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        this.add(user);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(user))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
    }

    public void setUser(String n) {
        this.user.setText(n);
    }


    public void setUserName() {
        setUser(AccountManagement.getActiveAccount().getName());

//        System.out.println("setUser");
//        this.setBackground(new Color(200,200,200));
//        this.user.setText("dell");
//
//
//
//        this.user.setText(AccountManagement.getActiveAccount().getName());
//        user.setVisible(false);
//        System.out.println("SSSSSSSSSSSSSS");

    }
}