package com.manuvikn.proyecto;

import static com.manuvikn.proyecto.utilities.Utilities.showMenu;

public class Main {
	
	public static void main(String[] args) {

		System.out.println("Bienvenido al proyecto CRUD Productos ¿qué desea hacer?\n");
		
		boolean in = true;
		while(in) {
			in = showMenu();
		}
		
	}
	

}
