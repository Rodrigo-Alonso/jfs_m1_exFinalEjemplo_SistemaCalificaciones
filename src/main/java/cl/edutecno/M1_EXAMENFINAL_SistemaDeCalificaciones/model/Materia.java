package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.model;

import java.util.List;

public class Materia {

	private MateriaEnum nombre;
	private List<Float> notas;

	// G&S
	public MateriaEnum getNombre() {
		return nombre;
	}

	public void setNombre(MateriaEnum nombre) {
		this.nombre = nombre;
	}

	public List<Float> getNotas() {
		return notas;
	}

	public void setNotas(List<Float> notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return nombre + "\n" + "Notas: " + "\n" + notas;
	}

}
