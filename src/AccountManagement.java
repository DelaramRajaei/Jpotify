import java.util.ArrayList;

public class AccountManagement {

    private static ArrayList<Account>accounts;
    private static Account activeAccount;
    private RootPanel rootPanel;

    public AccountManagement(Account active){


        activeAccount=active;

        //rootPanel=new RootPanel();
        //rootPanel=new RootPanel( activeAccount, accounts);

    }
    //TODO IP


    public static void addAcount(Account a){
        accounts.add(a);

    }
    public static ArrayList<Account> getAccounts(){
        return accounts;
    }

    public static Account getActiveAccount() {
        return activeAccount;
    }

}
