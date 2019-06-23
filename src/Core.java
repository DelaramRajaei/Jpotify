import java.io.*;
import java.util.ArrayList;

public class Core {
    static File songsFile;
    static FileWriter fileWriter;
    private static final String fileNameOfSongs = "Song.txt";
    private static final String fileNameOfPlayLists = "PlayList.txt";

    /**
     * Add a song to list of songs save it on the file and make an object of Music from it.
     *
     * @param directory Path of the file
     * @param musics    Arraylist of musics
     */
    public static void addSong(String directory, ArrayList<Music> musics) {
        try {

            fileWriter = new FileWriter(songsFile);
            fileWriter.write(directory);
            fileWriter.flush();
            fileWriter.close();

            Music newMusic = new Music(directory);
            musics = updateList(musics, newMusic);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPlaylist(ArrayList<PlayList> playLists) {
        try {
            fileWriter = new FileWriter(fileNameOfPlayLists);
            //fileWriter.write();
        } catch (Exception e) {
        }
    }

    /**
     * When you start the program this methode will run and if you saved a song before this methode will bring
     * it back!
     *
     * @param musics    Arraylist of songs that each account has.
     * @param playLists Arraylist of Playlists that each account has.
     */
    public static void initialLoad(ArrayList<Music> musics, ArrayList<PlayList> playLists) {
        FileReader input = null;
        BufferedReader reader = null;
        String line;
        try {
            songsFile = new File(fileNameOfSongs);
            if (!songsFile.createNewFile()) {
                input = new FileReader(songsFile);
                reader = new BufferedReader(input);
                while ((line = reader.readLine()) != null) {
                    Music music = new Music(line);
                    updateList(musics, music);
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
     * @param newMusic New music you wanted to add.
     * @return
     */
    public static ArrayList<Music> updateList(ArrayList<Music> musics, Music newMusic) {
        musics.add(newMusic);
        return musics;
    }
    public void updateAlbum(Music music,ArrayList<Album> albums){
        boolean flag=false;
        if (albums!=null)
            while (!flag) {
                for (Album eachAlbum : albums) {
                    if (eachAlbum.getAlbumName().equals(music.getAlbum())) {
                        eachAlbum.addSong(music);
                        flag=true;
                    }
                }
            }
    }
}