package dao;

import modelo.Medicamento;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MedicamentoDAOImpl implements MedicamentoDAO {
	@Override
	public boolean guardar(Medicamento medicamento) {
		return false;
	}
	
	@Test
	public static void testGuardar() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("GuardarParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
		assertTrue(medicamentoDAO.guardar(medicamento));
		
		assertTrue(medicamentoDAO.buscar("GuardarParacetamolTest") != null);
		
		medicamentoDAO.borrar(medicamento);
	}
	
	@Override
	public Medicamento buscar(String nombre) {
		return null;
	}
	
	@Test
	public static void testBuscar() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("BuscarParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		
		boolean mustBeTrue1 = medicamentoDAO.buscar("BuscarParacetamolTest") != null;
		boolean mustBeTrue2 = medicamentoDAO.buscar("BuscarParacetamolDoesNotExist") == null;
		
		assertTrue(mustBeTrue1 && mustBeTrue2);
		
		medicamentoDAO.borrar(medicamento);
	}
	
	@Override
	public boolean actualizar(Medicamento medicamento) {
		return false;
	}
	
	@Test
	public static void testActualizar() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("ActualizarParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		
		medicamento.setStock(8);
		
		assertTrue(medicamentoDAO.actualizar(medicamento));
		
		assertTrue(medicamentoDAO.buscar("ActualizarParacetamolTest").getStock() == 8);
		
		medicamentoDAO.borrar(medicamento);
	}
	
	@Override
	public boolean borrar(Medicamento medicamento) {
		return false;
	}
	
	@Test
	public static void testBorrar() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("BorrarParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
		medicamentoDAO.buscar("BorrarParacetamolTest");
		
		medicamentoDAO.guardar(medicamento);
		
		assertTrue(medicamentoDAO.borrar(medicamento));
		
		assertTrue(medicamentoDAO.buscar("BorrarParacetamolTest") != null);
	}
	
	@Override
	public List<Medicamento> leerTodos() {
		return null;
	}
	
	@Test
	public static void testLeerTodos() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("LeerTodosParacetamolTest");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		Medicamento medicamento2 = new Medicamento();
		medicamento2.setNombre("LeerTodosAlgidolTest");
		medicamento2.setPrecio(1.20D);
		medicamento2.setCod(10);
		medicamento2.setStock(4);
		medicamento2.setStockMaximo(8);
		medicamento2.setStockMinimo(2);
		medicamento2.setCodProveedor(4567);
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
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
		
		assertTrue(get1 && get2);
		
		medicamentoDAO.borrar(medicamento);
		medicamentoDAO.borrar(medicamento2);
	}
}
