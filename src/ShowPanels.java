

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowPanels extends JPanel{

    private ArrayList<CellShowJpanel>panels;
    private JButton temp;
    private CellShowJpanel c1;
    private CellShowJpanel c2;
    private CellShowJpanel c3;
    private CellShowJpanel c4;
    private CellShowJpanel c5;
    private CellShowJpanel c6;
    private CellShowJpanel c7;
    private CellShowJpanel c8;
    private CellShowJpanel c9;
    private CellShowJpanel c0;
    private CellShowJpanel c11;
    private CellShowJpanel c22;
    private CellShowJpanel c33;
    private CellShowJpanel c44;
    private CellShowJpanel c55;





    public ShowPanels()  {


        //panels=new ArrayList<>();
        panels=new ArrayList<CellShowJpanel>();


        this.setBackground(new Color(51,51,100));
        this.setLayout(new GridLayout(0,0));
        c1=new CellShowJpanel();
        c2=new CellShowJpanel();
        c3=new CellShowJpanel();
        c4=new CellShowJpanel();
        c5=new CellShowJpanel();
        c6=new CellShowJpanel();
        c7=new CellShowJpanel();
        c8=new CellShowJpanel();
        c9=new CellShowJpanel();
        c0=new CellShowJpanel();
        c11=new CellShowJpanel();
        c22=new CellShowJpanel();
        c33=new CellShowJpanel();
        c44=new CellShowJpanel();
        c55=new CellShowJpanel();

        panels.add(c1);
        panels.add(c2);
        panels.add(c3);
        panels.add(c4);
        panels.add(c5);
        panels.add(c6);
        panels.add(c7);
        panels.add(c8);
        panels.add(c0);
        panels.add(c11);
        panels.add(c22);
        panels.add(c33);
        panels.add(c44);
        panels.add(c55);
        showCellsMethod(panels);

        //JScrollPane scrollPane = new JScrollPane(this);
        //scrollPane.add(this
        // this.add(scrollPane);
        //this.getAutoscrolls();
        //this.setVisible(true);





    }


    public void showCellsMethod (ArrayList<CellShowJpanel> cells){
        this.removeAll();
        for(CellShowJpanel cell : cells){
            this.add(cell);
        }

    }





    }

