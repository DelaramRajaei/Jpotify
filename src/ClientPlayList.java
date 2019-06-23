public class ClientPlayList extends PlayList {
    private String fileName;

    public ClientPlayList(String name) {
        super();
        this.name = name;
        editable = true;
    }

    public void editName(String name) {
        this.name = name;
    }

    public void setFileName(String name) {
        fileName = name;
    }


    public String getFileName() {
        return fileName;
    }
}
