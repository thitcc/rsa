package treatment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FilesHandling {

    public static boolean readFile(String pathname, StringBuilder content) {

        try {
            File fr = new File(pathname);
            Scanner reader = new Scanner(fr);

            if (fr.length() == 0) throw new FileNotFoundException("Empty file");

            while (reader.hasNextLine()) {
                content.append(reader.nextLine());
            }
            reader.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        return false;
    }

    public static boolean writeFile(String filename, String content) {

        try {
            FileWriter fw = new FileWriter(filename);
            fw.write(content);
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        return false;
    }

}
