public class ClientPlayList extends PlayList {


    public ClientPlayList(String name) {
        super();
        this.name = name;
        editable = true;
    }

    public void editName(String name) {
        this.name = name;
    }

    public void setFileName(String name) {
        fileName = name+".txt";
    }

}
