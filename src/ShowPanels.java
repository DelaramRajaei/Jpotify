

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowPanels extends JPanel{
    private ArrayList<JPanel>panels;
    private JButton temp;


    public ShowPanels() {

        //panels=new ArrayList<>();
        temp = new JButton("emptySh");
        this.add(temp);
        this.setBackground(new Color(51,51,51));


    }



    public void setPanels(ArrayList<JPanel> panels) {
        this.panels = panels;
    }
    public void show(){
        int x=250;
        int y=50;
        int n=200;
        int cnt=0;
        for (JPanel p : panels){
            p.setVisible(true);
            if(cnt==3){x=250;y+=n;}
            p.setBounds(x,y,200,200);
            x+=n;
            cnt++;


        }




    }
}
