package dao;

import modelo.Medicamento;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MedicamentoDAOImpl implements MedicamentoDAO {
	@Override
	public boolean guardar(Medicamento medicamento) {
		return false;
	}
	
	@Test
	public static void testGuardar() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("ParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		MedicamentoDAOImpl medicamentoDAO = new MedicamentoDAOImpl();
		
		assertEquals(true, medicamentoDAO.guardar(medicamento));
		
		medicamentoDAO.borrar(medicamento);
	}
	
	@Override
	public Medicamento buscar(String nombre) {
		return null;
	}
	
	@Test
	public static void testBuscar() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("ParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		MedicamentoDAOImpl medicamentoDAO = new MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		
		assertEquals(true, medicamentoDAO.buscar("ParacetamolTest"));
		
		medicamentoDAO.borrar(medicamento);
	}
	
	@Override
	public boolean actualizar(Medicamento medicamento) {
		return false;
	}
	
	@Test
	public static void testActualizar() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("ParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		MedicamentoDAOImpl medicamentoDAO = new MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		
		assertEquals(true, medicamentoDAO.actualizar(medicamento));
		
		medicamentoDAO.borrar(medicamento);
	}
	
	@Override
	public boolean borrar(Medicamento medicamento) {
		return false;
	}
	
	@Test
	public static void testBorrar() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("ParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		MedicamentoDAOImpl medicamentoDAO = new MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		
		assertEquals(true, medicamentoDAO.borrar(medicamento));
	}
	
	@Override
	public List<Medicamento> leerTodos() {
		return null;
	}
	
	@Test
	public static void testLeerTodos() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("ParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		Medicamento medicamento2 = new Medicamento();
		medicamento2.setNombre("Algidol");
		medicamento2.setPrecio(1.20D);
		medicamento2.setCod(10);
		medicamento2.setStock(4);
		medicamento2.setStockMaximo(8);
		medicamento2.setStockMinimo(2);
		medicamento2.setCodProveedor(4567);
		
		MedicamentoDAOImpl medicamentoDAO = new MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		medicamentoDAO.guardar(medicamento2);
		
		boolean get1 = false;
		boolean get2 = false;
		
		for (Medicamento thisMedicamento : medicamentoDAO.leerTodos()) {
			if (thisMedicamento.equals(medicamento)) {
				get1 = true;
			} else if (thisMedicamento.equals(medicamento2)) {
				get2 = true;
			}
		}
		
		assertEquals(true, get1 && get2);
	}
}
