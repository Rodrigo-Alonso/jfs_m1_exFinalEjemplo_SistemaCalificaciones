package cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones;

import java.util.Scanner;

import cl.edutecno.M1_EXAMENFINAL_SistemaDeCalificaciones.vistas.Menu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Scanner sc = new Scanner(System.in);
			Menu menu = new Menu();
			menu.iniciarMenu(sc);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en main: " + e.getMessage());
		}

	}

}
