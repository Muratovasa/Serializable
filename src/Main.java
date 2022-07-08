import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    private static List<String> fileArchive = new ArrayList<>();

    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(94, 10, 2, 254);
        savegame("/Users/svetlanapustovit/Games/savegames/progres1.dat", gameProgress1);
        fileArchive.add("/Users/svetlanapustovit/Games/savegames/progres1.dat");

        GameProgress gameProgress2 = new GameProgress(90, 11, 22, 200);
        savegame("/Users/svetlanapustovit/Games/savegames/progres2.dat", gameProgress2);
        fileArchive.add("/Users/svetlanapustovit/Games/savegames/progres2.dat");

        GameProgress gameProgress3 = new GameProgress(50, 5, 55, 255);
        savegame("/Users/svetlanapustovit/Games/savegames/progres3.dat", gameProgress3);
        fileArchive.add("/Users/svetlanapustovit/Games/savegames/progres3.dat");

        zipFiles("/Users/svetlanapustovit/Games/savegames/", fileArchive);
        deleteFile();
    }

    private static void savegame(String file, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(file, true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String archive, List<String> files) {
        try (ZipOutputStream zous = new ZipOutputStream(new FileOutputStream(archive + "savedgames.zip"))) {
            for (String zipFile : files) {
                FileInputStream fis = new FileInputStream(zipFile);
                ZipEntry zipEntry = new ZipEntry(zipFile);
                zous.putNextEntry(zipEntry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zous.write(buffer);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void deleteFile() {
        for (String fileToDelete : fileArchive) {
            File file = new File(fileToDelete);
            file.delete();
        }
    }

}
