package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JCCPokemon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Pokemon> pokemones;
	private Date fechaLanzamiento;
	private int numCartas;
	
	public JCCPokemon() {
		pokemones = new ArrayList<>();
	}
	
	public List<Pokemon> getPokemones() {
		return pokemones;
	}
	
	public void add(Pokemon pokemon) {
		pokemones.add(pokemon);
	}
	
	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}
	
	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}
	
	public int getNumCartas() {
		return numCartas;
	}
	
	public void setNumCartas(int numCartas) {
		this.numCartas = numCartas;
	}
}
