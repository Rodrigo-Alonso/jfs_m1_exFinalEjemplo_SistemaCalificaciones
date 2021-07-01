package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.Alumno;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.Materia;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.MateriaEnum;

public class ArchivoServicio {

	private List<Alumno> alumnosACargarAlumnos;
	PromedioServicioImp promedioServicioImp = new PromedioServicioImp();

	// Metodos
	public List<Alumno> cargarDatos(String ruta) {
		// Cargar datos desde .csv
		alumnosACargarAlumnos = new ArrayList<Alumno>();
		String[] arrayData;

		try {

			File archivo = new File(ruta);
			if (archivo.exists()) {
				FileReader fr = new FileReader(archivo);
				BufferedReader br = new BufferedReader(fr);
				String data = br.readLine();

				while (data != null) {
					Alumno alumno = new Alumno();
					Materia materia = new Materia();
					List<Materia> listaMaterias = new ArrayList<Materia>();
					List<Float> listaNotas = new ArrayList<Float>();
					arrayData = data.split(",");// [rut, nombre, apellido, direccion, materia, nota]
					//System.out.println(arrayData);

					// [rut, nombre, apellido, direccion, List<materia>[materiaEnum,
					// List<nota>[nota_1,nota_2,...,nota_n]]]

					// AGREGAR MATERIAS Y NOTAS A ALUMNOS CORRESPONDIENTES SEGUN RUT

					if (alumnosACargarAlumnos.size() == 0) {
						alumno.setRut(arrayData[0]);
						alumno.setNombre(arrayData[1]);
						alumno.setApellido(arrayData[2]);
						alumno.setDireccion(arrayData[3]);
						materia.setNombre(MateriaEnum.valueOf(arrayData[4].toUpperCase()));
						listaNotas.add(Float.parseFloat(arrayData[5]));
						materia.setNotas(listaNotas);
						listaMaterias.add(materia);
						alumno.setMaterias(listaMaterias);
						alumnosACargarAlumnos.add(alumno);
					} else {

						for (Alumno alumnoTemp : alumnosACargarAlumnos) {

							if (alumnoTemp.getRut().equalsIgnoreCase(arrayData[0])) {

								for (Materia materiaTemp : alumnoTemp.getMaterias()) {

									if (materiaTemp.getNombre().equals(MateriaEnum.valueOf(arrayData[4].toUpperCase()))) {
										materiaTemp.getNotas().add(Float.parseFloat(arrayData[5]));
									} else {
										materiaTemp.setNombre(MateriaEnum.valueOf(arrayData[4].toUpperCase()));
										materiaTemp.getNotas().add(Float.parseFloat(arrayData[5]));
									}

									// listaMaterias.add(materia);
									// alumnoTemp.setMaterias(listaMaterias);
								}
							} else {
								alumno.setRut(arrayData[0]);
								alumno.setNombre(arrayData[1]);
								alumno.setApellido(arrayData[2]);
								alumno.setDireccion(arrayData[3]);
								materia.setNombre(MateriaEnum.valueOf(arrayData[4]));
								listaNotas.add(Float.parseFloat(arrayData[5]));
								materia.setNotas(listaNotas);
								listaMaterias.add(materia);
								alumno.setMaterias(listaMaterias);
								alumnosACargarAlumnos.add(alumno);
							}
						}
					}
					System.out.println(alumnosACargarAlumnos);
					data = br.readLine();
				}

			} else {
				System.out.printf("No se pudo cargar datos desde la siguiente ruta: %s\n", ruta);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en cargado de datos: " + e.getMessage());
		}

		return alumnosACargarAlumnos;
	}

	public void exportarDatos(Map<String, Alumno> alumnos, String ruta) {

		DecimalFormat df = new DecimalFormat("#.#");// Formato decimales
		df.setRoundingMode(RoundingMode.CEILING);// Formato de redondeo

		try {

			File directorio = new File(ruta);
			comprobarDirectorio(directorio);// Comprueba si directorio existe
			File archivo = new File(directorio + "/notas.txt");
			if (comprobarFichero(archivo)) {// Comprueba si archivo existe
				archivo.createNewFile();
				FileWriter fw = new FileWriter(archivo);
				BufferedWriter bw = new BufferedWriter(fw);

				for (Map.Entry<String, Alumno> alumno : alumnos.entrySet()) {// Iterar sobre cada elemento en Map
					// Escribe rut y nombre alumno
					bw.write("Alumno : " + alumno.getValue().getRut() + " - " + alumno.getValue().getNombre() + "\n");

					for (Materia materia : alumno.getValue().getMaterias()) { // Itera sobre lista materias
						// Escribe materia y promedio
						bw.write("Materia : " + materia.getNombre() + " - Promedio : "
								+ df.format(promedioServicioImp.calcularPromedio(materia.getNotas())) + "\n");

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
