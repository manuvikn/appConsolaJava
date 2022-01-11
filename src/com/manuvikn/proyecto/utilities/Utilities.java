package com.manuvikn.proyecto.utilities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.manuvikn.proyecto.dto.Productos;
import com.manuvikn.proyecto.repository.ProductosRepository;

public class Utilities {
	
	private static Scanner sc = new Scanner(System.in);
	private static ProductosRepository p = ProductosRepository.getInstance();
	
	public static boolean showMenu() {
		System.out.println("1) Listar todos los productos");
		System.out.println("2) Crear un producto");
		System.out.println("3) Actualizar un producto");
		System.out.println("4) Eliminar un producto");
		System.out.println("0) Salir");
		System.out.println();
		
		String o = sc.nextLine();
		System.out.println();
		
		switch (o) {
			case "1" -> {
				System.out.println("Listado de productos\n");
				listProducts();
			}
			case "2" -> {
				System.out.println("Crear un producto\n");
				createProduct();
			}case "3" -> {
				System.out.println("Actualizar producto\n");
				updateProduct();
			}case "4" -> {
				System.out.println("Eliminar un producto\n");
				deleteProduct();
			}
			case "0" -> {
				System.out.println("Adiós, te esperamos!");
				p.closeConnection();
				return false;
			}
			default -> {
				System.out.println("Por favor seleccione una opción válida\n");
			}
		}
		return true;
	}
	
	public static void listProducts() {
		
		StringBuilder sb = new StringBuilder();

		List<Productos> lp = p.findAll();
		int len = lp.size();
		
		for (int i =0; i < len; i ++) {
			sb.append("Producto ").append(i + 1).append(":").append("\n")
			.append("Nombre: ").append(lp.get(i).getNombre()).append("\n")
			.append("Descripción: ").append(lp.get(i).getDescripcion()).append("\n")
			.append("Precio: ").append(lp.get(i).getPrecio()).append("\n")
			.append("Fecha de compra: ").append(lp.get(i).getFechaCompra()).append("\n")
			.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void createProduct() {
		
		System.out.println("Introduzca el nombre del producto: ");
		String n = sc.nextLine();
		
		System.out.println("Introduzca la descripción del producto: ");
		String d = sc.nextLine();
		
		double price = 0D; 
		boolean in = true;
		while (in) {
			in = false;
			System.out.println("Introduzca el precio del producto: ");
			try {
				price = sc.nextDouble();
				sc.nextLine();
			} catch(Exception e) {
				System.out.println("Por favor introduzca un precio válido\n");
				in = true;
				sc.next();
			}
		}
		
		Productos product = new Productos(n, d, price, Date.valueOf(LocalDate.now()));
		p.create(product);
		System.out.println("\nProducto creado correctamente!\n");
		
	}
	
	public static void updateProduct() {
		
		System.out.println("Introduzca el índice del producto que desea actualizar ( El indice se puede consultar en la opción 1 del menú anterior )");
		
		List<Productos> lp = p.findAll();
		int len = lp.size();
		int i = 0;
		boolean in = true;
		while (in) {
			in = false;
			try {
				i = sc.nextInt();
				sc.nextLine();
				if (i < 1 || i > len) {
					System.out.println("El indice introducido esta fuera del rango permitido, vuelva a introducirlo\n");
					in = true;
				} else {
					System.out.println("\nIntroduzca el nombre del producto: ");
					String n = sc.nextLine();
					
					System.out.println("Introduzca la descripción del producto: ");
					String d = sc.nextLine();
					
					double price = 0D; 
					boolean inPrice = true;
					while (inPrice) {
						inPrice = false;
						System.out.println("Introduzca el precio del producto: ");
						try {
							price = sc.nextDouble();
							sc.nextLine();
						} catch(Exception e) {
							System.out.println("Por favor introduzca un precio válido\n");
							inPrice = true;
							sc.next();
						}
					}
					
					Productos product = new Productos(n, d, price, Date.valueOf(LocalDate.now()), lp.get(i - 1).getIdProductos());
					p.update(product);
					System.out.println("\nProducto actualizado correctamente!\n");
				}
			} catch(Exception e) {
				System.out.println("Por favor introduzca un indice válido\n");
				in = true;
				sc.next();
			}
		}	
		
	}
	
	public static void deleteProduct() {
		
		System.out.println("Introduzca el índice del producto que desea eliminar ( El indice se puede consultar en la opción 1 del menú anterior )");
		
		List<Productos> lp = p.findAll();
		int len = lp.size();
		int i = 0;
		boolean in = true;
		while (in) {
			in = false;
			try {
				i = sc.nextInt();
				sc.nextLine();
				if (i < 1 || i > len) {
					System.out.println("El indice introducido esta fuera del rango permitido, vuelva a introducirlo\n");
					in = true;
				} else {
					p.remove(lp.get(i - 1).getIdProductos());
					System.out.println("\nProducto eliminado de la base de datos\n");
				}
			} catch(Exception e) {
				System.out.println("Por favor introduzca un indice válido\n");
				in = true;
				sc.next();
			}
		}	
		
		
	}
}
