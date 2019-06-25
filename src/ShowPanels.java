

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowPanels extends JPanel{
    private ArrayList<JPanel>panels;
    private JButton temp;
    private CellShowJpanel c1;
    private CellShowJpanel c2;
    private CellShowJpanel c3;
    private CellShowJpanel c4;
    private CellShowJpanel c5;


    public ShowPanels()  {
        /**
         * give music Arraylist
         *
         */

        //panels=new ArrayList<>();

        this.setBackground(new Color(51,51,100));
        this.setLayout(new GridLayout(0 ,3));
        c1=new CellShowJpanel();
        c2=new CellShowJpanel();
        c3=new CellShowJpanel();
        c4=new CellShowJpanel();
        c5=new CellShowJpanel();
        this.add(c1);
        this.add(c2);
        this.add(c3);
        this.add(c4);
        this.add(c5);





    }


    public void ShowCellsMethod (ArrayList<CellShowJpanel> cells){
        this.removeAll();
        for(CellShowJpanel cell : cells){
            this.add(cell);
        }

    }





    }

