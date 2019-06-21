import java.io.*;
import java.util.ArrayList;

public class Core {
    static File songsFile;
    static FileWriter fileWriter;
    private static final String fileName = "Song.txt";

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

    public static void initialLoad(ArrayList<Music> musics) {
        FileReader input=null;
        BufferedReader reader=null;
        String line;
        try {
            songsFile = new File(fileName);
            if (!songsFile.createNewFile()) {
                input = new FileReader(songsFile);
                reader = new BufferedReader(input);
                while((line=reader.readLine())!= null){
                    Music music=new Music(line);
                    musics.add(music);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
           closeReader(input);
           closeReader(reader);
        }

    }

    private static void closeReader(Reader reader){
        try {
            if (reader!=null)reader.close();
        }catch(Exception e){}
    }

    public static ArrayList<Music> updateList(ArrayList<Music> musics, Music newMusic) {
        musics.add(newMusic);
        return musics;
    }
}