package es.eoi.jdbc.entity;

public class Alumno {
	
	private String Nombre;
	private String Apellido;
	private int Edad;
	private String Dni;
	
	public Alumno(String nombre, String apellido, int edad, String dni) {
		Nombre = nombre;
		Apellido = apellido;
		Edad = edad;
		Dni = dni;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public int getEdad() {
		return Edad;
	}

	public void setEdad(int edad) {
		Edad = edad;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}
	
	
}
