package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.vistas;

import java.util.Scanner;

public class MenuTemplate {
	
	static Scanner scI = new Scanner(System.in);
	
	//Metodos
	public void cargarDatos() {
		
	}
	
	public void exportarDatos() {
		
	}
	
	public void crearAlumno() {
		
	}
	
	public void agregarMateria() {
		
	}
	
	public void agregarNotaPasoUno() {
		
	}
	
	public void listarAlumnos() {
		
	}
	
	public void terminarPrograma() {
		
	}
	
	public void iniciarMenu(Scanner sc) {
		
		//Logica pra denotar los demas metodos en base a entrada del teclado
		Menu menu = new Menu();
		
		try {
			int opcion;
			do {
				System.out.println("1. Crear Alumnos");
				System.out.println("2. Listar Alumnos");
				System.out.println("3. Agregar Materias");
				System.out.println("4. Agregar Notas");
				System.out.println("5. Cargar Datos");
				System.out.println("6. Exportar Datos");
				System.out.println("7. Salir");
				System.out.print("Seleccion: ");
				opcion = scI.nextInt();
				
				switch (opcion) {
				case 1:
					menu.crearAlumno();
					break;
				case 2:
					menu.listarAlumnos();
					break;
				case 3:
					menu.agregarMateria();
					break;
				case 4:
					menu.agregarNotaPasoUno();
					break;
				case 5:
					menu.cargarDatos();
					break;
				case 6:
					menu.exportarDatos();
					break;
				case 7:
					menu.terminarPrograma();
					break;
				default:
					System.out.println("---------------OPCION NO VALIDA---------------");
					break;
				}

				
			} while (opcion != 7);
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println("Caracter no valido: " + e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en ejecucion menu: " + e.getMessage());
		}
		
	}
	
	

}

