package modelo;

import java.util.ArrayList;
import java.util.List;

import dao.MedicamentoDAO;

public class Farmacia implements MedicamentoDAO {
	private List<Medicamento> medicamentos;

	/**
	 * Constructor de la farmacia
	 */
	public Farmacia() {
	medicamentos = new ArrayList<Medicamento>();
	}

	@Override
	public boolean guardar(Medicamento medicamento) {
	medicamentos.add(medicamento);
		return false;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
	medicamentos.remove(medicamento);
		return false;
	}

	@Override
	public List<Medicamento> leerTodos() {

		return medicamentos;
	}

	@Override
	public Medicamento buscar(String nombre) {
		for(Medicamento medicamento : medicamentos ) {
			if (medicamento.getNombre().equals(nombre)) {
				return medicamento;
			}
		}
		return null;

	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		for(int i=0; i<=medicamentos.size()-1;i++) {
		if(medicamento.getCod() == medicamentos.get(i).getCod()){
			medicamentos.set(i,medicamento);
		}
		}
		return false;
	}
}
