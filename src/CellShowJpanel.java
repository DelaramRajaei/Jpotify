/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Darya
 */
public class CellShowJpanel extends JPanel  {

    protected JPanel imagePanel;
    protected JPanel detailPanel;
    protected JPanel buttonsPanel;

    protected JLabel image;
    protected JLabel l1;
    protected JLabel l2;
    protected JLabel l3;

    //protected JButton playButton;
    protected JMenu more;

    public CellShowJpanel() {
        buttonsPanel=new JPanel();

       // Music song=AccountManagement.getActiveAccount().getMusics().get(0);
        //ImageIcon c=new ImageIcon(song.getImage().getImage());
       // ImageIcon i=new ImageIcon(c.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT));
        image=new JLabel();
        imagePanel=new JPanel();
       // image.setPreferredSize(new Dimension(150,150));
       // imagePanel.setPreferredSize(new Dimension(150,150));
       // image.setIcon(i);


        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(image,BorderLayout.CENTER);
        imagePanel.setBackground(new Color(0,0,0));

        //l1=new JLabel(song.getName());
       // l2=new JLabel(song.getArtist());
       // l3=new JLabel(song.getAlbum());
        l1=new JLabel();
        l2=new JLabel();
        l3=new JLabel();
        detailPanel=new JPanel();
        detailPanel.setLayout(new GridLayout(3,1));
        detailPanel.add(l1);
        detailPanel.add(l2);
        detailPanel.add(l3);

       // playButton=new JButton("Play");
       // playButton.setToolTipText("play");
       // buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setMaximumSize(new Dimension(100,20));
        //buttonsPanel.add(playButton);

        this.setLayout(new BorderLayout());
        imagePanel.setMaximumSize(new Dimension(200,200));
        add(imagePanel,BorderLayout.NORTH);
        detailPanel.setMaximumSize(new Dimension(200,300));
        add(detailPanel,BorderLayout.CENTER);
        buttonsPanel.setMaximumSize(new Dimension(200,50));
        add(buttonsPanel,BorderLayout.SOUTH);
        this.setVisible(true);
        //this.setSize(150,400);
        this.setBackground(new Color(0,0,0));



//        playButton.addActionListener(this);
        this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 4, true));


    }




//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object source =e.getSource();
//        if(source==playButton){
//
//        }
//    }
}
