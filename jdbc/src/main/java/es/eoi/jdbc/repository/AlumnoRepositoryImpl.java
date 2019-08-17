package es.eoi.jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.eoi.jdbc.entity.Alumno;

public class AlumnoRepositoryImpl implements AlumnoRepository {

	private Connection openConnection() {
		String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
		String user = "root";
		String pass = "1234";

		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(url, user, pass);
			System.out.println("Conectado a ".concat(url));

		} catch (Exception e) {
			System.err.println("No se ha realizado correctamente la accion");
			System.err.println(e.getMessage());
		}

		return conexion;
	}

	public Alumno findByDni(String dni) {
		String dniFound = null;
		String nombre = null;
		String apellido = null;
		int edad = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * ");
		sb.append("FROM ALUMNO ");
		sb.append("WHERE DNI = ? ");

		try {
			
			Connection conn = openConnection();

			PreparedStatement pst = conn.prepareStatement(sb.toString());
			pst.setString(1, dni);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				dniFound = rs.getString("Dni");
				nombre = rs.getString("Nombre");
				apellido = rs.getString("Apellidos");
				edad = rs.getInt("Edad");
			}
			
			conn.close();
			
		} catch (Exception e) {
			System.err.println("No se ha realizado correctamente la accion");
			System.err.println(e.getMessage());
		}

		Alumno newAlumno = new Alumno(nombre, apellido, edad, dniFound);
		System.out.println("DEBUG Nombre:" + newAlumno.getNombre() + " edad: " + newAlumno.getEdad());
		return newAlumno;
	}

	public List<Alumno> findAll() {

		String dniFound = null;
		String nombre = null;
		String apellido = null;
		int edad = 0;

		List<Alumno> selectedAlumnos = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * ");
		sb.append("FROM ALUMNO ");
		sb.append("WHERE DNI = ? ");

		try {
			selectedAlumnos = new ArrayList<Alumno>();
			Connection conn = openConnection();

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM ALUMNO");

			while (rs.next()) {
				dniFound = rs.getString("Dni");
				nombre = rs.getString("Nombre");
				apellido = rs.getString("Apellidos");
				edad = rs.getInt("Edad");

				Alumno newAlumno = new Alumno(nombre, apellido, edad, dniFound);
				selectedAlumnos.add(newAlumno);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("No se ha realizado correctamente la accion");
			System.err.println(e.getMessage());
		}

		return selectedAlumnos;
	}

	public boolean create(Alumno alumno) {
		int resultQuery = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append("ALUMNO ");
		sb.append("VALUES( ");
		sb.append(" ? ,");
		sb.append(" ? ,");
		sb.append(" ? ,");
		sb.append(" ? );");

		try {

			Connection conn = openConnection();

			PreparedStatement pst = conn.prepareStatement(sb.toString());
			pst.setString(1, alumno.getDni());
			pst.setString(2, alumno.getNombre());
			pst.setString(3, alumno.getApellido());
			pst.setInt(4, alumno.getEdad());

			resultQuery = pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.err.println("No se ha realizado correctamente la accion");
			System.err.println(e.getMessage());
		}

		if (resultQuery > 0) {
			return true;
		}
		return false;
	}

	public boolean delete(String dni) {

		int resultQuery = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("FROM ALUMNO ");
		sb.append("WHERE DNI = ? ");

		try {
			Connection conn = openConnection();

			PreparedStatement pst = conn.prepareStatement(sb.toString());
			pst.setString(1, dni);

			resultQuery = pst.executeUpdate();

			conn.close();
		} catch (Exception e) {
			System.err.println("No se ha realizado correctamente la accion");
			System.err.println(e.getMessage());
		}

		if (resultQuery >= 1) {
			System.out.println("Alumno borrado correctamente");
			return true;
		}
		return false;

	}

	public boolean update(String dni, String nombre, String apellidos) {
		
		int resultQuery = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ALUMNO ");
		sb.append("SET ");
		sb.append("Nombre = ? ,");
		sb.append("Apellidos = ? ");
		sb.append("WHERE DNI = ? ;");

		try {
			Connection conn = openConnection();

			PreparedStatement pst = conn.prepareStatement(sb.toString());
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setString(3, dni);

			resultQuery = pst.executeUpdate();

			conn.close();
		} catch (Exception e) {
			System.err.println("No se ha realizado correctamente la accion");
			System.err.println(e.getMessage());
		}

		if (resultQuery > 0) {
			System.out.println("Alumno modificado correctamente");
			return true;
		}
		System.out.println("No se ha podido modificar al alumno correctamente");

		return false;
	}

}
