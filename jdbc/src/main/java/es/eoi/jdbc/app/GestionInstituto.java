package es.eoi.jdbc.app;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.service.AlumnoService;
import es.eoi.jdbc.service.AlumnoServiceImpl;

public class GestionInstituto {
	public static AlumnoService myService;

	public static void main(String[] args) {

		myService = new AlumnoServiceImpl();
		printMenu();
	}

	public static void printMenu() {
		int option = 0;

		Scanner scan = new Scanner(System.in);

		System.out.println("GESTION INSTITUTO V1");
		System.out.println("-------------------------------");
		System.out.println("1 –Listado Alumnos");
		System.out.println("2 –Busca Alumno (DNI)");
		System.out.println("3 –Crear Alumno");
		System.out.println("4 –Modificar Alumno");
		System.out.println("5 –Eliminar Alumno");
		System.out.println("0 –SALIR");
		System.out.println("-------------------------------");

		if (scan.hasNextInt()) {
			option = scan.nextInt();
		} else {
			System.out.println("No se ha introducido una opcion correcta");
			printMenu();
		}

		switch (option) {
		case 1:
			listAlumnos();
			break;
		case 2:
			searchAlumno();
			break;
		case 3:
			createAlumno();
			break;
		case 4:
			updateAlumno();
			break;
		case 5:
			deleteAlumno();
			break;
		case 0:
			System.out.println("Saliendo del programa...");
			return;
		default:
			break;
		}
		printMenu();
	}

	private static void searchAlumno() {
		String parteNumericaDni = "";
		String letraDni = "";

		Scanner scan = new Scanner(System.in);

		System.out.println("Introduce el DNI del alumno a buscar");
		String dniIntroducido = scan.nextLine();
		if (validarDNI(dniIntroducido)) {
			myService.findByDni(dniIntroducido);
		} else {
			System.out.println("El DNI que ha introducido es erroneo");
			searchAlumno();
		}
	}

	public static boolean validarDNI(String dni) {

		boolean correcto = false;

		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		Matcher matcher = pattern.matcher(dni);

		if (matcher.matches()) {

			String letra = matcher.group(2);
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			int index = Integer.parseInt(matcher.group(1));

			index = index % 23;

			String reference = letras.substring(index, index + 1);

			if (reference.equalsIgnoreCase(letra)) {
				correcto = true;
			} else {
				correcto = false;
			}
		} else {
			correcto = false;
		}

		return correcto;

	}
	private static void listAlumnos() {
		List<Alumno> listAlumno = myService.findAll();
		
		System.out.println("Alumnos encontrados:");
		
		for (Alumno alumno : listAlumno) {
			System.out.println("DNI: ".concat(alumno.getDni()));
			System.out.println("Nombre: ".concat(alumno.getNombre()));
			System.out.println("Apellidos: ".concat(alumno.getApellido()));
			System.out.println("Edad: ".concat(String.valueOf(alumno.getEdad())));
			System.out.println();
		}
	}
	private static void createAlumno() {
		Scanner scan = new Scanner(System.in);
		int edad = 0;

		System.out.println("Introduce el DNI del alumno:");
		String dni = scan.nextLine();
		System.out.println(dni);

		if (!validarDNI(dni)) {
			System.out.println("El DNII que ha introducido es erroneo");
			createAlumno();
		}

		System.out.println("Introduce el nombre del alumno:");
		String nombre = scan.nextLine();

		scan.reset();

		System.out.println("Introduce el apellido del alumno:");
		String apellido = scan.nextLine();

		do {
			System.out.println("Introduce la edad del alumno:");
			if (scan.hasNextInt()) {
				edad = scan.nextInt();
			} else {
				System.out.println("Ha introducido un valor erroneo para la edad");
			}
		} while (edad <= 0);

		Alumno alumno = new Alumno(nombre, apellido, edad, dni);

		if (myService.create(alumno)) {
			System.out.println("Alumno registrado correctamente");
		} else {
			System.out.println("ERROR - No se ha registrado el alumno correctamente");
		}
	}

	private static void updateAlumno() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Introduce el DNI del alumno a modificar");
		String dni = scan.nextLine();

		if (!validarDNI(dni)) {
			System.out.println("El DNI introducido no es valido");
			updateAlumno();
		}

		System.out.println("Introduce el nombre que sera registrado");
		String nombre = scan.nextLine();

		System.out.println("Introduce los apellidos que seran registrados");
		String apellidos = scan.nextLine();

		if (myService.update(dni, nombre, apellidos)) {
			System.out.println("Alumno modificado correctamente");
		} else {
			System.out.println("No se ha modificado correctamente al alumno");
		}

	}

	private static void deleteAlumno() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Introduce el DNI del alumno a modificar");
		String dni = scan.nextLine();

		if (!validarDNI(dni)) {
			System.out.println("El DNI introducido no es valido");
			deleteAlumno();
		}
		myService.delete(dni);
	}
}
