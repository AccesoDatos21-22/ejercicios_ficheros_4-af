package dao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import modelo.Farmacia;
import modelo.Medicamento;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FarmaciaXSTREAM implements FarmaciaDAO {

	final static String rutaFarmcia = "C:\\Users\\Alumno\\textos\\Medicamentos.xml"; // localizacion del fichero XML
	final static String rutaMedicamento = ""; // localizacion del fichero XML


	@Override
	public Farmacia leer() {
		XStream xs = new XStream(new DomDriver());
		Medicamento f = new Medicamento();
		ArrayList<Medicamento> medis = new ArrayList<>();

		try {
			FileInputStream fis = new FileInputStream(rutaFarmcia);
			xs.fromXML(fis,f);

				System.out.println(f.toString());

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();

		}
		return null;

	}

	@Override
	public boolean guardar(Farmacia farmacia) {
		try {


			XStream xstream = new XStream(new DomDriver());
			xstream.alias("modelo.Medicamento", modelo.Medicamento.class);
			xstream.addImplicitCollection(Medicamento.class, "Farmacia");


			xstream.toXML(farmacia.leerTodos(), new FileOutputStream(rutaFarmcia));
			System.out.println("Creando...");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;


	}


	public Medicamento leerMedicamento() {
		XStream xs = new XStream(new DomDriver());
		Medicamento m = new Medicamento();
		try {
			FileInputStream fis = new FileInputStream(rutaFarmcia);
			xs.fromXML(fis, m);

			System.out.println(m.toString());
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();

		}
		return null;

	}

	public void guardarMedicamento(Medicamento medicamento) {
		try {


			XStream xstream = new XStream(new DomDriver());
			xstream.alias("modelo.Medicamento", modelo.Medicamento.class);
			xstream.addImplicitCollection(Medicamento.class, "Medicamento");


			xstream.toXML(medicamento, new FileOutputStream(rutaFarmcia));
			System.out.println("Creando...");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}


