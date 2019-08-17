package es.eoi.jdbc.app;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.service.AlumnoService;
import es.eoi.jdbc.service.AlumnoServiceImpl;

public class GestionInstituto {
	public static AlumnoService myService;

	public static void main(String[] args) {
		
		myService = new AlumnoServiceImpl();
		
//		Alumno alumnoFound = myService.findByDni("48774753X");
//		
//		System.out.println("Alumno encontrado");
//		System.out.println("Nombre: " + alumnoFound.getNombre());
		
	}
	
	public static void printMenu() {
		
	}
}
