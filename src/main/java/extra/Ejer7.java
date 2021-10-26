package extra;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Ejer7 {
	/**
	 * Imprime el clima de la URL dada en formato de la API de OpenWeatherMap.
	 * En inglés, ya que es el idioma original de dicha API.
	 * https://openweathermap.org/
	 * @author Alberto Jiménez
	 * 
	 * @param xml URI del XML
	 */
	public void readClimate(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xml);
			document.getDocumentElement().normalize();
			
			// City name
			String city = document.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
			System.out.println(city + " - Weather for this week");
			
			NodeList weatherList = document.getElementsByTagName("time");
			
			for (int i = 0; i < weatherList.getLength(); i++){
				Node weatherNode = weatherList.item(i);
				
				if (weatherNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element)weatherNode;
					
					// Padding
					System.out.println();
					
					// Day
					System.out.print("    Weather for ");
					System.out.println(element.getAttributes().getNamedItem("day").getNodeValue());
					
					// Weather
					System.out.print("Today we will have ");
					System.out.print(getAttribute("symbol", "name", element));
					System.out.print(" with a precipitation chance of ");
					System.out.print(Float.valueOf(getAttribute("precipitation", "probability", element)) * 100);
					System.out.println("%");
					System.out.print("Humidity will be at ");
					System.out.print(getAttribute("humidity", "value", element));
					System.out.println("%");
					
					// Temps
					System.out.println("Temps:");
					System.out.print("Day: ");
					System.out.print(getAttribute("temperature", "day", element));
					System.out.print("c, night: ");
					System.out.print(getAttribute("temperature", "night", element));
					System.out.println("c");
					System.out.print("Min: ");
					System.out.print(getAttribute("temperature", "min", element));
					System.out.print("c, max: ");
					System.out.print(getAttribute("temperature", "max", element));
					System.out.println("c");
					
					// Wind
					System.out.println("Wind:");
					System.out.print(getAttribute("windSpeed", "name", element));
					System.out.print(", at ");
					System.out.print(getAttribute("windSpeed", "mps", element));
					System.out.print("m/s, ");
					System.out.print(" with gusts of ");
					System.out.print(getAttribute("windGust", "gust", element));
					System.out.println("m/s");
					
//					pokemon.setNombre(getNode("windDirection", element));
//					pokemon.setVida(getNodeInt("Vida", element));
//					pokemon.setVelocidad(getNodeInt("Velocidad", element));
//					pokemon.setAtaque(getNodeInt("Ataque", element));
//					pokemon.setDefensa(getNodeInt("Defensa", element));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private String getAttribute(String key, String attribute, Element element) {
		return element.getElementsByTagName(key).item(0).getAttributes().getNamedItem(attribute).getNodeValue();
	}
}
