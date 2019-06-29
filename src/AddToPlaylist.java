import javax.jws.WebParam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AddToPlaylist extends JFrame implements ActionListener {

    private JList<PlayList> playlists;
    private JScrollPane scrolPlaylist;
    private JButton createAndAdd;
    private JLabel nnew;


    private Music song;
    private DefaultListModel<PlayList> model = new DefaultListModel<>();
    protected boolean isFinish=false;
    public AddToPlaylist(Music songa){
        song=songa;
        scrolPlaylist=new JScrollPane();


        createAndAdd=new JButton("Create and Add");
        nnew=new JLabel("new playlist");
        playlists=new JList<>();
        playlists.setPreferredSize(new Dimension(200,200));
        scrolPlaylist.setViewportView(playlists);
        setJlistPlayList();
        JPanel jp=new JPanel();

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.add(scrolPlaylist);
        jp.add(nnew);
        jp.add(createAndAdd);
        this.add(jp);
        this.setVisible(true);
        pack();
        createAndAdd.addActionListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }

    protected void setJlistPlayList(){



        for(PlayList p:AccountManagement.getActiveAccount().getPlayLists()){
            model.addElement(p);
        }
        playlists.setModel(model);





        playlists.getSelectionModel().addListSelectionListener(e -> {
            playlists.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    JList list = (JList)evt.getSource();
                    if (evt.getClickCount() == 1) {

                        System.out.println("darya action");
                        PlayList p = playlists.getSelectedValue();
                        p.addSong(song);
                        isFinish=true;


                        int index = list.locationToIndex(evt.getPoint());
                    }
                }
            });

            this.setVisible(false);

        });

        playlists.setModel(model);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createAndAdd) {
            System.out.println("mirese inja????");
            String n = JOptionPane.showInputDialog("playlist name : ");
            AccountManagement.getActiveAccount().createPlayList(n);
            int x = AccountManagement.getActiveAccount().getPlayLists().size();
            System.out.println("song numbers was : " + AccountManagement.getActiveAccount().getPlayLists().size());
            AccountManagement.getActiveAccount().getPlayLists().get(x - 1).addSong(song);
            System.out.println("song numbers is : " + AccountManagement.getActiveAccount().getPlayLists().size());
            isFinish = true;
            AccountManagement.leftPanel.model.removeAllElements();
            for(PlayList p : AccountManagement.getActiveAccount().getPlayLists()){
                AccountManagement.leftPanel.model.addElement(p);
            }
            AccountManagement.leftPanel.jplaylist.setModel(AccountManagement.leftPanel.model);



            this.setVisible(false);




        }

    }
}
