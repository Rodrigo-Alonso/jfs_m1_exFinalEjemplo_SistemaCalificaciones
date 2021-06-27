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
		
		listaAlumnos.put(alumno.getRut(), alumno);//alumno -> listaAlumno
		System.out.println(alumno);
		System.out.println(listaAlumnos);
		System.out.println("Alumno creado");
	}
	
	public void agregarMateria(String rutAlumno, Materia currentMate) {
		
		
		Alumno alumno = new Alumno();
		alumno = listaAlumnos.get(rutAlumno);
		System.out.println("1. " + alumno);
		alumno.getMaterias().stream().forEach(i -> i.setNombre(currentMate.getNombre()));
		System.out.println("2. " + alumno);
		listaAlumnos.put(rutAlumno, alumno);
		
		
		//alumno.setMaterias(listaMaterias);;//materia -> listaAlumnos(rut, alumno)
		
		//System.out.println(currentMate);
		System.out.println("3. " + alumno);
		System.out.println(listaAlumnos);
		System.out.println("Materia agregada");
	}
	
	public List<Materia> materiasPorAlumnos(String rutAlumno) {
		//Buscar alumno con rut en Map<>listaAlumno
		Materia materia = new Materia();
		Materia materia2 = new Materia();
		materia.setNombre(MateriaEnum.MATEMATICAS);
		materia2.setNombre(MateriaEnum.HISTORIA);
		ArrayList<Materia> listaMateria = new ArrayList<Materia>();
		listaMateria.add(materia);
		listaMateria.add(materia2);
		
		return listaMateria;
	}
	
	public Map<String, Alumno> listarAlumnos() {
		
		Map<String, Alumno> lista = listaAlumnos;
		
		return lista;
	}
	
	
	

}
