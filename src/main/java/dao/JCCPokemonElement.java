package dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class JCCPokemonElement {
	private Document document;
	private Element raiz;
	
	public JCCPokemonElement(Document document, Element main) {
		this.document = document;
		
		raiz = document.createElement("Pokemon");
		main.appendChild(raiz);
	}
	
	public void appendChild(String key, Object value) {
		Element elem = document.createElement(key);
		Text text = document.createTextNode(String.valueOf(value));
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
}
