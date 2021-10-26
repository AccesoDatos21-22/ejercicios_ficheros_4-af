package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import modelo.Farmacia;



import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class FarmaciaDOM{

	/**
	 * Lee los medicamentos de la farmacia de un fichero xml
	 * mediante DOM
	 * @param
	 * @return
	 */


	public static boolean leer(Path farmaciaXML) {

		/**
		 * Guarda los medicamentos de la farmacia en un fichero XML
		 * mediamente DOM
		 * @param farmacia
		 * @return
		 * */
		try {
			File file = new File(String.valueOf(farmaciaXML));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			document.getDocumentElement().normalize();
			System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
			NodeList nList = document.getElementsByTagName("Medicamento");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println(" Codigo : " + eElement.getElementsByTagName("Codigo").item(0).getTextContent());
					System.out.println("Nombre : " + eElement.getElementsByTagName("Nombre").item(0).getTextContent());
					System.out.println("Precio : " + eElement.getElementsByTagName("Precio").item(0).getTextContent());
					System.out.println("Stock : " + eElement.getElementsByTagName("Stock").item(0).getTextContent());
					System.out.println("Proveedor : " + eElement.getElementsByTagName("Proveedor").item(0).getTextContent());
				}
			}
		}
		catch(IOException | ParserConfigurationException e) {
			System.out.println(e);
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean guardar(String name, ArrayList<String> cod, ArrayList<String> nom, ArrayList<String> pre, ArrayList<String> stock, ArrayList<String> prov) throws Exception {
		if(cod.isEmpty() || nom.isEmpty() || cod.size()!=nom.size()){
			System.out.println("ERROR empty ArrayList");

		}else{

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, name, null);
			document.setXmlVersion("1.0");

			//Main Node
			Element raiz = document.getDocumentElement();
			//Por cada key creamos un item que contendrá la key y el value
			for(int i=0; i<cod.size();i++){
				//Item Node
				Element itemNode = document.createElement("Medicamento");
				//Key Node
				Element CodNode = document.createElement("Codigo");
				Text nodecodValue = document.createTextNode(cod.get(i));
				CodNode.appendChild(nodecodValue);
				//Value Node
				Element NombreNode = document.createElement("Nombre");
				Text nodeNombreValue = document.createTextNode(nom.get(i));
				NombreNode.appendChild(nodeNombreValue);

				Element preNode = document.createElement("Precio");
				Text nodepreValue = document.createTextNode(pre.get(i));
				preNode.appendChild(nodepreValue);

				Element stockNode = document.createElement("Stock");
				Text nodestockValue = document.createTextNode(stock.get(i));
				stockNode.appendChild(nodestockValue);

				Element provNode = document.createElement("Proveedor");
				Text nodeprovValue = document.createTextNode(prov.get(i));
				provNode.appendChild(nodeprovValue);
				//append keyNode and valueNode to itemNode
				itemNode.appendChild(CodNode);
				itemNode.appendChild(NombreNode);
				itemNode.appendChild(preNode);
				itemNode.appendChild(stockNode);
				itemNode.appendChild(provNode);
				//append itemNode to raiz
				raiz.appendChild(itemNode); //pegamos el elemento a la raiz "Documento"
			}
			//Generate XML
			Source source = new DOMSource(document);
			//Indicamos donde lo queremos almacenar
			Result result = new StreamResult(new java.io.File("C:\\Users\\Fernando\\textos\\"+name+".xml")); //nombre del archivo
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			System.out.println(result);
			System.out.println();
			transformer.transform(source, result);
		}
		return true;
	}

}
