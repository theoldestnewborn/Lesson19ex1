
import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        final String path = "D:\\JAVA\\tasks\\Lesson19ex1\\src\\homework.xml";
        PoetrySaxParser saxParser = new PoetrySaxParser();
        String fileName;

        try {
            saxParser.getName(path);
            saxParser.getSecondName(path);
            fileName = saxParser.getFileName(path);

            File file = new File(fileName);
            PrintWriter pw = new PrintWriter(file);
            String poem = saxParser.saxParse(path);
            pw.print(poem); //почему нельзя напрямую передать saxParser.saxParse(path)?

            pw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}