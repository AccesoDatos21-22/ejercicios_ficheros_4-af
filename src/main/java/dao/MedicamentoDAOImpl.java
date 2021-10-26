package dao;

import jdk.swing.interop.SwingInterOpUtils;
import modelo.Medicamento;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MedicamentoDAOImpl implements MedicamentoDAO  {
	private static ByteArrayOutputStream escribir=null;
	private static ObjectOutputStream salida=null;
	private static byte []array=null;
	private static ArrayList<Medicamento> miLista = null;
	@Override
	public boolean guardar(Medicamento medicamento) {
		RandomAccessFile fichero =null;
		try {
			File f = new File("C:\\Users\\Fernando\\textos\\d.txt");

			if(!f.exists()){
				f.createNewFile();

			}
			 fichero = new RandomAccessFile(f, "rw");
			fichero.seek(fichero.length());
			//fichero.writeBytes(medicamento.toString());
			escribir= new ByteArrayOutputStream();
			salida = new ObjectOutputStream(escribir);
			salida.writeObject(miLista);


			// Cerramos el objeto.
			salida.close();
			//array = escribir.toByteArray();
			fichero.write(escribir.toByteArray());
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		try {
			if (fichero != null) {
				fichero.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
		return true;
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
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
		assertEquals(true, medicamentoDAO.guardar(medicamento));
		
		medicamentoDAO.borrar(medicamento);
	}

	@Override
	public Medicamento buscar(String nombre) {
		boolean existe = miLista.contains("nombre :"+nombre);
		if (existe) {
			System.out.println("El elemento si existe en la lista");
		} else {
			System.out.println("El elemento no existe");
		}

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
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		
		assertEquals(true, medicamentoDAO.buscar("ParacetamolTest"));
		
		medicamentoDAO.borrar(medicamento);
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
	miLista.add(medicamento);
	guardar(medicamento);
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
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		
		assertEquals(true, medicamentoDAO.actualizar(medicamento));
		
		medicamentoDAO.borrar(medicamento);
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		miLista.remove(medicamento);
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
		
		dao.MedicamentoDAOImpl medicamentoDAO = new dao.MedicamentoDAOImpl();
		
		medicamentoDAO.guardar(medicamento);
		
		assertEquals(true, medicamentoDAO.borrar(medicamento));
	}

	@Override
	public List<Medicamento> leerTodos() {

		try {
			miLista = new ArrayList<>();
			ObjectInputStream leyendoFichero = new ObjectInputStream(
					new FileInputStream("C:\\Users\\Alumno\\textos\\doc.txt"));
			miLista.add((Medicamento) leyendoFichero.readObject());
			leyendoFichero.close();

			System.out.println("ok!");
			System.out.println("Datos leídos del fichero:");



		}catch(Exception e) {
			System.out.println( e.getMessage() );
		}
		return miLista;
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
		
		assertEquals(true, get1 && get2);
	}
}
