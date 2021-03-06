package modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Medicamento implements Serializable {
	public final static float IVA = 0.04f;
	
	private String nombre; // tama?o 30, 60 bytes
	private double precio; // 8 bytes
	private int cod; // 4 bytes
	private int stock; // 4 bytes
	private int stockMaximo; // 4 bytes
	private int stockMinimo; // 4 bytes
	private int codProveedor; // 4 bytes
	
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if (nombre != null) {
			if (nombre.length() > 50) {
				this.nombre = nombre.substring(0, 50);
			} else {
				this.nombre = nombre;
			}
		}
	}
	
	public double getPrecio() {
		return precio * 0.04D;
	}
	
	public void setPrecio(double precio) {
		DecimalFormat df = new DecimalFormat("#.##");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		
		this.precio = Double.valueOf(df.format(precio));
	}
	
	public int getCod() {
		return cod;
	}
	
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getStockMaximo() {
		return stockMaximo;
	}
	
	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}
	
	public int getStockMinimo() {
		return stockMinimo;
	}
	
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	
	public int getCodProveedor() {
		return codProveedor;
	}
	
	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}
	
	public boolean equals(Medicamento medicamento) {
		return cod == medicamento.getCod();
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		
		return
						 "C?digo: " + cod
				+ "\r\n" + "Nombre: " + nombre
				+ "\r\n" + "Precio: " + df.format(precio) + "?"
				+ "\r\n" + "Stock: " + stock
				+ "\r\n" + "Proveedor: " + codProveedor
				+ "\r\n";
	}
}
