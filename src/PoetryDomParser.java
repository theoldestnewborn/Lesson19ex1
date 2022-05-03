import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class PoetryDomParser {
    String poemLines;
    String fileName = "";

    public String parseName (String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilderName = factory.newDocumentBuilder();
        File file = new File(path);
        Document doc = dBuilderName.parse(file);
        NodeList nodeListName = doc.getElementsByTagName("firstName");

        for (int i=0; i<nodeListName.getLength(); i++) {
            Node node = nodeListName.item(i);
            fileName = String.join(fileName, node.getTextContent());
        }
        NodeList nodeListSurname = doc.getElementsByTagName("lastName");
        for (int j = 0; j < nodeListSurname.getLength(); j++) {
            Node node = nodeListSurname.item(j);
            fileName = fileName.join("_", fileName, node.getTextContent());
        }
        NodeList nodeTitle = doc.getElementsByTagName("title");
        for (int j=0; j<nodeTitle.getLength(); j++){
            Node node = nodeTitle.item(j);
            fileName = fileName.join("_", fileName, node.getTextContent());
        }
    return fileName;
    }

    public String parseDom (String path) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        File file = new File(path);
        Document doc = dBuilder.parse(file);
        NodeList nodeList = doc.getElementsByTagName("lines");

        for (int i = 0; i< nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            poemLines = node.getTextContent();
        }
        return poemLines;
    }

}
