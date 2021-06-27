package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.Alumno;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.Materia;
import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model.MateriaEnum;

public class AlumnoServicio {

	private Map<String, Alumno> listaAlumnos = new TreeMap<String, Alumno>();

	public void crearAlumno(Alumno alumno) {

		listaAlumnos.put(alumno.getRut(), alumno);// alumno -> listaAlumno
		System.out.println(alumno);
		System.out.println(listaAlumnos);
		System.out.println("Alumno creado");
	}

	public void agregarMateria(String rutAlumno, Materia currentMate) {

		Alumno alumno = new Alumno();
		
		alumno = listaAlumnos.get(rutAlumno);// Se obtiene alumno de Map segun rut
		alumno.getMaterias().add(currentMate);// Agregar materia a lista materias en alumno
		listaAlumnos.put(rutAlumno, alumno);// Agrega alumno actualizacon con materia a listaAlumnos

		System.out.println(listaAlumnos); // Ayuda - Testeo de correcto ingreso
		System.out.println("Materia agregada");
	}

	public List<Materia> materiasPorAlumnos(String rutAlumno) {

		Alumno alumno = new Alumno();

		alumno = listaAlumnos.get(rutAlumno);// Se obtiene alumno de Map segun rut

		return alumno.getMaterias();
	}

	public void agregarNotas(List<Materia> notasMateria, String rutAlumno) {
		
		listaAlumnos.get(rutAlumno).setMaterias(notasMateria);//Se agrega lista de Materias con nota en listaAlumnos
		System.out.println(listaAlumnos);//Ayuda - Teste de correcto ingreso
	}

	public Map<String, Alumno> listarAlumnos() {
		
		Map<String, Alumno> lista = listaAlumnos;
		
//		if (lista.size() != 0) {
//			
//			for (Alumno listaAlumnos : lista) {
//				
//			}
//			
//		}else {
//			System.out.println("No se puede listar los productos");
//		}

		return lista;
	}

}
