import java.io.*;
import java.util.ArrayList;

public class Core {
    static File songsFile;
    static File playListFile;
    static FileWriter fileWriter;
    private static final String FILE_PATH_OF_SONGS = "Song.txt";
    private static final String SHARED_PLAYLIST_FILE = "SharedPlayList.txt";
    private static final String FAVORITE_PLAYLIST_FILE = "FavoriteList.txt";

    /**
     * Add a song to list of songs save it on the file and make an object of Music from it.
     *
     * @param directory Path of the file
     * @param musics    Arraylist of musics
     */
    public static void addSong(String directory, ArrayList<Music> musics, ArrayList<Album> albums) {
        try {

            fileWriter = new FileWriter(songsFile);
            fileWriter.write(directory);
            fileWriter.flush();
            fileWriter.close();

            Music newMusic = new Music(directory);
            musics.add(newMusic);
            updateAlbum(newMusic, albums);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPlaylist(ClientPlayList newClientPlayList, ArrayList<PlayList> playLists) {
        try {
            playListFile=
            newClientPlayList.setFileName("P" + (playLists.size() - 2));
            fileWriter = new FileWriter(playListFile);
            fileWriter.write(newClientPlayList.getName() + " : " + newClientPlayList.getFileName());
            fileWriter.flush();
            fileWriter.close();

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
                    Music music = new Music(line);
                    musics.add(music);
                    updateAlbum(music, albums);
                }
            }
        } catch (Exception e) {
        } finally {
            closeReader(input);
            closeReader(reader);
        }
    }

    public static void initialLoadClientsPlaylist(ArrayList<PlayList> playLists) {
        FileReader input = null;
        BufferedReader reader = null;
        ClientPlayList clientPlayList;
        String readingPlaylist;
        try {
            playListFile = new File(PLAY_LIST_TXT);
            input = new FileReader(playListFile);
            reader = new BufferedReader(input);
            if (!playListFile.createNewFile()) {
                while ((readingPlaylist = reader.readLine()) != null) {
                    clientPlayList = new ClientPlayList(readingPlaylist.split(":")[0]);
                    clientPlayList.setFileName(readingPlaylist.split(":")[1]);
                    playLists.add(clientPlayList);
                }
            }
        } catch (Exception e) {
        } finally {
            closeReader(input);
            closeReader(reader);
        }
    }

    public static void loadPlayList(PlayList playList, ArrayList<Music> musics, String readLine) {
        for (Music eachMusic : musics) {
            if (readLine.equals(eachMusic.getName())) {
                updateList(playList, eachMusic);
            }
        }
    }


    public static void initialLoadAPlaylist(ArrayList<PlayList> playLists, ArrayList<Music> musics, String fileName) {

        FileReader input = null;
        BufferedReader reader = null;
        PlayList playList=null;
        String readingPlaylist;
        try {
            playListFile = new File(fileName);
            input = new FileReader(playListFile);
            reader = new BufferedReader(input);

            if (fileName.equals("SharedPlayList.txt"))
                playList = new SharedPlayList();
            else if (fileName.equals("FavoriteList.txt")) {
                playList = new FavoriteSongs();
            }

            playLists.add(playList);
            if (!playListFile.createNewFile()) {
                while ((readingPlaylist = reader.readLine()) != null) {
                    loadPlayList(playList, musics, readingPlaylist);
                }
            }


        } catch (Exception e) {
        } finally {
            closeReader(input);
            closeReader(reader);
        }
    }

    public static void intialLoadAllPlaylists(ArrayList<PlayList> playLists, ArrayList<Album> albums, ArrayList<Music> musics) {
        initialLoadSongs(musics, albums);
        initialLoadAPlaylist(playLists, musics, SHARED_PLAYLIST_FILE);
        initialLoadAPlaylist(playLists, musics, FAVORITE_PLAYLIST_FILE);
        initialLoadClientsPlaylist(playLists);

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
    public static void updateAlbum(Music music, ArrayList<Album> albums) {
        boolean flag = false;
        Album album = null;
        if (music.getAlbum() != null) {
            while (!flag) {
                for (Album eachAlbum : albums) {
                    if (eachAlbum.getAlbumName().equals(music.getAlbum())) {
                        album = eachAlbum;
                        flag = true;
                    }
                }
            }
            if (!flag) {
                album = new Album(music);
            }
            album.addSong(music);
        }
    }

    public static void updateList(PlayList playList, Music music) {
        playList.addSong(music);
        music.addPlayList(playList);
    }
}