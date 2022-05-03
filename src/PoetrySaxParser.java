import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class PoetrySaxParser {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    String fileName = "";
    String poemLines = "";

    public void getName(String path) throws Exception {
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler nameHandler = new DefaultHandler() {
            boolean nameField = false;

            @Override
            public void startElement(String uri,
                                     String localName,
                                     String qName,
                                     Attributes attributes) {
                if (qName.equalsIgnoreCase("firstname")) {
                    nameField = true;
                }
            }
            @Override
            public void characters(char[] ch, int start, int length) {
                if (nameField) {
                    fileName = new String(ch, start, length);
                }
                nameField = false;
            }
        };

        saxParser.parse(path, nameHandler);
    }

    public void getSecondName(String path) throws Exception {
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler nameHandler = new DefaultHandler() {
            boolean nameField = false;

            @Override
            public void startElement(String uri,
                                     String localName,
                                     String qName,
                                     Attributes attributes) {
                if (qName.equalsIgnoreCase("lastname")) {
                    nameField = true;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                if (nameField) {
                    fileName = String.join("_", fileName, new String(ch, start, length));
                }
                nameField = false;
            }
        };
        saxParser.parse(path, nameHandler);
    }

    public String getFileName(String path) throws Exception {
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler nameHandler = new DefaultHandler() {
            boolean nameField = false;

            @Override
            public void startElement(String uri,
                                     String localName,
                                     String qName,
                                     Attributes attributes) {
                if (qName.equalsIgnoreCase("title")) {
                    nameField = true;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                if (nameField) {
                    fileName = String.join("_", fileName, new String(ch, start, length));
                }
                nameField = false;
            }
        };
        saxParser.parse(path, nameHandler);
        return fileName+".txt";
    }

    public String saxParse(String path) throws Exception {
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new DefaultHandler() {
            boolean field = false;

            @Override
            public void startElement(String uri,
                                     String localName,
                                     String qName,
                                     Attributes attributes) throws SAXException {
                if (qName.equalsIgnoreCase("line")) {
                    field = true;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                if (field) {
                    poemLines = poemLines.concat(new String(ch, start, length)+"\n");
                }
                field = false;
            }
        };
        saxParser.parse(path, handler);
        return poemLines;
    }

}
