import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public static String loadTextFile(String fileName) throws IOException {
        return loadFile("src\\files\\" + fileName);
    }

    public static String loadFile(String pathInDirectory) throws IOException {
        pathInDirectory = System.getProperty("user.dir") + "\\" +pathInDirectory;

        BufferedReader reader = new BufferedReader(new FileReader(pathInDirectory));

        String out = "";
        String nextLine = reader.readLine();
        while(nextLine != null){
            if(nextLine != ""){out += nextLine;}
            nextLine = reader.readLine();
            if(nextLine!=null){out += "\n";}
        }

        return out;
    }

}
