/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Darya
 */
public class CellShowJpanel extends JPanel {

    private JPanel imagePanel;
    private JPanel detailPanel;
    private JPanel buttonsPanel;

    private JLabel image;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;

    private JButton playButton;
    private JPopupMenu popupMenu;

    private ImageIcon cover = new ImageIcon(this.getClass().getResource("images/cover.png"));


    public CellShowJpanel() {

        image=new JLabel(cover);
        imagePanel=new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(image,BorderLayout.CENTER);
        imagePanel.setBackground(new Color(70,50,100));

        l1=new JLabel("Smooth Criminal");
        l2=new JLabel("2CELLO");
        l3=new JLabel("non Album");
        detailPanel=new JPanel();
        detailPanel.setLayout(new GridLayout(3,1));
        detailPanel.add(l1);
        detailPanel.add(l2);
        detailPanel.add(l3);

        playButton=new JButton("Play");
        playButton.setToolTipText("play");
        popupMenu= new JPopupMenu();
        popupMenu.setToolTipText("add to");
        //popupMenu.addMenuKeyListener();
        buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2));
        buttonsPanel.setMaximumSize(new Dimension(100,20));
        buttonsPanel.add(playButton);
        buttonsPanel.add(popupMenu);

        this.setLayout(new BorderLayout());
        add(image,BorderLayout.NORTH);
        add(detailPanel,BorderLayout.CENTER);
        add(buttonsPanel,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setSize(100,200);
        //this.setBackground(new Color(10,100,100));








    }


}
