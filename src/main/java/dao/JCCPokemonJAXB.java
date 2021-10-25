package dao;

import modelo.JCCPokemon;
import modelo.Pokemon;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JCCPokemonJAXB implements JCCPokemonDAO {
	
	@Override
	public JCCPokemon leer() {
		return null;
	}
	
	@Override
	public boolean guardar(JCCPokemon pokemones) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document document = implementation.createDocument(null, "Pokemons",null);
			document.setXmlVersion("1.0");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Element fecha = document.createElement("FechaLanzamiento");
			Text text = document.createTextNode(dateFormat.format(pokemones.getFechaLanzamiento()));
			document.getDocumentElement().appendChild(fecha);
			fecha.appendChild(text);
			
			Element numCartas = document.createElement("NumCartas");
			text = document.createTextNode(String.valueOf(pokemones.getNumCartas()));
			document.getDocumentElement().appendChild(numCartas);
			numCartas.appendChild(text);
			
			for (Pokemon pokemon : pokemones.getPokemones()) {
				JCCPokemonElement element = new JCCPokemonElement(document);
				
				element.appendChild("nombre", pokemon.getNombre());
				element.appendChild("vida", pokemon.getVida());
				element.appendChild("velocidad", pokemon.getVelocidad());
				element.appendChild("ataque", pokemon.getAtaque());
				element.appendChild("defensa", pokemon.getDefensa());
			}
			
			Source source = new DOMSource(document);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			Result result = new StreamResult(new java.io.File("Pokemons.xml"));
			
			transformer.transform(source, result);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
	
	private static void CrearElemento(String key, String value,Element root, Document document){
		Element elem = document.createElement(key);
		Text text = document.createTextNode(value);
		root.appendChild(elem);
		elem.appendChild(text);
	}
}
