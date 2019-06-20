import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Core {
    File songsFile;
    FileWriter fileWriter;

    public Core() {
        songsFile = new File("Songs.txt");
    }

    public void addSong(String directory, ArrayList<Music> musics) {
        try {

            fileWriter = new FileWriter(songsFile);
            fileWriter.write(directory);
            fileWriter.flush();
            fileWriter.close();

            Music newMusic = new Music(directory);
            musics = uptodateList(musics,newMusic);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Music> uptodateList(ArrayList<Music> musics,Music newMusic) {
        musics.add(newMusic);
        return musics;
    }
}
