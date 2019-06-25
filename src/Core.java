import java.io.*;
import java.util.ArrayList;

public class Core {
    static File songsFile;
    static File playListFile;
    static BufferedWriter fileWriter;
    private static ArrayList<String> file_client_playlists;
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
            fileWriter = new BufferedWriter(new FileWriter(songsFile, true));
            fileWriter.write(directory + "\n");
            fileWriter.newLine();
            fileWriter.flush();
            fileWriter.close();

            Music newMusic = new Music(directory);
            musics.add(newMusic);
            initialAlbum(newMusic, albums);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will delete the selected song from file.
     *
     * @param music The music that we wanted to delete.
     */
    public static void removeSong(Music music) {
        removeLineFromFile(FILE_PATH_OF_SONGS, music.getDirectory());
        if (music.getPlaylist() != null) {
            for (PlayList eachPlayList : music.getPlaylist()) {
                removeSongFromPlaylist(eachPlayList, music);
            }
        }
    }//TODO check files and test remove

    //TODO Name account
    //TODO search
    public static void removeLineFromFile(String file, String lineToRemove) {

        try {

            File inFile = new File(file);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveName(Account account) {
        try {
            File myAccount = new File(account.getName());
            File f2 = new File("temp.txt");
            myAccount.mkdir();
            f2.createNewFile();
            System.out.println("Printed successfully!");

        } catch (Exception e) {
        }
    }

    /**
     * This method will remove the file of that playlist.
     *
     * @param playList The selected playlist which the song want to be removed from it.
     * @param music    The selected music
     */
    public static void removeSongFromPlaylist(PlayList playList, Music music) {
        try {
            removeLineFromFile(playList.getFileName(), music.getName());
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
        File playlistFile = new File(playList.getFileName());
        if (playlistFile.exists()) {
            playListFile.delete();
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
            file_client_playlists.add(fileName);
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
        file_client_playlists = new ArrayList<String>();
        if (file_client_playlists.size() != 0) {
            for (String fileName : file_client_playlists) {
                FileReader input = null;
                BufferedReader reader = null;
                ClientPlayList clientPlayList;
                String readingPlaylist;
                try {
                    playListFile = new File(fileName.concat(".txt"));
                    input = new FileReader(playListFile);
                    reader = new BufferedReader(input);
                    clientPlayList = new ClientPlayList(fileName);
                    if (!playListFile.createNewFile()) {
                        while ((readingPlaylist = reader.readLine()) != null) {
                            loadPlayList(clientPlayList, musics, readingPlaylist);
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


    public static void initialLoadPlaylist(ArrayList<PlayList> playLists, ArrayList<Music> musics, String fileName) {

        FileReader input = null;
        BufferedReader reader = null;
        PlayList playList = null;
        String readingPlaylist;
        try {
            playListFile = new File(fileName);
            input = new FileReader(playListFile);
            reader = new BufferedReader(input);

            if (fileName.equals(SHARED_PLAYLIST_FILE))
                playList = new SharedPlayList();
            else if (fileName.equals(FAVORITE_PLAYLIST_FILE)) {
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

    public static void initialLoad(ArrayList<PlayList> playLists, ArrayList<Album> albums, ArrayList<Music> musics) {
        initialLoadSongs(musics, albums);
        initialLoadPlaylist(playLists, musics, SHARED_PLAYLIST_FILE);
        initialLoadPlaylist(playLists, musics, FAVORITE_PLAYLIST_FILE);
        initialLoadClientsPlaylist(playLists, musics);

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
        if (music.getAlbum().isEmpty()) {
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
