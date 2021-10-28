package dao;

import modelo.JCCPokemon;
import modelo.Pokemon;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JCCPokemonJAXB implements JCCPokemonDAO {
	
	@Override
	public JCCPokemon leer() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			JCCPokemon pokemons = new JCCPokemon();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("Pokemons.xml"));
			document.getDocumentElement().normalize();
			
			// FechaLanzamiento
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String fechaLanzamiento = document.getElementsByTagName("FechaLanzamiento").item(0).getChildNodes().item(0).getNodeValue();
			
			pokemons.setFechaLanzamiento(dateFormat.parse(fechaLanzamiento));
			
			// NumCartas
			String numCartas = document.getElementsByTagName("NumCartas").item(0).getChildNodes().item(0).getNodeValue();
			
			pokemons.setNumCartas(Integer.valueOf(numCartas));
			
			// Pokemon
			NodeList pokemonList = document.getElementsByTagName("Pokemon");
			
			for (int i = 0; i < pokemonList.getLength(); i++){
				Node pokemonNode = pokemonList.item(i);
				
				if (pokemonNode.getNodeType() == Node.ELEMENT_NODE) {
					Pokemon pokemon = new Pokemon();
					
					Element element = (Element)pokemonNode;
					
					pokemon.setNombre(getNode("Nombre", element));
					pokemon.setVida(getNodeInt("Vida", element));
					pokemon.setVelocidad(getNodeInt("Velocidad", element));
					pokemon.setAtaque(getNodeInt("Ataque", element));
					pokemon.setDefensa(getNodeInt("Defensa", element));
					
					pokemons.add(pokemon);
				}
			}
			
			return pokemons;
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
	
	private String getNode(String key, Element element) {
		NodeList nodeList = element.getElementsByTagName(key).item(0).getChildNodes();
		Node node = (Node)nodeList.item(0);
		
		return node.getNodeValue();
	}
	
	private int getNodeInt(String key, Element element) {
		return Integer.valueOf(getNode(key, element));
	}
	
	@Override
	public boolean guardar(JCCPokemon pokemones) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document document = implementation.createDocument(null, "JCCPokemon",null);
			document.setXmlVersion("1.0");
			
			Element fecha = document.createElement("FechaLanzamiento");
			Text text = document.createTextNode(pokemones.getFechaLanzamientoString());
			document.getDocumentElement().appendChild(fecha);
			fecha.appendChild(text);
			
			Element numCartas = document.createElement("NumCartas");
			text = document.createTextNode(String.valueOf(pokemones.getNumCartas()));
			document.getDocumentElement().appendChild(numCartas);
			numCartas.appendChild(text);
			
			Element main = document.createElement("Pokemons");
			document.getDocumentElement().appendChild(main);
			
			for (Pokemon pokemon : pokemones.getPokemones()) {
				JCCPokemonElement element = new JCCPokemonElement(document, main);
				
				element.appendChild("Nombre", pokemon.getNombre());
				element.appendChild("Vida", pokemon.getVida());
				element.appendChild("Velocidad", pokemon.getVelocidad());
				element.appendChild("Ataque", pokemon.getAtaque());
				element.appendChild("Defensa", pokemon.getDefensa());
			}
			
			Source source = new DOMSource(document);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			Result result = new StreamResult(new java.io.File("JCCPokemon.xml"));
			
			transformer.transform(source, result);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
}
