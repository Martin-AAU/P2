package apr13_opgave4;

import java.io.BufferedReader;
import java.io.Flushable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class cmdLine {

    public static void main(String[] args) {
        Path path = Paths.get(args[0]);

        if(!Files.exists(path)) {
            System.out.println("File does not exist");
        } else if(!Files.isRegularFile(path)) {
            System.out.println("File is not normal");
        } else if(!Files.isReadable(path)) {
            System.out.println("File is not readable");
        } else {
            System.out.println("File opened");
        }

        try {
            int lines = 0;
            String str = null;
            BufferedReader reader = Files.newBufferedReader(path);

            while((str = reader.readLine()) != null) {
                lines++;
                System.out.println(lines + ": " + str);
            }

        } catch (IOException e) {
            System.out.println("Cannot read file " + e.getMessage());
        }
    }
}