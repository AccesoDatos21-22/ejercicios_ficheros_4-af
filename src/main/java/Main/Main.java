package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import dao.FarmaciaDOM;
import dao.FarmaciaXSTREAM;
import dao.JCCPokemonJAXB;

import dao.MedicamentoDAOImpl;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import modelo.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import extra.Ejer7;
/*
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
*/
import static dao.FarmaciaXSTREAM.*;
class Main {

	private static final String JAXB_XML_FILE = "xml/EmpresaJAXB.xml";
	private static final String XSTREAM_XML_FILE = "xml/EmpresaXTREAM.xml";
	private static final String DOM_XML_FILE = "xml/EmpleadosDOM.xml";
	


	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(System.getProperty("user.dir"));
		
		// Ejercicio 5
		ejer5();
		
		// Padding
		System.out.println();
		
		// Ejercicio 7
		ejer7();
		
		fernando();
	}
	
	private static void ejer5() {
		JCCPokemon pokemons = new JCCPokemon();
		
		Calendar calendar = new GregorianCalendar(2013, 2, 20);
		pokemons.setFechaLanzamiento(calendar.getTime());
		pokemons.setNumCartas(3);
		
		Pokemon pikachu = new Pokemon();
		pikachu.setNombre("Pikachu");
		pikachu.setVida(3);
		pikachu.setVelocidad(2);
		pikachu.setAtaque(4);
		pikachu.setDefensa(2);
		
		pokemons.add(pikachu);
		
		Pokemon raichu = new Pokemon();
		raichu.setNombre("Raichu");
		raichu.setVida(30);
		raichu.setVelocidad(20);
		raichu.setAtaque(40);
		raichu.setDefensa(20);
		
		pokemons.add(raichu);
		
		Pokemon charmander = new Pokemon();
		charmander.setNombre("Charmander");
		charmander.setVida(40);
		charmander.setVelocidad(50);
		charmander.setAtaque(10);
		charmander.setDefensa(10);
		
		pokemons.add(charmander);
		
		JCCPokemonJAXB jaxb = new JCCPokemonJAXB();
		
		jaxb.guardar(pokemons);
		
		JCCPokemon pokemons2 = jaxb.leer();
		
		System.out.println("Fecha de lanzamiento=" + pokemons2.getFechaLanzamientoString());
		System.out.println("Numero de cartas=" + pokemons2.getNumCartas());
		
		for (Pokemon pokemon : pokemons2.getPokemones()) {
			System.out.println(pokemon.toString());
		}
	}
	
	private static void fernando() {
		// ejemploJaxb();
		// ejemploEscribirDOM();
		// ejemploLeerDOM();
		// ejemploEscribirXSTREAM();
		// ejemploLeerXSTREAM();
		Medicamento medicamento = new Medicamento();
		medicamento.setNombre("ibunoTest");
		medicamento.setPrecio(2.90);
		medicamento.setCod(112);
		medicamento.setStock(5);
		medicamento.setStockMaximo(7);
		medicamento.setStockMinimo(3);
		medicamento.setCodProveedor(1234);
		MedicamentoDAOImpl medi = new MedicamentoDAOImpl();
		List<Medicamento> miLista = medi.leerTodos();
		for (int i = 0; i < miLista.size(); i++) {
			System.out.println("Medicamentos[" + i + "] = " + miLista.get(i));
		}
		medi.actualizar(medicamento);
		medi.actualizar(medicamento);
		medi.actualizar(medicamento);
		medi.actualizar(medicamento);
		medi.borrar(medicamento);
		System.out.println("Escribe medicamento para comprobar si esta en stock");
		
		Scanner teclado = new Scanner(System.in);
		
		medi.buscar(teclado.nextLine());


	for (int i = 0; i < miLista.size(); i++) {
			System.out.println("Medicamentos[" + i + "] = " + miLista.get(i));
		}
		medi.borrar(medicamento);
		for (int i = 0; i < miLista.size(); i++) {
			System.out.println("Medicamentos[" + i + "] = " + miLista.get(i));
		}
		String nombre_archivo = "Medicamentos";
		ArrayList cod = new ArrayList();
		ArrayList nom = new ArrayList();
		ArrayList pre = new ArrayList();
		ArrayList stock = new ArrayList();
		ArrayList prov = new ArrayList();

		cod.add("10");
		nom.add("iburpofeno");
		pre.add("1.2");
		stock.add("14");
		prov.add("1234");

		cod.add("11");
		nom.add("paracetamol");
		pre.add("1.5");
		stock.add("23");
		prov.add("1224");





		try {
			FarmaciaDOM.guardar(nombre_archivo, cod, nom,pre,stock,prov);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//FarmaciaDOM.leer(Path.of("C:\\Users\\Alumno\\textos\\Medicamentos.xml"));
		FarmaciaXSTREAM f  = new FarmaciaXSTREAM();

		f.guardarMedicamento(medicamento);
		f.leerMedicamento();
		Farmacia far = new Farmacia();
		far.guardar(medicamento);
		f.guardar(far);
		f.leer();

	}
	
	private static void ejer7() {
		String villalba = "https://api.openweathermap.org/data/2.5/forecast/daily?q=Villalba&units=metric&mode=xml&appid=479092b77bcf850403cb2aeb1a302425";
		
		Ejer7 time = new Ejer7();
		time.readClimate(villalba);
	}

	private static void ejemploEscribirXSTREAM() {

		try {

			System.out.println("Comienza el proceso de creaci??n del fichero a XML...");

			XStream xstream = new XStream();
			
			long time = System.currentTimeMillis();
			System.out.println("Inicio: " + new Date(time));
			Empresa cc = new Empresa();
			cc.setIdEmpresa(1);
			cc.setDireccion("En la nube");
			cc.setNombreEmpresa("IES");
			cc.setNumEmpleados(10);

			ArrayList<Empleado> alCU = new ArrayList<Empleado>();
			int init = 20000;
			for (int i = 1; i < 10; i++) {
				Empleado cu = new Empleado();
				cu.setId(i);
				cu.setActivo(true);
				cu.setNumeroEmpl(init++);
				cu.setNombre("Empleado " + i);
				cu.setTitulo("SW Architect");
				cu.setFechaAlta(new Date(System.currentTimeMillis()));

				alCU.add(cu);
			}

			cc.setEmpleados(alCU);
			
			// cambiar de nombre a las etiquetas XML
			xstream.alias("Empleado", Empleado.class);
			xstream.alias("Empresa", Empresa.class);

			// quitar etiqueta lista (Atributo de la clase ListaEmpleados)
			xstream.addImplicitCollection(Empresa.class, "Empresa");
			// Insertar los objetos en XML
			xstream.toXML(cc, new FileOutputStream(XSTREAM_XML_FILE));
			System.out.println("Creado fichero XML....");

		} catch (IOException e) {
			System.err.println("Error: " + e);
		}
	}

	private static void ejemploLeerXSTREAM() {
		Empresa empresa = new Empresa();
        try {
            Class<?>[] classes = new Class[] { Empresa.class, Empleado.class };

            XStream xstream = new XStream();
            //XStream.setupDefaultSecurity(xstream);
            //xstream.allowTypes(classes);
           
            xstream.alias("Empresa", Empresa.class);
            xstream.alias("Empleado", Empleado.class);
            xstream.addImplicitCollection(Empresa.class, "Empresa");

            empresa = (Empresa) xstream
                    .fromXML(new FileInputStream(XSTREAM_XML_FILE));

            for(Empleado e: empresa.getEmpleados()) {
            	System.out.println(e);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e);
        }

	}

	private static void ejemploLeerDOM() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(DOM_XML_FILE));
			document.getDocumentElement().normalize();

			// Obtenemos la lista de nodos con nombre empleado de todo el documento
			NodeList empleados = document.getElementsByTagName("empleado");

			for (int i = 0; i < empleados.getLength(); i++) {
				Node emple = empleados.item(i); // obtener un nodo
				if (emple.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) emple; // tipo de nodo
					System.out.println("ID: " + getNodo("id", elemento));
					System.out.println("Apellido: " + getNodo("nombre", elemento));
					System.out.println("Departamento: " + getNodo("dep", elemento));
					System.out.println("Salario: " + getNodo("salario", elemento));
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// obtener informaci??n de un nodo
	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve el valor del nodo
	}

	private static void ejemploEscribirDOM() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Empleados", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro XML

			for (int i = 1; i < 10; i++) {
				Element raiz = document.createElement("empleado");

				document.getDocumentElement().appendChild(raiz);

				CrearElemento("id", Integer.toString(i), raiz, document);
				CrearElemento("nombre", "Empleado " + i, raiz, document);
				CrearElemento("dep", "01", raiz, document);
				CrearElemento("salario", "1000.0", raiz, document);
			}

			// Creamos la fuente XML a partir del documento
			Source source = new DOMSource(document);
			// Creamos el resultado en el fichero Empleados.xml
			Result result = new StreamResult(new java.io.File(DOM_XML_FILE));
			// Obtenemos un TransformerFactory
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			// Le damos formato y realizamos la transformaci??n del documento a fichero
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);
			// Mostramos el documento por pantalla especificando el canal de salida el
			// System.out
			Result console = new StreamResult(System.out);

			transformer.transform(source, console);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

	private static void ejemploJaxb() {
		long time = System.currentTimeMillis();
		System.out.println("Inicio: " + new Date(time));
		Empresa cc = new Empresa();
		cc.setIdEmpresa(1);
		cc.setDireccion("En la nube");
		cc.setNombreEmpresa("IES");
		cc.setNumEmpleados(10);

		ArrayList<Empleado> alCU = new ArrayList<Empleado>();
		int init = 20000;
		for (int i = 1; i < 10; i++) {
			Empleado cu = new Empleado();
			cu.setId(i);
			cu.setActivo(true);
			cu.setNumeroEmpl(init++);
			cu.setNombre("Empleado " + i);
			cu.setTitulo("SW Architect");
			cu.setFechaAlta(new Date(System.currentTimeMillis()));

			alCU.add(cu);
		}

		cc.setEmpleados(alCU);

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Empresa.class);

			// Si las clases a serializar est??n en otro paquete se indica el paquete
			// al crear el marshall
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Provincia provincia = fillProvincia();
			// Mostramos el documento XML generado por la salida estandar
			marshaller.marshal(cc, System.out);
			// guardamos el objeto serializado en un documento XML
			marshaller.marshal(cc, Files.newOutputStream(Paths.get(JAXB_XML_FILE)));
			Unmarshaller unmarshaller = context.createUnmarshaller();
			// Deserealizamos a partir de un documento XML
			Empresa empresa = (Empresa) unmarshaller.unmarshal(Files.newInputStream(Paths.get(JAXB_XML_FILE)));
			System.out.println("********* Empresa cargado desde fichero XML***************");
			// Mostramos por linea de comandos el objeto Java obtenido
			// producto de la deserialziacion
			marshaller.marshal(empresa, System.out);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}
}
