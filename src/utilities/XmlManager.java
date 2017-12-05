package utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * A class to read and write to XML files
 * @author Andy Li
 * @since Dec 04, 2017
 */
public class XmlManager {
	
	public static void writeFile(String rootName, int rootElements,
								 String[] a) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement(rootName);
			
			
			
		} catch (ParserConfigurationException /*| TransformerException*/ e) {
			e.printStackTrace();
		}
	}
}
