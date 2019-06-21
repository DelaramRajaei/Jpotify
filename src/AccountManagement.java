import java.io.PrintWriter;
import java.util.ArrayList;

public class AccountManagement {

    private ArrayList<Account>accounts;
    private Account activeAccount;
    private RootPanel rootPanel;

    public AccountManagement(Account a){
        activeAccount=new Account(a.getName());
        activeAccount=a;
        rootPanel=new RootPanel( activeAccount, accounts);

    }
}
