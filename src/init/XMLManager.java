
package init;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLManager {
    
    private static Document doc = null;
    private static Element rootElement = null;
    private static String fileName;
    private static HashMap<String, ArrayList<java.util.Date>> occupied;
        
    public XMLManager(String fileName) {
        this.fileName = fileName;
    }
  public void importXML() {
        try {
            System.out.println("//===========================================================");
            System.out.println("\tTrying to load XML...");
            File file = new File("sources/"+fileName+".xml");
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            System.out.println("\tXML file was loaded!");
            if (doc.hasChildNodes()) {
                printNote(doc.getChildNodes());
            } 
        } catch (ParserConfigurationException ex) {     
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            try {
                System.out.println("\tTrying to load XML...");
                File file = new File(fileName+".xml");
                DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                System.out.println("\tXML file was loaded!");
                if (doc.hasChildNodes()) {
                    printNote(doc.getChildNodes());
                }
            } catch (Exception e) {
                System.out.println("\tError loading XML!");
                System.out.println(e.getMessage());
            }
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


  private static void printNote(NodeList nodeList) {
    occupied = new HashMap<String, ArrayList<java.util.Date>>();
    for (int count = 0; count < nodeList.getLength(); count++) {
	Node tempNode = nodeList.item(count);
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
		if (tempNode.hasAttributes()) {
			NamedNodeMap nodeMap = tempNode.getAttributes();
			for (int i = 0; i < nodeMap.getLength(); i++) {
                            Node node = nodeMap.item(i);
                            String string = node.getNodeValue();
                            String[] parts = string.split(",");
                            String part1 = parts[0]; // artist
                            String part2 = parts[1]; // date
                            if (!occupied.containsKey(part1)) {
                                occupied.put(part1, new ArrayList<java.util.Date>());
                                occupied.get(part1).add(new java.util.Date(Long.valueOf(part2)));
                            } else occupied.get(part1).add(new java.util.Date(Long.valueOf(part2)));
			}
		}
		if (tempNode.hasChildNodes()) {
			printNote(tempNode.getChildNodes());
		}
	}
    }
  }
    
    public void create(String title) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
            rootElement = doc.createElement(title);
            doc.appendChild(rootElement);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void write(ResultSet rs) {
        try {
            while (rs.next()) {
                Element show = doc.createElement("Artist");
                Attr attr = doc.createAttribute("ID");
                attr.setValue(rs.getString(1));
                show.setAttributeNode(attr);
                
                Element el = doc.createElement("ShowDate");
                el.appendChild(doc.createTextNode(rs.getString(2)));
                show.appendChild(el);
                
                rootElement.appendChild(show);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void export() {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setAttribute("indent-number", 2);
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName+".xml"));
            transformer.transform(source, result);
        } catch (TransformerException  ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static HashMap<String, ArrayList<java.util.Date>> getOccupied() {
        return occupied;
    }
    
    
}
