
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String path = "D:\\JAVA\\tasks\\Lesson19ex1\\src\\homework.xml";
        PoetrySaxParser saxParser = new PoetrySaxParser();
        PoetryDomParser domParser = new PoetryDomParser();
        String fileName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 or 2");
        String choice = scanner.next();

        switch (choice) {
            case "1":
                try {
                    fileName = domParser.parseName(path);
                    PrintWriter pw = new PrintWriter(fileName);
                    pw.write(domParser.parseDom(path));
                    pw.close();
                } catch (IOException | ParserConfigurationException | SAXException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "2":
                try {
                    saxParser.getName(path);
                    saxParser.getSecondName(path);
                    fileName = saxParser.getFileName(path);

                    File file = new File(fileName);
                    PrintWriter pw = new PrintWriter(file);
                    pw.write(saxParser.saxParse(path));
                    pw.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                System.out.println("1 or 2");
                break;
        }


    }
}