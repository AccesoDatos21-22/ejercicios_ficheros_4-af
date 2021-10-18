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
	public static void testMedicamentoDAO() {
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("Paracetamol");
		medicamento.setPrecio(1.30D);
		medicamento.setCod(12);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		
		MedicamentoDAOImpl medicamentoDAO = new MedicamentoDAOImpl();
		
		assertEquals(true, medicamentoDAO.guardar(medicamento));
	}
	
	@Override
	public Medicamento buscar(String nombre) {
		return null;
	}
	
	@Override
	public boolean actualizar(Medicamento medicamento) {
		return false;
	}
	
	@Override
	public boolean borrar(Medicamento medicamento) {
		return false;
	}
	
	@Override
	public List<Medicamento> leerTodos() {
		return null;
	}
}
