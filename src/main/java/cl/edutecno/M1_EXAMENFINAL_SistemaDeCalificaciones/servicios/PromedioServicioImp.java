package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.servicios;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class PromedioServicioImp {
	
	//Metodos
	public float calcularPromedio(List<Float> notas) {
		
		float sumaNotas = 0f;
		
		for (Float nota : notas) {
			sumaNotas += nota;
		}
		
		return sumaNotas/notas.size();
	}

}
