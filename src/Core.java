import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;

public class Core {
    static File songsFile;
    static File playListFile;
    static File accountFile;
    static BufferedWriter fileWriter;
    private static ArrayList<String> file_client_playlists;
    private static final String FILE_PATH_OF_SONGS = "Song.txt";
    private static final String FILE_PATH_OF_PLAYLIST = "Playlist.txt";
    private static final String FILE_PATH_OF_ACCOUNT = "Account.txt";
    private static final String SHARED_PLAYLIST_FILE = "SharedPlayList.txt";
    private static final String FAVORITE_PLAYLIST_FILE = "FavoriteList.txt";

    /**
     * Add a song to list of songs save it on the file and make an object of Music from it.
     *
     * @param newMusic New music that added.
     * @param albums   Array list of albums.
     */
    public static void addSong(Music newMusic, ArrayList<Album> albums) {
        try {
            fileWriter = new BufferedWriter(new FileWriter(songsFile, true));
            fileWriter.write(newMusic.getDirectory());
            fileWriter.newLine();
            fileWriter.flush();
            fileWriter.close();
            initialAlbum(newMusic, albums);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will delete the selected song from file.
     */
    public static void removeSong(ArrayList<PlayList> playlists, ArrayList<Music> musics, ArrayList<Album> albums) throws Exception {
        songsFile.delete();
        for (Music eachMusic : musics) {
            addSong(eachMusic, albums);
        }
        for (PlayList eachPlayList : playlists) {
            File file = new File(eachPlayList.getFileName());
            file.delete();
            file.createNewFile();
            for (Music eachMusic : eachPlayList.getMusic())
                addSongToPlayList(eachPlayList, eachMusic);
        }
    }

    /**
     * This method will remove the file of that playlist.
     *
     * @param playList The selected playlist which the song want to be removed from it.
     */
    public static void removeSongFromPlaylist(PlayList playList) {
        try {
            File file = new File(playList.getFileName());
            file.delete();
            file.createNewFile();
            for (Music eachMusic : playList.getMusic())
                addSongToPlayList(playList, eachMusic);
        } catch (
                Exception e) {
        }
    }

    /**
     * This method will remove the file of that playlist.
     *
     * @param playList The selected playlist which we wanted to remove.
     */
    public static void removePlaylist(PlayList playList) {
        boolean flag = false;
        File file = new File(playList.getFileName());
        file_client_playlists.remove(playList.getFileName());
        if (playList.editable) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * This method will create a new file for the playlist and set the file name of the new playlist.
     *
     * @param newClientPlayList The new playlist
     * @param playLists         Arraylist of all the playlists.
     */
    public static void createPlaylist(ClientPlayList newClientPlayList, ArrayList<PlayList> playLists) {
        try {
            String fileName = "P" + (playLists.size() - 2);
            newClientPlayList.setFileName(fileName);
            file_client_playlists.add(fileName + ".txt");
            playListFile = new File(fileName);
            playListFile.createNewFile();
            fileWriter = new BufferedWriter(new FileWriter(playListFile, true));
            fileWriter.write(fileName);
            fileWriter.newLine();

            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
        }
    }

    public static void addSongToPlayList(PlayList playList, Music music) {
        try {
            for (String eachFileName : file_client_playlists) {
                if (playList.getFileName().equals(eachFileName)) {
                    File clientPlaylistFile = new File(eachFileName);
                    fileWriter = new BufferedWriter(new FileWriter(eachFileName, true));
                    fileWriter.write(music.getName());
                    fileWriter.newLine();
                    fileWriter.flush();
                    fileWriter.close();
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * When you start the program this method will run and if you saved a song before this method will bring
     * it back!
     *
     * @param musics Arraylist of songs that each account has.
     * @param albums Arraylist of albums that each account has.
     */
    public static void initialLoadSongs(ArrayList<Music> musics, ArrayList<Album> albums) {
        FileReader input = null;
        BufferedReader reader = null;
        String line;
        try {
            songsFile = new File(FILE_PATH_OF_SONGS);
            if (!songsFile.createNewFile()) {//If the file exists.
                input = new FileReader(songsFile);
                reader = new BufferedReader(input);
                while ((line = reader.readLine()) != null) {
                    if (line == "") continue;
                    Music music = new Music(line);
                    musics.add(music);
                    initialAlbum(music, albums);
                }
            }
        } catch (Exception e) {
        } finally {
            closeReader(input);
            closeReader(reader);
        }
    }

    public static void initialLoadClientsPlaylist(ArrayList<PlayList> playLists, ArrayList<Music> musics) {
        if (file_client_playlists.size() != 0) {
            for (String fileName : file_client_playlists) {
                FileReader input = null;
                BufferedReader reader = null;
                ClientPlayList clientPlayList;
                String readingPlaylist;
                try {
                    playListFile = new File(fileName);
                    if (!playListFile.createNewFile()) {
                        input = new FileReader(playListFile);
                        reader = new BufferedReader(input);
                        clientPlayList = new ClientPlayList(fileName);
                        if (!playListFile.createNewFile()) {
                            while ((readingPlaylist = reader.readLine()) != null) {
                                loadPlayList(clientPlayList, musics, readingPlaylist);
                            }
                        }
                    }
                } catch (Exception e) {
                } finally {
                    closeReader(input);
                    closeReader(reader);
                }
            }
        }
    }


    public static void initialLoadPlaylistsFileName() {

        FileReader input = null;
        BufferedReader reader = null;
        PlayList playList = null;
        String line;
        file_client_playlists = new ArrayList<String>();
        try {
            File playlists = new File(FILE_PATH_OF_PLAYLIST);
            if (!playlists.createNewFile()) {
                while ((line = reader.readLine()) != null) {
                    file_client_playlists.add(line);
                }
            }


        } catch (Exception e) {
        } finally {
            if (file_client_playlists.size() == 0) {
                file_client_playlists.add("Shared Playlist.txt");
                file_client_playlists.add("Favorite Songs.txt");
            }
            closeReader(input);
            closeReader(reader);
        }
    }



    public static void initialLoad() throws Exception {
        boolean flag = checkAccount();
        if (flag) {
            Account account = loadAccount();
            Main.showPanel();
        } else {
            InetAddress IP = null;
            IP = InetAddress.getLocalHost();
            UserOpenFrame UOF = new UserOpenFrame(IP.getHostAddress());
        }

    }

    private static boolean checkAccount() {
        FileReader input = null;
        boolean flag = false;
        BufferedReader reader = null;
        String line = null;

        try {

            accountFile = new File(FILE_PATH_OF_ACCOUNT);
            accountFile.createNewFile();
            input = new FileReader(accountFile);
            reader = new BufferedReader(input);
            line = reader.readLine();
            if (line != null) {
                flag = true;
            }
        } catch (Exception e) {

        } finally {
            closeReader(input);
            closeReader(reader);
        }
        return flag;
    }


    /**
     * This method will close the Readers you opened earlier.
     *
     * @param reader
     */
    private static void closeReader(Reader reader) {
        try {
            if (reader != null) reader.close();
        } catch (Exception e) {
        }
    }

    /**
     * First this method will check if the music has an album name
     * then if the album already exists it adds the song to that album else it would create a
     * new album.
     *
     * @param music  The music that we added earlier.
     * @param albums Lists of albums.
     */
    public static void initialAlbum(Music music, ArrayList<Album> albums) {
        boolean flag = false;
        Album album = null;
        if (music.getAlbum().length() != 0) {
            for (Album eachAlbum : albums) {
                if (eachAlbum.getAlbumName().equals(music.getAlbum())) {
                    album = eachAlbum;
                    flag = true;
                }
            }
        }
        if (!flag) {
            album = new Album(music);
            albums.add(album);
        }
        flag = false;
        for (Music eachMusic : album.getMusics())
            if (eachMusic == music) flag = true;
        if (!flag)
            album.addSong(music);
    }

    public static void addFriend(ArrayList<Friend> friends) throws Exception {
        File friendFile = new File("Friends.txt");
        friendFile.delete();
        for (Friend eachFriend : friends) {
            friendFile.createNewFile();
            fileWriter = new BufferedWriter(new FileWriter(friendFile, true));
            fileWriter.write(eachFriend.getIP());
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static void initialFriend(ArrayList<Friend> friends) throws Exception {
        File friendFile = new File("Friends.txt");
        FileReader input = null;
        BufferedReader reader = null;
        String line;
        if (!friendFile.createNewFile()) {//If the file exists.
            input = new FileReader(friendFile);
            reader = new BufferedReader(input);
            while ((line = reader.readLine()) != null) {
                if (line == "") continue;
                Friend friend = new Friend();
                friend.setIP(line);
                friends.add(friend);
            }
        }
        closeReader(input);
        closeReader(reader);
    }

    public static Account loadAccount() throws Exception {
        Account account = null;
        FileReader input = null;
        BufferedReader reader = null;
        String line = null;
        UserOpenFrame UOF = null;
        InetAddress IP = null;
        accountFile = new File(FILE_PATH_OF_ACCOUNT);
        accountFile.createNewFile();
        try {
            input = new FileReader(accountFile);
            reader = new BufferedReader(input);
            line = reader.readLine();
            IP = InetAddress.getLocalHost();

            account = new Account(line);
            account.setIP(IP.getHostAddress());
            AccountManagement.setActiveAccount(account);
            ToolBarPanel t =new ToolBarPanel();
            t.setUserName();
            AccountManagement.toolBarPanel=t;
            initialLoadSongs(account.getMusics(), account.getAlbums());
            initialLoadPlaylistsFileName();
            initialLoadClientsPlaylist(account.getPlayLists(), account.getMusics());
            initialFriend(account.getFriends());
            Main.networkInitial();

        } catch (
                Exception e) {
            System.out.println(e);
        } finally {


            //File accountFolder = new File(line);
            //accountFolder.mkdir();
            //accountFolder.createNewFile();
            closeReader(input);
            closeReader(reader);
        }
        return account;
    }

    public static void saveAccount(String name) {
        FileReader input = null;
        BufferedReader reader = null;
        String line;
        File accountFile = new File("Account.txt");
        try {
            input = new FileReader(accountFile);
            reader = new BufferedReader(input);
            line = reader.readLine();
            if (line == null) {
                fileWriter = new BufferedWriter(new FileWriter(accountFile, true));
                fileWriter.write(name);
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
        } finally {
            closeReader(input);
            closeReader(reader);
        }
    }

    public static void updateList(PlayList playList, Music music) {
        playList.addSong(music);
        music.addPlayList(playList);
    }

    public static void loadPlayList(PlayList playList, ArrayList<Music> musics, String readLine) {
        for (Music eachMusic : musics) {
            if (readLine.equals(eachMusic.getName())) {
                updateList(playList, eachMusic);
            }
        }
    }

}
