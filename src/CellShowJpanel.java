/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Darya
 */
public class CellShowJpanel extends JPanel implements ActionListener {

    private JPanel imagePanel;
    private JPanel detailPanel;
    private JPanel buttonsPanel;

    private JLabel image;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;

    private JButton playButton;
    private JPopupMenu popupMenu;

    public CellShowJpanel() {



        ImageIcon c=new ImageIcon(this.getClass().getResource("images/cover.png"));
        ImageIcon i=new ImageIcon(c.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        image=new JLabel();
        imagePanel=new JPanel();
        //image.setPreferredSize(new Dimension(150,150));
        //imagePanel.setPreferredSize(new Dimension(150,150));
        image.setIcon(i);


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
        imagePanel.setMaximumSize(new Dimension(150,150));
        add(imagePanel,BorderLayout.NORTH);
        detailPanel.setMaximumSize(new Dimension(150,200));
        add(detailPanel,BorderLayout.CENTER);
        buttonsPanel.setMaximumSize(new Dimension(150,50));
        add(buttonsPanel,BorderLayout.SOUTH);
        this.setVisible(true);
        //this.setSize(150,400);
        this.setBackground(new Color(0,0,0));



        playButton.addActionListener(this);


        //this.setPreferredSize(new Dimension(100,100));
        //this.setMinimumSize(new Dimension(100,100));
        //this.setMaximumSize(new Dimension(100,100));
//        this.setMinimumSize(new Dimension(150,400));
this.getAutoscrolls();

    }

    public  void displayMusic(Music song){
        ImageIcon c=song.getImage() ;
        ImageIcon i=new ImageIcon(c.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        image.setPreferredSize(new Dimension(150,150));
        image.setIcon(i);

        //cover.setPreferredSize(new Dimension(200,170));
        //musicImage.setIcon(i);


        //ImageIcon cover=song.getImage();
        //ImageIcon i=new ImageIcon(cover.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        image.setIcon(i);
        l1.setText(song.getName());
        l2.setText("Artist : "+song.getArtist());
        l3.setText("Album : "+song.getAlbum());



    }




    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =e.getSource();
        if(source==playButton){

        }
    }
}
