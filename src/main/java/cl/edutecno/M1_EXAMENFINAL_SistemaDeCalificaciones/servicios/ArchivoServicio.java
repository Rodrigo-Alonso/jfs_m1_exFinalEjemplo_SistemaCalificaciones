package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.servicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.Alumno;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.Materia;

public class ArchivoServicio {

	private List<Alumno> alumnosACargarAlumnos;
	PromedioServicioImp promedioServicioImp = new PromedioServicioImp();

	// Metodos
	public List<Alumno> cargarDatos(String ruta) {
		// Cargar datos desde .csv

		return null;
	}

	public void exportarDatos(Map<String, Alumno> alumnos, String ruta) {
		
		DecimalFormat df = new DecimalFormat("#.#");//Formato decimales
		df.setRoundingMode(RoundingMode.CEILING);//Formato de redondeo 
		
		try {

			File directorio = new File(ruta);
			comprobarDirectorio(directorio);//Comprueba si directorio existe
			File archivo = new File(directorio + "/notas.txt");
			if (comprobarFichero(archivo)) {//Comprueba si archivo existe
				archivo.createNewFile();
				FileWriter fw = new FileWriter(archivo);
				BufferedWriter bw = new BufferedWriter(fw);

				for (Map.Entry<String, Alumno> alumno : alumnos.entrySet()) {// Iterar sobre cada elemento en Map
					bw.write("Alumno : " + alumno.getValue().getRut() 
							+ " - " + alumno.getValue().getNombre() + "\n"); // Guarda rut y nombre
					for (Materia materia : alumno.getValue().getMaterias()) { // Itera sobre lista materias
						bw.write("Materia : " + materia.getNombre() + " - Promedio : "
								+ df.format(promedioServicioImp.calcularPromedio(materia.getNotas())) + "\n");// Guarda Materia y Promedio
					}
				}
				bw.close();
				System.out.println("Datos exportados correctamente.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en guardado de datos: " + e.getMessage());
		}
	}

	public void comprobarDirectorio(File directorio) {

		try {
			if (directorio.exists() != true) {
				directorio.mkdir();
				System.out.println("Directorio Creado");
			} else {
				System.out.println("El directorio ya existe");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ha ocurrido un error al crear el directorio - " + e.getMessage());
		}
	}

	public boolean comprobarFichero(File fichero) {

		Scanner sc = new Scanner(System.in);
		boolean crear = false;
		boolean condicion = true;
		String respuesta = " ";

		try {
			if (fichero.exists() != true) {
				crear = true;
			} else {
				System.out.print("El archivo notas.txt ya existe, ¿Desea sobreescibirlo? [s/n]: ");
				respuesta = sc.nextLine();
				while (condicion) {
					if (respuesta.equalsIgnoreCase("s")) {
						crear = true;
						condicion = false;
					} else if (respuesta.equalsIgnoreCase("n")) {
						System.out.println("No se escribira en el archivo notas.txt");
						crear = false;
						condicion = false;
					} else {
						System.out.println("Opcion invalida");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ha ocurrido un error al crear el archivo - " + e.getMessage());
		}
		return crear;
	}

}
