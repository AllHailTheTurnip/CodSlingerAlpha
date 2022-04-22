import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.*;
import java.util.Random;

// Saves a .txt with the word "cod" to a random folder in your computer.
public class Main {

    Desktop desktop;
    Random random = new Random();

    Main() {
        desktop = Desktop.getDesktop();
    }

    public boolean FileIsValid(File file) {
        return !file.isHidden() && file.isDirectory();
    }

    public void OpenAndEnter(File target) throws IOException {
        // If the file contains no directories, plant a cod.
        int foundDirectories = 0;
        for (File file : target.listFiles()) {
            if (FileIsValid(file))
                foundDirectories++;

        }
        if (foundDirectories == 0) {
            // Deploy cod and return.
            File codFile = new File(target.getAbsolutePath() + "\\cod.txt");
            FileWriter writer = new FileWriter(codFile);
            writer.write("cod");
            writer.close();
            return;
        }


        File[] foundFiles = target.listFiles();

        ArrayList<File> directories = new ArrayList<File>();

        for (File file : foundFiles)
            if (FileIsValid(file))
                directories.add(file);


        int index = random.nextInt(directories.size());
        File nextFile = directories.get(index);
        OpenAndEnter(nextFile);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        File files[] = File.listRoots();

        main.OpenAndEnter(files[0]);
    }
}