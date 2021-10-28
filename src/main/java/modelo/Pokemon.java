package modelo;
/*
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
*/
import java.io.Serializable;

//@XmlRootElement(name="Pokemon")
//@XmlType(propOrder = { "nombre", "vida", "velocidad", "ataque", "defensa" })

public class Pokemon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private int vida;
	private int velocidad;
	private int ataque;
	private int defensa;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public int getAtaque() {
		return ataque;
	}
	
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	
	public int getDefensa() {
		return defensa;
	}
	
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	
	@Override
	public String toString() {
		return "Pokemon{" +
				"nombre='" + nombre + '\'' +
				", vida=" + vida +
				", velocidad=" + velocidad +
				", ataque=" + ataque +
				", defensa=" + defensa +
				'}';
	}
}
