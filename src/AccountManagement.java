import java.util.ArrayList;

public class AccountManagement {

    private static Account activeAccount;
    protected static RootPanel rootPanel;
    protected static MusicBarP musicBarP;
    protected static LeftPanel leftPanel;
    protected static FriendsActivityPanel friendsActivityPanel;
    protected static ShowPanels showPanels;
    protected static ToolBarPanel toolBarPanel;

    public AccountManagement(Account active) {

        activeAccount = active;


        //rootPanel=new RootPanel();
        //rootPanel=new RootPanel( activeAccount, accounts);

    }
    //TODO IP

    public static Account getActiveAccount() {
        return activeAccount;
    }

    public static void setActiveAccount(Account a) {
        activeAccount = a;
    }
}
