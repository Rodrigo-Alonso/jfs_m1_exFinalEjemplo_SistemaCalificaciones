package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.utilidades;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

public class Utilidad {

	public void tiempoEspera() {

		System.out.println("");
		System.out.print("Abadonando el sistema de Calificaciones");

		try {
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".\n");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Acaba de salir del sistema");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("Error de ejecucion de tiempo: " + e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error no identificado: " + e.getMessage());
		}

	}

	public void limpiezaPantalla() {
		for (int i = 0; i < 200; i++) {
			System.out.println("");
		}
		System.exit(0);
	}

}
