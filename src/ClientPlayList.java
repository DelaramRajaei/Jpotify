public class ClientPlayList extends PlayList {

    public ClientPlayList(String name) {
        this.name = name;
        editable = true;
    }

    public void editName(String name) {
        this.name = name;
    }

}
