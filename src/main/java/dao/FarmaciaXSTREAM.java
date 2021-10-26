package dao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import modelo.Farmacia;
import modelo.Medicamento;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FarmaciaXSTREAM implements FarmaciaDAO{
	
	final static String rutaFarmcia =""; // localizacion del fichero XML
	final static String rutaMedicamento =""; // localizacion del fichero XML


	/*public static Farmacia leer() {

		return null;
	}*/

	@Override
	public Farmacia leer() {
		return null;
	}

	@Override
	public boolean guardar(Farmacia farmacia) {
		return false;
	}


	public static boolean guardad(Medicamento medicamento) throws FileNotFoundException {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Medicamentos",Medicamento.class);
		xstream.addImplicitCollection(Medicamento.class,"Medicamento");


		xstream.toXML(medicamento,new FileOutputStream("\"C:\\Users\\Fernando\\textos\\Medicamentos.xml\""));
		System.out.println("Creando...");
		return false;
	}


	public Medicamento leerMedicamento() {
		
		return null;
	}

	public void guardarMedicamento(Medicamento medicamento) {
	}

	
}
