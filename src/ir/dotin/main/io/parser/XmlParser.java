package ir.dotin.main.io.parser;

import ir.dotin.main.logic.Exceptions.IllegalInputException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlParser implements Parser {
    public static String filePath;

    public NodeList parseByTagName(String tagName) throws IllegalInputException {
        NodeList nodeList = null;
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filePath);
            document.getDocumentElement().normalize();
            nodeList = document.getElementsByTagName(tagName);
        } catch (SAXException e) {
            System.err.println("Unexpected input format!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        if (nodeList == null)
            throw new IllegalInputException("Illegal input file!" + "\n" + "The input file must start with <" + tagName + "> and end with </" + tagName + ">.");
        return nodeList;
    }
}
