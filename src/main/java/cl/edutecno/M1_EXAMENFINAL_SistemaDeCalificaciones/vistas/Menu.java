package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.vistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.Alumno;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.Materia;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.MateriaEnum;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.servicios.AlumnoServicio;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.servicios.ArchivoServicio;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.utilidades.Utilidad;

public class Menu extends MenuTemplate {

	AlumnoServicio alumnosServicio = new AlumnoServicio();
	ArchivoServicio archivoServicio = new ArchivoServicio();
	static Scanner scS = new Scanner(System.in);
	static Scanner scI = new Scanner(System.in);
	static Scanner scF = new Scanner(System.in);

	// Metodos Sobreescritos
	@Override
	public void cargarDatos() {
		// TODO Auto-generated method stub
		super.cargarDatos();
		String ruta = "";

		System.out.println("");
		System.out.println("---------------CARGAR DATOS---------------");
		System.out.println("");
		System.out.print("Ingrese la ruta en donde se encuentra el archivo notas.csv: ");
		ruta = scS.nextLine();// Ruta formato ejemplo: src/directorio/notas.csv

		for (Alumno alumno : archivoServicio.cargarDatos(ruta)) {// Itera sobre lista de alumnos
			alumnosServicio.getListaAlumnos().put(alumno.getRut(), alumno);// Guarda alumnos en Map
		}

		System.out.println("Datos exportados correctamente.");
		System.out.println("-----------------------------------------");
		System.out.println("");
	}

	@Override
	public void exportarDatos() {
		// TODO Auto-generated method stub
		super.exportarDatos();
		String ruta = "";

		System.out.println("");
		System.out.println("---------------EXPORTAR DATOS---------------");
		System.out.println("");
		System.out.print("Ingrese la ruta en donde se guardara el archivo notas.txt: ");
		ruta = scS.nextLine();// Ruta formato ejemplo: src/directorio

		archivoServicio.exportarDatos(alumnosServicio.listarAlumnos(), ruta);// Envia Map para exportar

		System.out.println("-----------------------------------------");
		System.out.println("");

	}

	@Override
	public void crearAlumno() {
		// TODO Auto-generated method stub
		super.crearAlumno();

		String dato = "";
		Alumno alumno = new Alumno();
		List<Materia> listaMaterias = new ArrayList<Materia>();

		try {
			System.out.println("");
			System.out.println("---------------CREAR ALUMNO---------------");
			System.out.println("");
			System.out.print("Ingrese RUT: ");
			dato = scS.nextLine();
			alumno.setRut(dato);
			System.out.print("Ingrese Nombre: ");
			dato = scS.nextLine();
			alumno.setNombre(dato);
			System.out.print("Ingrese Apellido: ");
			dato = scS.nextLine();
			alumno.setApellido(dato);
			System.out.print("Ingrese Direccion: ");
			dato = scS.nextLine();
			alumno.setDireccion(dato);
			alumno.setMaterias(listaMaterias);// Se agrega lista de materias

			alumnosServicio.crearAlumno(alumno);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en ejecucion Crear Alumno: " + e.getMessage());
		}

		System.out.println("-----------------------------------------");
		System.out.println("");
	}

	@Override
	public void agregarMateria() {
		// TODO Auto-generated method stub
		super.agregarMateria();

		Materia materia = new Materia();
		ArrayList<Float> listaNotasMaterias = new ArrayList<Float>();
		String rut = " ";
		int opcion = 0;

		try {
			System.out.println("");
			System.out.println("---------------AGREGAR MATERIA---------------");
			System.out.println("");
			System.out.print("Ingrese RUT del Alumno: ");
			rut = scS.nextLine();

			System.out.println("");
			System.out.println("1. " + MateriaEnum.MATEMATICAS);
			System.out.println("2. " + MateriaEnum.LENGUAJE);
			System.out.println("3. " + MateriaEnum.CIENCIA);
			System.out.println("4. " + MateriaEnum.HISTORIA);
			System.out.print("Selecciona una materia: ");
			opcion = scI.nextInt();

			switch (opcion) {// Se agrega materia y lista de notas
			case 1:
				materia.setNombre(MateriaEnum.MATEMATICAS);
				materia.setNotas(listaNotasMaterias);
				break;
			case 2:
				materia.setNombre(MateriaEnum.LENGUAJE);
				materia.setNotas(listaNotasMaterias);
				break;
			case 3:
				materia.setNombre(MateriaEnum.CIENCIA);
				materia.setNotas(listaNotasMaterias);
				break;
			case 4:
				materia.setNombre(MateriaEnum.HISTORIA);
				materia.setNotas(listaNotasMaterias);
				break;
			default:
				System.out.println("Seleccion no valida, no se pudo agregar una materia");
				break;
			}

			alumnosServicio.agregarMateria(rut, materia);// Se envia materia de alumno

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en ejecucion Agregar Materia: " + e.getMessage());
		}
		System.out.println("-----------------------------------------");
		System.out.println("");

	}

