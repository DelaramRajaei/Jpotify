import java.io.*;
import java.util.ArrayList;

public class Core {
    static File songsFile;
    static File playListFile;
    static FileWriter fileWriter;
    private static final String filePathOfSongs = "Song.txt";
    private static final String filePathOfPlayLists = "PlayList.txt";

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
            musics = updateList(musics, newMusic);
            updateAlbum(newMusic, albums);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPlaylist(ClientPlayList newClientPlayList, ArrayList<PlayList> playLists) {
        try {
            newClientPlayList.setFileName("P" + playLists.size());
            fileWriter = new FileWriter(playListFile);
            fileWriter.write(newClientPlayList.getName() + " : " + newClientPlayList.getFileName());
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) { }
    }

    /**
     * When you start the program this method will run and if you saved a song before this method will bring
     * it back!
     *
     * @param musics    Arraylist of songs that each account has.
     * @param playLists Arraylist of play lists that each account has.
     */
    public static void initialLoad(ArrayList<Music> musics, ArrayList<PlayList> playLists, ArrayList<Album> albums) {
        FileReader input = null;
        BufferedReader reader = null;
        String line;
        try {
            songsFile = new File(filePathOfSongs);
            playListFile = new File(filePathOfPlayLists);
            if (!songsFile.createNewFile()) {
                input = new FileReader(songsFile);
                reader = new BufferedReader(input);
                while ((line = reader.readLine()) != null) {
                    Music music = new Music(line);
                    updateList(musics, music);
                    updateAlbum(music, albums);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            closeReader(input);
            closeReader(reader);
        }
        //TODO initial playlist
    }

    /**
     * This method will close the Readers you opened earlier
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
     * This will add musics to your Arraylist of songs
     * With this method you will update your list.
     *
     * @param musics   Your Arraylist of songs.
     * @param newMusic New musics you wanted to add.
     * @return
     */
    public static ArrayList<Music> updateList(ArrayList<Music> musics, Music newMusic) {
        musics.add(newMusic);
        return musics;
    }

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
}