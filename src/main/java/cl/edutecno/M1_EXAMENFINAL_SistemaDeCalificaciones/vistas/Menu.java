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
	static Scanner scL = new Scanner(System.in);
	static Scanner scI = new Scanner(System.in);
	static Scanner scF = new Scanner(System.in);

	// Metodos Sobreescritos
	@Override
	public void cargarDatos() {
		// TODO Auto-generated method stub
		super.cargarDatos();
	}

	@Override
	public void exportarDatos() {
		// TODO Auto-generated method stub
		super.exportarDatos();
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
			System.out.println("Ingrese RUT: ");
			dato = scL.nextLine();
			alumno.setRut(dato);
			System.out.println("Ingrese Nombre: ");
			dato = scL.nextLine();
			alumno.setNombre(dato);
			System.out.println("Ingrese Apellido: ");
			dato = scL.nextLine();
			alumno.setApellido(dato);
			System.out.println("Ingrese Direccion: ");
			dato = scL.nextLine();
			alumno.setDireccion(dato);
			alumno.setMaterias(listaMaterias);//Poder iterar sobre esta lista

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
		String rut = " ";
		int opcion = 0;

		try {
			System.out.println("");
			System.out.println("---------------AGREGAR MATERIA---------------");
			System.out.println("");
			System.out.println("Ingrese RUT del Alumno: ");
			rut = scL.nextLine();
			
			System.out.println("");
			System.out.println("1. " + MateriaEnum.MATEMATICAS);
			System.out.println("2. " + MateriaEnum.LENGUAJE);
			System.out.println("3. " + MateriaEnum.CIENCIA);
			System.out.println("4. " + MateriaEnum.HISTORIA);
			System.out.print("Selecciona una materia: ");
			opcion = scI.nextInt();

			switch (opcion) {
			case 1:
				materia.setNombre(MateriaEnum.MATEMATICAS);
				break;
			case 2:
				materia.setNombre(MateriaEnum.LENGUAJE);
				break;
			case 3:
				materia.setNombre(MateriaEnum.CIENCIA);
				break;
			case 4:
				materia.setNombre(MateriaEnum.HISTORIA);
				break;
			default:
				System.out.println("Seleccion no valida, no se pudo agregar una materia");
				break;
			}

			alumnosServicio.agregarMateria(rut, materia);//Materia -> Map<String,Alumno>listAlumnos

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

		String rut = "";
		float nota = 0f;
		int count = 1;
		int opcion = 0;

		try {
			System.out.println("");
			System.out.println("---------------AGREGAR NOTA---------------");
			System.out.println("");
			System.out.println("Ingrese RUT del Alumno: ");
			rut = scL.nextLine();
			System.out.println("Alumno tiene las siguientes materias agregadas: ");

			// alumnosServicio.materiasPorAlumnos(dato).stream().filter(x -> x <
			// 10).forEach(i -> System.out.println("." + i.getNombre()));
			Map<String, Alumno> listaAlumnos = alumnosServicio.listarAlumnos();

			for (Materia materia : alumnosServicio.materiasPorAlumnos(rut)) {
				System.out.printf("%d. %s\n", count, materia.getNombre());
				count++;
			}
			System.out.println(alumnosServicio.materiasPorAlumnos(rut));// Ayuda
			System.out.print("Seleccionar materia: ");
			opcion = scI.nextInt();
			System.out.print("Ingresar nota: ");
			nota = scF.nextFloat();

			System.out.println("Nota agregada a ...");

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