	@Override
	public void agregarNotaPasoUno() {
		// TODO Auto-generated method stub
		super.agregarNotaPasoUno();

		List<Materia> listaMaterias = new ArrayList<Materia>();
		String rut = "";
		String opcion2 = "";
		float nota = 0f;
		int count = 1;
		int opcion = 0;

		try {
			System.out.println("");
			System.out.println("---------------AGREGAR NOTA---------------");
			System.out.println("");
			System.out.print("Ingrese RUT del Alumno: ");
			rut = scS.nextLine();
			System.out.println("Alumno tiene las siguientes materias agregadas: ");

			// Opcion para filtrar con Stream segun rut sin contador
//			alumnosServicio.materiasPorAlumnos(rut).stream().forEach(i ->
//			System.out.println(i.getNombre()));

			listaMaterias = alumnosServicio.materiasPorAlumnos(rut);
			for (Materia materia : listaMaterias) {// Filtrado de Materias por alumno segun rut con contador
				System.out.printf("%d. %s\n", count, materia.getNombre());
				count++;
			}

			System.out.print("Seleccionar materia: ");
			opcion = scI.nextInt();
			opcion -= 1;// Corregir indice lista

			// Opcion de agregar nota con ciclo, permite agregar multiples notas por
			// materias
			do {
				System.out.print("Ingresar nota: ");
				nota = scF.nextFloat();
				listaMaterias.get(opcion).getNotas().add(nota);// Agrega nota en lista de notas
				System.out.printf("¿Desea ingresar otra nota en %s?[s/n]: ", listaMaterias.get(opcion).getNombre());
				opcion2 = scS.nextLine();

			} while (opcion2.equalsIgnoreCase("s"));

			// Opcion de agregar nota sin ciclo, permite agregar una sola nota por materia
//			System.out.print("Ingresar nota: ");
//			nota = scF.nextFloat();
//			listaMaterias.get(opcion).getNotas().add(nota);//Agrega nota en lista de notas

			alumnosServicio.agregarNotas(listaMaterias, rut);// Se envia lista de materias con nota
			System.out.printf("nota agregada a %s\n", listaMaterias.get(opcion).getNombre());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en ejecucion Agregar Nota: " + e.getMessage());
		}
		System.out.println("-----------------------------------------");
		System.out.println("");
	}

	@Override
	public void listarAlumnos() {
		// TODO Auto-generated method stub
		super.listarAlumnos();

		try {
			System.out.println("");
			System.out.println("---------------LISTAR ALUMNOS---------------");
			System.out.println("");

			Map<String, Alumno> lista = alumnosServicio.listarAlumnos();// Llama a metodo para listar

			if (lista.size() != 0) {

				for (Map.Entry<String, Alumno> alumno : lista.entrySet()) {// Iterar sobre cada elemento en Map
					System.out.println("Datos Alumno");
					System.out.println("RUT: " + alumno.getValue().getRut());
					System.out.println("Nombre: " + alumno.getValue().getNombre());
					System.out.println("Apellido: " + alumno.getValue().getApellido());
					System.out.println("Direccion: " + alumno.getValue().getDireccion());
					System.out.println("Materias");
					for (Materia materia : alumno.getValue().getMaterias()) {// Iterar sobre cada elementos de Materia
						System.out.println(materia.getNombre());
						System.out.println("Notas");
						System.out.println(materia.getNotas());
					}
					System.out.println("");
				}
			} else {
				System.out.println("No se pueden listar alumnos");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en ejecucion Listar Alumno: " + e.getMessage());
		}

		System.out.println("-----------------------------------------");
		System.out.println("");
	}

	@Override
	public void terminarPrograma() {
		// TODO Auto-generated method stub
		super.terminarPrograma();
		Utilidad utilidad = new Utilidad();
		utilidad.tiempoEspera();
		utilidad.limpiezaPantalla();
	}

}
